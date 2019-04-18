package com.QuestionBank.controller;


import com.QuestionBank.entity.GetResultEntity;
import com.QuestionBank.exception.MException;
import com.QuestionBank.service.LiveStatisService;
import com.QuestionBank.utils.DateUtil;
import com.QuestionBank.utils.PropertiesUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "getInfo")
public class GetUserInfoController {
    private Logger log = Logger.getLogger(GetUserInfoController.class);


    @Autowired
    private LiveStatisService liveStatisService;

    @RequestMapping(value = "getProfileInfo.do")
    public  @ResponseBody  JSONObject  getProfileInfo(@RequestParam(value = "subjectId") String subjectId){
        log.info("根据subjectId查询所有用户的信息："+subjectId);

        try {
            List<String> stringList = liveStatisService.getUserList(subjectId);
            log.info("userList:"+ stringList);

            String mysql_url = PropertiesUtils.getInstance().getProperty("mysql_url");
            String user_name = PropertiesUtils.getInstance().getProperty("user_name");
            String user_password = PropertiesUtils.getInstance().getProperty("user_password");
            String save_path = PropertiesUtils.getInstance().getProperty("save_path");

            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序
                log.info("Success loading Mysql Driver!");
                Connection connect = DriverManager.getConnection(
                        mysql_url,user_name,user_password);
                //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
//            File file=new File(PropertiesUtils.getInstance().getProperty("dd"));
            File file=new File(save_path);
            if (!file.exists()){
                file.mkdir();
            }
            String toDay = DateUtil.formatSimpleDate(new Date());
//            FileWriter out = new FileWriter(file+toDay+".11csv", true);
            FileWriter out = new FileWriter(save_path+subjectId+"_"+toDay+".csv", true);
            log.info("用户的个数："+stringList.size());
            for (String pid:stringList){
                log.info("user:"+pid);

                    Long pId = Long.parseLong(pid);
                    log.info("pId:"+pId);
                    Statement stmt = connect.createStatement();
                    String sql="SELECT * FROM  t_profile_info_1 where profile_id ="+pId;
                    ResultSet rs = stmt.executeQuery(sql);
                    //user 为你表的名称
                    while (rs.next()) {

                        //往文件写入
                        out.write(rs.getString("name"));
                        out.write(",");
                        out.write(rs.getString("mobile"));
                        //换行
                        out.write("\r\n");
                    }
                }
                        //刷新IO内存流
                        out.flush();
                        //关闭
                        out.close();
                GetResultEntity getResultEntity=GetResultEntity.create200();
              return  getResultEntity.toJSONObject();
        }catch (MException e){
            return  e.toJSONObject();
        }catch (Exception e){
            return  MException.create500(e.getMessage()).toJSONObject();
        }
    }
}

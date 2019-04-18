package com.QuestionBank.controller;


import com.QuestionBank.entity.GetResultEntity;
import com.QuestionBank.entity.LiveInfoEntity;
import com.QuestionBank.exception.MException;
import com.QuestionBank.service.ILiveInfoService;
import com.QuestionBank.utils.DateUtil;
import com.QuestionBank.utils.PropertiesUtils;
import com.QuestionBank.utils.StringUtils;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "getLiveInfo")
public class GetLivesInfoController {
    private Logger log = Logger.getLogger(GetLivesInfoController.class);


    @Autowired
    private ILiveInfoService liveInfoService;

    @RequestMapping(value = "getlivesInfo.do")
    public  @ResponseBody  JSONObject  getLivesInfo(@RequestParam(value = "startTime") String startTime,
                                                    @RequestParam(value = "endTime") String endTime)  {
        log.info("getlivesInfo ："+startTime+"endTime : "+endTime);


        /*String startTime = (String) jsonObject.get("startTime");
        String endTime = (String) jsonObject.get("endTime");*/

        if (StringUtils.isBlank(startTime)){
            return MException.create500("开始时间不能为空！").toJSONObject();
        }

        if (StringUtils.isBlank(endTime)){
            endTime= DateUtil.getMoosNow();
        }

        try {
            List<LiveInfoEntity> liveInfoEntities= liveInfoService.getLivesInfo(startTime,endTime);
            log.info("liveInfoEntities:"+ liveInfoEntities);

            //String save_path = PropertiesUtils.getInstance().getProperty("save_path");
            String save_path = PropertiesUtils.getInstance().getProperty("live_info_save_path");

            File file=new File(save_path);
            if (!file.exists()){
                file.mkdir();
            }
           //String toDay = DateUtil.formatSimpleDate(new Date());
//            FileWriter out = new FileWriter(file+toDay+".11csv", true);
            Date startTimeDate = DateUtil.convertToDate(startTime.trim(), "yyyy-MM-dd");
            Date  endTimeDate= DateUtil.convertToDate(endTime.trim(), "yyyy-MM-dd");
            String s = DateUtil.formatSimpleDate(startTimeDate);
            String s1 = DateUtil.formatSimpleDate(endTimeDate);

            String filrReName=s+"_"+s1+".csv";
            log.info("直播的个数："+liveInfoEntities.size());
            System.out.println(liveInfoEntities.size());

            //开始写入excel,创建模型文件头
            String[] titleA = {"标题","学校","播放次数","访问人次","访问人数","创建时间"};

            File fileA = new File(save_path+filrReName);
                if(fileA.exists()){
                    //如果文件存在就删除
                    fileA.delete();
                }

                fileA.createNewFile();
            //创建工作簿
            WritableWorkbook workbookA = Workbook.createWorkbook(fileA);
            //创建sheet
            WritableSheet sheetA = workbookA.createSheet("sheet1", 0);
            Label labelA = null;
            //设置列名
            for (int i = 0; i < titleA.length; i++) {
            labelA = new Label(i,0,titleA[i]);
            sheetA.addCell(labelA);
            }

            //获取数据源
            for (int i = 0; i < liveInfoEntities.size(); i++) {
                labelA = new Label(0,i+1,liveInfoEntities.get(i).getTitle());
                sheetA.addCell(labelA);
                labelA = new Label(1,i+1,liveInfoEntities.get(i).getSchoolName());
                sheetA.addCell(labelA);
                labelA = new Label(2,i+1,liveInfoEntities.get(i).getPlayTimes());
                sheetA.addCell(labelA);
                labelA = new Label(3,i+1,liveInfoEntities.get(i).getReVisitTimes());
                sheetA.addCell(labelA);
                labelA = new Label(4,i+1,liveInfoEntities.get(i).getVisitTimes());
                sheetA.addCell(labelA);
                labelA = new Label(5,i+1,liveInfoEntities.get(i).getCreateTime());
                sheetA.addCell(labelA);

            }
                workbookA.write();//写入数据        
                workbookA.close(); //关闭连接
            log.info("成功写入文件，请前往E盘查看文件！");



            /*for (int i=0;i<liveInfoEntities.size();i++){
                log.info("liveInfoEntity:"+liveInfoEntities.get(i));
                        //往文件写入
                out.write(liveInfoEntities.get(i).getTitle());
                out.write(liveInfoEntities.get(i).getSchoolName());
                out.write(liveInfoEntities.get(i).getPlayTimes());
                out.write(liveInfoEntities.get(i).getReVisitTimes());
                out.write(liveInfoEntities.get(i).getVisitTimes());
                       // out.write(",");
                        //换行
                        out.write("\r\n");
                        if ((i+1)%500==0){
                            out.flush();
                        }
                    }

                        //刷新IO内存流
                        out.flush();
                        //关闭
                        out.close();*/
                GetResultEntity getResultEntity=GetResultEntity.create200();
                getResultEntity.setData(filrReName);
              return  getResultEntity.toJSONObject();
        }catch (MException e){
            return  e.toJSONObject();
        }catch (Exception e){
            return  MException.create500(e.getMessage()).toJSONObject();
        }
    }
}

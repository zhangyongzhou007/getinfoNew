package com.QuestionBank.entity;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * @ClassName: SchoolEntity
 * @Description: 慕校信息
 * @author LeonFung
 * @date 2017-09-06 16:28:41
 */
@JsonSerialize(include=Inclusion.NON_NULL)
public class SchoolEntity implements Comparable{

	//组织架构中的Id
	private String orgId;
	public static final String ORG_ID = "orgId";
	
	//慕校编号
	private String schoolId;
	public static final String SCHOOL_ID = "schoolId";

	//慕校名称
	private String name;
	public static final String NAME = "name";

	//慕校状态  0：未开通；1：已开通；2：已屏蔽
	private String status;
	public static final String STATUS = "status";

	//慕校类型
	private String typeId;
	public static final String TYPE_ID = "typeId";

	//慕校关键词(多个已分号:分隔)
	private String keys;
	public static final String KEYS = "keys";
	
	//慕校简介
	private String introduction;
	public static final String INTRODUCTION = "introduction";

	//慕校详细描述
	private String detail;
	public static final String DETAIL = "detail";
	
	//慕校头像
	private String imageUrl;
	public static final String IMAGE_URL = "imageUrl";

	//慕校PC形象图
	private String pcImageUrl;
	public static final String PC_IMAGE_URL = "pcImageUrl";

	//慕校手机形象图
	private String mbImageUrl;
	public static final String MB_IMAGE_URL = "mbImageUrl";

	//直播服务状态 0：未申请；1：待开通；2：已开通；3：已关闭
	private String liveServiceStatus;
	public static final String LIVE_SERVICE_STATUS = "liveServiceStatus";

	//是否开通了教视通服务 0：未开通  1：已开通
	private String jstServer;
	public static final String JST_SERVICE_STATUS = "jstServer";

	//直播宝主账号
	private String zbbAccount;
	public static final String ZBB_ACCOUNT = "zbbAccount";

	//云屋企业帐号(云屋组织ID)
	private String companyId;
	public static final String COMPANY_ID = "companyId";
	
	//云屋企业帐号密码
	private String companyPassword;
	public static final String COMPANY_PASSWORD = "companyPassword";

	//创建方法
	private String source;
	public static final String SOURCE = "source";
	
	
	//创建时间
	private String createTime;
	public static final String CREATE_TIME = "createTime";
	
	//修改时间
	private String modifyTime;
	public static final String MODIFY_TIME = "modifyTime";

	//修改标记
	private int update = 1;
	
	//慕校类型（非入库字段）
	private String type;
	
	//手机号码（非入库字段）
	private String phone;
	
	//当前用户对应的云屋账号（非入库字段）
	private String curYwAccount;

	//用户ID（非入库字段）
	private String profileId;
	
	//当前慕校下账号与注册到云屋子账户的个数（非入库字段）	避免出现null值，直接赋值为0
	private Long AssociationNum = 0L;

	//当前慕校所授权的慕校（多个）（非入库字段）
	public String[] authSchools;
//	public List<SchoolAuthorizationEntity> authList;

	//教视通管理员账号（非入库字段）
	public String jstSchoolId;

	//终端搜索接收字段（非入库）
	private String queryString;

	//当前登录人是否关注此慕校（非入库）0：未关注  1：已关注
	private String isFollow = "0";

	//当前登录人是否属于此慕校成员 0:非此慕校成员；1:是此慕校成员（此时无法取消关注）
	private String isBelong = "0";

	//是终端的请求操作
	private boolean isTerminal = false;

	//是终端的请求操作，创建慕校的时候判断
	private String fromTerminal = "0";

	//关注数量
	private int followNum = 0;

	//课程数量
	private int courseNum = 0;

	//名师数量
	private int teacherNum = 0;

	//学生数量
	private int studentNum = 0;

	//StarCMS模糊查询字段
	private String queryStr;
	
	//审核状态：0：未审核  1：通过  2：拒绝
	private String approveStatus;
	public static final String APPROVE_STATUS = "approveStatus";
	
	//查询条件使用（非入库）
	private String startDate;
	private String endDate;


	public String getOrgId() {
		return orgId;
	}

//	public List<SchoolAuthorizationEntity> getAuthList() {
//		return authList;
//	}
//
//	public void setAuthList(List<SchoolAuthorizationEntity> authList) {
//		this.authList = authList;
//	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getLiveServiceStatus() {
		return liveServiceStatus;
	}
	public void setLiveServiceStatus(String liveServiceStatus) {
		this.liveServiceStatus = liveServiceStatus;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public int getUpdate() {
		return update;
	}
	public void setUpdate(int update) {
		this.update = update;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public static HashMap<String,SchoolEntity> getMap(List<SchoolEntity> schools){
		HashMap<String,SchoolEntity> map = new HashMap<String,SchoolEntity>();
		for (int i=0;i<schools.size();i++){
			map.put(schools.get(i).getSchoolId(),schools.get(i));
		}

		return map;
	}
	public String getZbbAccount() {
		return zbbAccount;
	}
	public void setZbbAccount(String zbbAccount) {
		this.zbbAccount = zbbAccount;
	}
	
	public Long getAssociationNum() {
		return AssociationNum;
	}
	
	public void setAssociationNum(Long associationNum) {
		AssociationNum = associationNum;
	}
	public String getCompanyPassword() {
		return companyPassword;
	}
	public void setCompanyPassword(String companyPassword) {
		this.companyPassword = companyPassword;
	}
	public String getCurYwAccount() {
		return curYwAccount;
	}
	public void setCurYwAccount(String curYwAccount) {
		this.curYwAccount = curYwAccount;
	}
	public String getJstServer() {
		return jstServer;
	}
	public void setJstServer(String jstServer) {
		this.jstServer = jstServer;
	}
	public String[] getAuthSchools() {
		return authSchools;
	}
	public void setAuthSchools(String[] authSchools) {
		this.authSchools = authSchools;
	}
	public String getJstSchoolId() {
		return jstSchoolId;
	}

	public void setJstSchoolId(String jstSchoolId) {
		this.jstSchoolId = jstSchoolId;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getPcImageUrl() {
		return pcImageUrl;
	}

	public void setPcImageUrl(String pcImageUrl) {
		this.pcImageUrl = pcImageUrl;
	}

	public String getMbImageUrl() {
		return mbImageUrl;
	}

	public void setMbImageUrl(String mbImageUrl) {
		this.mbImageUrl = mbImageUrl;
	}

	public void setIsFollow(String isFollow) {
		this.isFollow = isFollow;
	}

	public boolean getIsTerminal() {
		return isTerminal;
	}

	public void setIsTerminal(boolean isTerminal) {
		this.isTerminal = isTerminal;
	}

	public String getIsFollow() {
		return isFollow;
	}

	public String getIsBelong() {
		return isBelong;
	}

	public void setIsBelong(String isBelong) {
		this.isBelong = isBelong;
	}

	public int getFollowNum() {
		return followNum;
	}

	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}

	public int getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}

	public int getTeacherNum() {
		return teacherNum;
	}

	public void setTeacherNum(int teacherNum) {
		this.teacherNum = teacherNum;
	}

	public int getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public int compareTo(Object o){
		if(o instanceof SchoolEntity){
			SchoolEntity tmp = (SchoolEntity)o;
			return this.name.compareTo(tmp.name);
		}
		return -1;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public String getFromTerminal() {
		return fromTerminal;
	}

	public void setFromTerminal(String fromTerminal) {
		this.fromTerminal = fromTerminal;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	@Override
	public String toString() {
		return "SchoolEntity{" +
				"orgId='" + orgId + '\'' +
				", schoolId='" + schoolId + '\'' +
				", name='" + name + '\'' +
				", status='" + status + '\'' +
				", typeId='" + typeId + '\'' +
				", keys='" + keys + '\'' +
				", introduction='" + introduction + '\'' +
				", detail='" + detail + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				", pcImageUrl='" + pcImageUrl + '\'' +
				", mbImageUrl='" + mbImageUrl + '\'' +
				", liveServiceStatus='" + liveServiceStatus + '\'' +
				", jstServer='" + jstServer + '\'' +
				", zbbAccount='" + zbbAccount + '\'' +
				", companyId='" + companyId + '\'' +
				", companyPassword='" + companyPassword + '\'' +
				", source='" + source + '\'' +
				", createTime='" + createTime + '\'' +
				", modifyTime='" + modifyTime + '\'' +
				", update=" + update +
				", type='" + type + '\'' +
				", phone='" + phone + '\'' +
				", curYwAccount='" + curYwAccount + '\'' +
				", profileId='" + profileId + '\'' +
				", AssociationNum=" + AssociationNum +
				", authSchools=" + Arrays.toString(authSchools) +
				", jstSchoolId='" + jstSchoolId + '\'' +
				", queryString='" + queryString + '\'' +
				", isFollow='" + isFollow + '\'' +
				", isBelong='" + isBelong + '\'' +
				", isTerminal=" + isTerminal +
				", fromTerminal='" + fromTerminal + '\'' +
				", followNum=" + followNum +
				", courseNum=" + courseNum +
				", teacherNum=" + teacherNum +
				", studentNum=" + studentNum +
				", queryStr='" + queryStr + '\'' +
				", approveStatus='" + approveStatus + '\'' +
				", startDate='" + startDate + '\'' +
				", endDate='" + endDate + '\'' +
				'}';
	}
}

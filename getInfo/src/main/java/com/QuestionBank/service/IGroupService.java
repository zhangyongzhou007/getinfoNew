package com.QuestionBank.service;


import com.QuestionBank.entity.DeptInfoEntity;
import com.QuestionBank.entity.OrgInfoEntity;
import com.QuestionBank.entity.ProfileInfoEntity;
import com.QuestionBank.entity.RegInfoEntity;
import com.QuestionBank.entity.e.RegScene;
import com.QuestionBank.exception.MException;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 组织接口
* @ClassName: IGroupService  
* @Description: TODO(
* @author LeonFung  
* @date 2017年9月7日  下午4:00:41 
*
 */
public interface IGroupService {
	
	/**
	 * 根据手机号码查找组织ID
	 * 校验手机号是否为组织管理员手机号,如果否则返回null或者""
	* @Title: getOrgIdByPhone  
	* @param  phone : 手机号码
	* @return orgId : 组织架构中的Id
	* @author LeonFung  
	* @date 2017年9月7日 下午4:01:59 
	* @throws
	 */
	String findOrgIdByPhone(String phone) throws MException;
	
	/**
	 * 根据手机号创建账号，并注册组织
	 * 组织名称和组织简称=慕校名称，组织类型=学校，联系人=输入的手机号
	* @Title: createGroup  
	* @param  phone : 手机号码
	* @param  schoolName : 慕校名称
	* @param  introduction : 慕校简介
	* @param  imageUrl : 慕校头像URL
	* @return orgId : 组织架构中的Id  
	* @author LeonFung  
	* @date 2017年9月7日 下午4:12:29 
	* @throws
	 */
	String createOrg(String phone, String schoolName, String introduction, String imageUrl) throws MException;
	void register(List<RegInfoEntity> regInfos, RegScene scene) throws MException;
	void register(RegInfoEntity regInfo, RegScene scene) throws MException;
	void addMoosAdmin(String orgId) throws MException;
	ProfileInfoEntity getSuperAdmin(String orgId) throws MException;
	Map<String, ProfileInfoEntity> getSuperAdmins(List<String> orgIds) throws MException;
	ProfileInfoEntity getProfileInfo(String profileId) throws MException;
	ProfileInfoEntity getProfileInfoOfMobile(String mobile) throws MException;
	Map<String, ProfileInfoEntity> getProfileInfoMapOfMobile(List<String> mobiles) throws MException;
	Map<String, ProfileInfoEntity> getProfileInfoMap(List<String> profileIds) throws MException;
	Map<String, DeptInfoEntity> getDeptInfoMap(List<String> deptIds) throws MException;
	List<String> getDeptIdList(String profileId) throws MException;
	Set<String> getPathDeptIdList(List<String> deptIds) throws MException;
	//获取用户的管理员身份
//	List<OrgAdminEntity> getOrgAdmins(String profileId) throws MException;
	//获取临时登录码
	String getLoginCode(String mobile)throws MException;
	//获取验证码
	void getVC(String mobile)throws MException;
	//验证验证码
	boolean confirmVC(String mobile, String vc)throws MException;
	void confirmVCWithException(String mobile, String vc)throws MException;

	//绑定手机号
	void bindMobile(String profileId, String mobile)throws MException;
	//发送微信绑定手机短信
	void sendWeixinRegSMS(String mobile)throws MException;

	void modifyProfile(JSONObject pObj) throws MException;

	/**
	 * 获取用户所属的组织id
	 * @param profileId
	 * @return
	 * @throws MException
	 */
	List<OrgInfoEntity> getOrgsByProfile(String profileId) throws MException;

	//申请加入组织(验证码)
	void joinOrgWithKey(String profileId, String name, String orgId, String keyCode)throws MException;

	//申请加入组织(无验证码)
	String applyJoinOrg(String profileId, String name, String orgId)throws MException;
	//审核通过
	void applyJoinOrgAccept(String memberApplyId)throws MException;
	//审核未通过
	void applyJoinOrgRefuse(String memberApplyId)throws MException;
	
//	void getApplyJoinOrgInfo(ApplyJoinSchoolInfoEntity entity)throws MException;
	
	OrgInfoEntity getOrgInfo(String orgId) throws MException;
	//获取组织信息
	List<OrgInfoEntity> getOrgInfos(List<String> orgIds) throws MException;
	//获取成员申请信息
//	List<OrgMemberApplyEntity> getMemberApplys(String profileId) throws MException;
	//获取成员申请信息,状态为等待认证
//	Map<String,OrgMemberApplyEntity> getMemberApplysNoDealed(String profileId) throws MException;
	
	//获取组织管理员信息
//	List<OrgAdminEntity> getOrgAdminsOfOrg(String orgId) throws MException;
	//获取组织成员信息
//	List<OrgMemberEntity> getOrgMembersOfProfile(String profileId) throws MException;
	

	Map<String, String> getActivedCount(String startTime, String endTime) throws MException;
}

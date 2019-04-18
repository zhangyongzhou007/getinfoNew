package com.QuestionBank.service.impl;

import  com.QuestionBank.entity.RegInfoEntity.RegClassInfoEntity;
import com.QuestionBank.entity.*;
import com.QuestionBank.entity.e.RegScene;
import com.QuestionBank.exception.MException;
import com.QuestionBank.service.IGroupService;
import com.QuestionBank.utils.GroupRequest;
import com.QuestionBank.utils.StringUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xmpp.packet.IQ;

import java.util.*;

@Service("groupService")
public class GroupServiceImpl implements IGroupService {
	private static final Logger log = LoggerFactory.getLogger(GroupServiceImpl.class);

	@Override
	public String findOrgIdByPhone(String phone) throws MException {
		
		GroupRequest req = new GroupRequest("org","get-org-super-admin-by-mobile");
		req.setColumns("mobile");
		req.addRow(phone);
		req.setFilters("org-id","name","type");
		List<Map<String, String>> result = req.send();
		if (result.size() == 0){
			return null;
		}
		return result.get(0).get("org-id");
	}

	@Override
	public String createOrg(String phone, String schoolName, String introduction, String imageUrl) throws MException {
		return null;
	}

	
/*	@Override
	public String createOrg(String phone, String schoolName,
			String introduction, String imageUrl) throws MException {
		//获取profileId
		ProfileInfoEntity profileInfo = getProfileInfoOfMobile(phone);
		if (profileInfo == null || profileInfo.getInvalid().equals("1")){
//			HashMap<String,String> pMap = new HashMap<String, String>();
//			pMap.put(phone, schoolName);
			RegInfoEntity regInfo = new RegInfoEntity();
			regInfo.setMobile(phone);
			regInfo.setName(schoolName);
			regInfo.setSchoolName(schoolName);
			List<RegInfoEntity> regInfos = new ArrayList<RegInfoEntity>();
			regInfos.add(regInfo);
			register(regInfos,RegScene.OpCreateSchool);
			profileInfo = getProfileInfoOfMobile(phone);
		}
		if (profileInfo == null || profileInfo.getInvalid().equals("1")){
			return null;
		}
		//建组织
		GroupRequest req = new GroupRequest(IQ.Type.set,"org","add-org-join");
		req.setUser(profileInfo.getProfileId());
		req.setColumns("name","abbr-name","description","logo","type","contact-tel");
		req.addRow(schoolName,schoolName,introduction,imageUrl,"6",phone);
		List<Map<String, String>> result = req.send();
		if (result.size() == 0){
			return null;
		}
		String orgId = result.get(0).get("org-id");
		int jjj =0;
		//建角色
		addMoosAdmin(orgId);
		return orgId;
	}
*/
	
	@Override
	public ProfileInfoEntity getProfileInfo(String profileId) throws MException 
	{
		Date beginTime = new Date();
		List<String> profileIds = new ArrayList<String>();
		profileIds.add(profileId);
		Map<String, ProfileInfoEntity> map = getProfileInfoMap(profileIds);
		
		Date endTime = new Date();
		log.info("groupService.getProfileInfo.cost "+String.valueOf(endTime.getTime()-beginTime.getTime()));
		return map.get(profileId);
	}

	
	@Override
	public Map<String, ProfileInfoEntity> getProfileInfoMapOfMobile(List<String> mobiles)
			throws MException {
		if (mobiles.size() == 0){
			return new HashMap<String, ProfileInfoEntity>();
		}
		GroupRequest req = new GroupRequest("profile","get-profile-mobile");
		req.setColumns("mobile");
		//req.setFilters("name","profile-id","mobile","avatar","actived", "password");
		req.addRowsOfOne(mobiles);
		List<Map<String, String>> result = req.send();
		System.out.println("result:"+result.isEmpty());
			if (result.size() == 0){
			return new HashMap<String, ProfileInfoEntity>();

		}

		Map<String, ProfileInfoEntity> map = ProfileInfoEntity.parseToMobileMap(result);
		return map;
		
	}
	
	@Override
	public ProfileInfoEntity getProfileInfoOfMobile(String mobile)
			throws MException {
		List<String> mobiles = new ArrayList<String>();
		mobiles.add(mobile);
		Map<String, ProfileInfoEntity> map = getProfileInfoMapOfMobile(mobiles);
		if (!map.containsKey(mobile)){
			return null;
		}
		return map.get(mobile);
	}

	
	@Override
	public Map<String, ProfileInfoEntity> getProfileInfoMap(List<String> profileIds)
			throws MException {
		
		GroupRequest req = new GroupRequest("profile","get-profile");
		req.setColumns("profile-id");
		//req.setFilters("name","profile-id","mobile","avatar","actived");
		req.addRowsOfOne(profileIds);
		List<Map<String, String>> result = req.send();
		if (result.size() == 0){
			return new HashMap<String, ProfileInfoEntity>();
		}
		Map<String, ProfileInfoEntity> map = ProfileInfoEntity.parseToMap(result);
		return map;
	}


	@Override
	public void register(List<RegInfoEntity> regInfos, RegScene scene) throws MException {
		if (regInfos.size() == 0){
			return;
		}
		GroupRequest req = new GroupRequest(IQ.Type.set,"profile","register-QuestionBank");
		
		if (scene == RegScene.DefaultWithPassword){
			//加密码
			req.setColumns("mobile","name","actived","improved","sms","type","avatar","sex","password","pwd-type");
		}
		else{
			req.setColumns("mobile","name","actived","improved","sms","type","avatar","sex");
		}
		
		for(int i=0;i<regInfos.size();i++){
			RegInfoEntity info = regInfos.get(i);
			
			ActionParameter action = null;
			
			List<RegClassInfoEntity> cinfos = info.getRegClassInfos();
			
			//运营后台为用户创建慕校
			if (scene == RegScene.OpCreateSchool){
				String schoolName = StringUtils.null2String(info.getSchoolName());
				action = new ActionParameter("3000007");
				action.put("org-name", schoolName);
			}
			//主讲人注册不发短信
			else if (scene == RegScene.Speaker || scene == RegScene.Weixin){
				action = ActionParameter.none;
			}
			else if (scene == RegScene.AddMember || scene == RegScene.ClassMember){
//				if (cinfos.size() == 0){
//					throw MException.create500("class is not found as register QuestionBank");
//				}
//				RegClassInfoEntity cinfo = cinfos.get(0);
				action = new ActionParameter("3000006");
				action.put("org-name", info.getSchoolName());
//				action.put("class-name", cinfo.getClassName());
//				action.put("adviser-name", cinfo.getAdviserClassMemberName());
			}
			else{
				action = new ActionParameter("3000001");
			}
			
			String type = "0";
			String improved = "0";
			if (scene == RegScene.Weixin){
				type = "4";
				improved = "1";
			}
			if (scene == RegScene.DefaultWithPassword){
				//加密码
				req.addRow(info.getMobile(),info.getName(),"0",improved,action.toString(),type,info.getAvatar(),info.getSex(),info.getPassword(),"raw");
			}
			else{
				req.addRow(info.getMobile(),info.getName(),"0",improved,action.toString(),type,info.getAvatar(),info.getSex());	
			}
			
		}
		try{
			req.send();
		} catch(MException e){
			if (e.getInfo().indexOf("0070201") > 0){ //该用户已注册
				return;
			}
			throw e;
		}
	}


	@Override
	public Map<String, DeptInfoEntity> getDeptInfoMap(List<String> deptIds)
			throws MException {
		GroupRequest req = new GroupRequest("org","get-dept");
		req.setColumns("dept-id");
		req.setFilters("dept-id","name","path-name");
		req.addRowsOfOne(deptIds);
		List<Map<String, String>> result = req.send();
		if (result.size() == 0){
			return new HashMap<String, DeptInfoEntity>();
		}
		Map<String, DeptInfoEntity> map = DeptInfoEntity.parseToMap(result);
		return map;
	}
	
/*
	@Override
	public void addMoosAdmin(String orgId) throws MException {
		GroupRequest req = new GroupRequest(IQ.Type.set,"org","add-role-school");
		req.setColumns("org-id");
		req.addRow(orgId);
		try{
			req.send();
		}
		catch(MException ex){
			String info = ex.getInfo();
			if (info.indexOf("0070730") >= 0){
			}
			else{
				throw ex;
			}
		}
	}
	*/



	public void test() throws MException{

	}


	@Override
	public List<String> getDeptIdList(String profileId) throws MException {
		GroupRequest req = new GroupRequest("org","get-member-dept-profile");
		req.setColumns("profile-id");
		req.addRow(profileId);
		req.setFilters("dept-id");
		List<Map<String, String>> result = req.send();
		if (result.size() == 0){
			return null;
		}
		List<String> list = new ArrayList<String>();
		for(Map<String, String> map : result) {
			list.add(map.get("dept-id"));
		}
		
		return list;
	}


	@Override
	public Set<String> getPathDeptIdList(List<String> deptIds) throws MException {
		
		GroupRequest req = new GroupRequest("org","get-dept");
		req.setColumns("dept-id");
		req.addRowsOfOne(deptIds);
		req.setFilters("path");
		List<Map<String, String>> result = req.send();
		if (result.size() == 0){
			return null;
		}
		Set<String> set = new HashSet<String>();
		for(Map<String, String> map : result) {
			set.add(map.get("path"));
		}
		
		return set;
	}


	@Override
	public ProfileInfoEntity getSuperAdmin(String orgId) throws MException {
		GroupRequest req = new GroupRequest("org","get-super-admin");
		req.setColumns("org-id");
		req.addRow(orgId);
		List<Map<String, String>> result = req.send();
		if (result.size() == 0){
			return null;
		}
		
		Map<String, ProfileInfoEntity> map = ProfileInfoEntity.parseToMap(result);
		ProfileInfoEntity profileInfo = null;
		for(ProfileInfoEntity p :map.values()){
			profileInfo = p;
			break;
		}
		return profileInfo;
	}
	

	@Override
	public Map<String, ProfileInfoEntity> getSuperAdmins(List<String> orgIds) throws MException {
		GroupRequest req = new GroupRequest("org","get-super-admin");
		req.setColumns("org-id");
		req.addRowsOfOne(orgIds);
		List<Map<String, String>> result = req.send();
		if (result.size() == 0){
			return null;
		}
		Map<String, ProfileInfoEntity> map = ProfileInfoEntity.parseToOrgAdminMap(result);
		return map;
	}


	@Override
	public List<OrgInfoEntity> getOrgsByProfile(String profileId) throws MException
	{
		GroupRequest req = new GroupRequest("org","get-org-info-profile");
		req.setColumns("profile-id");
		req.addRow(profileId);
		req.setFilters("org-id", "name");
		List<Map<String, String>> result = req.send();
		if (result.size() == 0){
			return null;
		}
		
		List<OrgInfoEntity> org = OrgInfoEntity.parserToOrgs(result);
		
		return org;
	}

/*
	@Override
	public List<OrgAdminEntity> getOrgAdmins(String profileId) throws MException {
		List<OrgAdminEntity> results = new ArrayList<OrgAdminEntity>();
		GroupRequest req = new GroupRequest("org","get-role-profile");
		req.setColumns("profile-id");
		req.setVer("2");
		req.addRow(profileId);
		req.setFilters("org-id", "profile-id", "role-id", "name", "type");
		List<Map<String, String>> result = req.send();
		if (result.size() == 0){
			return results;
		}
		List<OrgAdminEntity> orgAdmins = OrgAdminEntity.parserToOrgAdmins(result);
		
		return orgAdmins;
		
	}
	*/

	private List<String> getValues(List<Map<String, String>> result,String key,boolean merge){
		List<String> list = new ArrayList<String>();
		for(Map<String, String> map : result) {
			String value = map.get(key);
			if (merge){
				if (!list.contains(value)){
					list.add(value);
				}
			}
			else{
				list.add(value);
			}
		}
		return list;
	}
	
	private String getValue(List<Map<String, String>> result,String key,String defaultValue){
		List<String> list = new ArrayList<String>();
		for(Map<String, String> map : result) {
			String value = map.get(key);
			list.add(value);
			break;
		}
		if (list.size() == 0){
			return defaultValue;
		}
		return list.get(0);
	}

	@Override
	public String getLoginCode(String mobile) throws MException{
		GroupRequest req = new GroupRequest("sys","get-login-code");
		req.setColumns("mobile");
		req.addRow(mobile);
		List<Map<String, String>> result = req.send();
		String loginCode = getValue(result,"login-code","");
		return loginCode;
	}


	@Override
	public void getVC(String mobile)  throws MException{
		GroupRequest req = new GroupRequest("sys","get-vc");
		req.setColumns("mobile");
		req.addRow(mobile);
		req.send();
	}


	@Override
	public boolean confirmVC(String mobile, String vc) throws MException {
		GroupRequest req = new GroupRequest("sys","confirm-vc");
		req.setColumns("mobile","vc");
		req.addRow(mobile,vc);
		try{
			req.send();
		}
		catch(Exception ex){
			return false;
		}
		return true;
	}
	
	
	@Override
	public void confirmVCWithException(String mobile, String vc) throws MException {
		GroupRequest req = new GroupRequest("sys","confirm-vc");
		req.setColumns("mobile","vc");
		req.addRow(mobile,vc);
		req.send();
	}


	@Override
	public void bindMobile(String profileId, String mobile) throws MException {
		GroupRequest req = new GroupRequest(IQ.Type.set,"profile","modify-profile");
		req.setUser(profileId);
		req.setSer("bind-mobile");
		req.setColumns("profile-id","mobile","type");
		req.addRow(profileId,mobile,"0");
		req.send();
	}

	@Override
	public void sendWeixinRegSMS(String mobile) throws MException {

	}

/*
	@Override
	public void sendWeixinRegSMS(String mobile) throws MException {
		// TODO Auto-generated method stub
		ActionParameter action = new ActionParameter("3000008");
		GroupRequest req = new GroupRequest("profile","send-reg-sms");
		req.setColumns("mobile","sms");
		req.addRow(mobile,action.toString());
		try{
			req.send();
		}
		catch(Exception ex){
		}
	}
*/

	@Override
	public void register(RegInfoEntity regInfo, RegScene scene)
			throws MException {
		List<RegInfoEntity> infos = new ArrayList<RegInfoEntity>();
		infos.add(regInfo);
		register(infos,scene);
		
	}

	@Override
	public void addMoosAdmin(String orgId) throws MException {

	}


	@Override
	public void joinOrgWithKey(String profileId,String name,String orgId,String keyCode) throws MException{
		GroupRequest req = new GroupRequest(IQ.Type.set,"org","add-member-join");
		req.setSer("add-member-key");
		req.setColumns("key-code","org-id","profile-id","name");
		req.addRow(keyCode,orgId,profileId,name);
		/*try{
			req.send();
		}catch(Exception ex){
			log.error("send apply failed",ex);
		}*/
		req.send();
	}

	@Override
	public String applyJoinOrg(String profileId,String name,String orgId) throws MException{
		GroupRequest req = new GroupRequest(IQ.Type.set,"org","add-member-apply");
		req.setSer("add-member-no-key");
		req.setColumns("name","org-id","profile-id","QuestionBank-name");
		req.addRow(name,orgId,profileId,profileId);
		List<Map<String, String>> result = null;
		/*try{
			result = req.send();
		}catch(Exception ex){
			log.error("send apply failed",ex);
			return "";
		}
		if (result.size() == 0){
			return "";
		}*/
		result = req.send();
		String maId = result.get(0).get("member-apply-id");
		return maId;
	}

	

	@Override
	public void applyJoinOrgAccept(String memberApplyId) throws MException{
		GroupRequest req = new GroupRequest(IQ.Type.set,"org","modify-member-join");
		req.setSer("agree-member");
		req.setColumns("member-apply-id");
		req.addRow(memberApplyId);
		req.send();
	}


	


	@Override
	public void applyJoinOrgRefuse(String memberApplyId) throws MException{
		GroupRequest req = new GroupRequest(IQ.Type.set,"org","modify-member-apply");
		req.setSer("reject-member");
		req.setColumns("member-apply-id");
		req.addRow(memberApplyId);
		req.send();
	}



	
	@Override
	public OrgInfoEntity getOrgInfo(String orgId) throws MException {
		ArrayList<String> orgIds = new ArrayList<String>();
		orgIds.add(orgId);
		List<OrgInfoEntity> orgInfos = getOrgInfos(orgIds);
		if (orgInfos.size() == 0){
			return null;
		}
		return orgInfos.get(0);
	}


	@Override
	public List<OrgInfoEntity> getOrgInfos(List<String> orgIds)
			throws MException {
		GroupRequest req = new GroupRequest("org","get-org");
		req.setColumns("org-id");
		req.addRowsOfOne(orgIds);
		List<Map<String, String>> result = req.send();
		if (result.size() == 0){
			return new ArrayList<OrgInfoEntity>();
		}
		
		List<OrgInfoEntity> org = OrgInfoEntity.parserToOrgs(result);
		
		return org;
	}

/*
	@Override
	public List<OrgMemberApplyEntity> getMemberApplys(String profileId)
			throws MException {
		GroupRequest req = new GroupRequest("org","get-member-apply-profile");
		req.setColumns("profile-id");
		req.addRow(profileId);
		List<Map<String, String>> result = req.send();
		if (result.size() == 0){
			return new ArrayList<OrgMemberApplyEntity>();
		}
		List<OrgMemberApplyEntity> applys = OrgMemberApplyEntity.parse(result);
		return applys;
	}

/*
	@Override
	public List<OrgAdminEntity> getOrgAdminsOfOrg(String orgId)
			throws MException {
		List<OrgAdminEntity> results = new ArrayList<OrgAdminEntity>();
		GroupRequest req = new GroupRequest("org","get-admin-by-org");
		req.setColumns("org-id");
		req.addRow(orgId);
		req.setFilters("org-id", "profile-id", "role-id", "name", "type");
		List<Map<String, String>> result = req.send();
		if (result.size() == 0){
			return results;
		}
		List<OrgAdminEntity> orgAdmins = OrgAdminEntity.parserToOrgAdmins(result);
		
		return orgAdmins;
	}

	/*
        @Override
        public List<OrgMemberEntity> getOrgMembersOfProfile(String profileId)
                throws MException {
            GroupRequest req = new GroupRequest("org","get-member-by-profile");
            req.setColumns("profile-id");
            req.addRow(profileId);
            List<Map<String, String>> result = req.send();
            if (result.size() == 0){
                return new ArrayList<OrgMemberEntity>();
            }
            List<OrgMemberEntity> members = OrgMemberEntity.parse(result);
            return members;
        }
        */

    /*
        @Override
        public Map<String,OrgMemberApplyEntity> getMemberApplysNoDealed(String profileId) throws MException {
            List<OrgMemberApplyEntity> applyEntitys = getMemberApplys(profileId);
            Map<String,OrgMemberApplyEntity> map = new HashMap<String,OrgMemberApplyEntity>();
            for(OrgMemberApplyEntity apply : applyEntitys){
                if("0".equals(apply.getJoinStatus())){
                    map.put(apply.getOrgId(), apply);
                }
            }
            return map;
        }

        */
	@Override
	public Map<String,String> getActivedCount(String startTime,String endTime) throws MException {
		GroupRequest req = new GroupRequest("profile","get-actived-info");
		req.setColumns("start-time","end-time");
		req.addRow(startTime,endTime);
		Map<String,String> map = new HashMap<String, String>();
		map.put("minActivedTime", "");
		map.put("maxActivedTime", "");
		map.put("count", "0");
		List<Map<String, String>> result = req.send();
		if (result.size() == 0){
			return map;
		}
		Map<String, String> m = result.get(0);
		map.put("minActivedTime", m.get("min-actived-time"));
		map.put("maxActivedTime", m.get("max-actived-time"));
		map.put("count", m.get("c"));
		return map;
	}


	@Override
	public void modifyProfile(JSONObject pObj)
			throws MException {
		String profileId = pObj.getString("profileId");
		GroupRequest req = new GroupRequest(IQ.Type.set,"profile","modify-profile");
		req.setUser(profileId);
		req.setFrom(profileId + "@y/moos");
		List<String> columnNames = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		
		if (pObj.containsKey("improved")){
			req.setSer("improve-profile");
		}
		if (pObj.containsKey("oldPassword")){
			columnNames.add("old-password");
			values.add(pObj.getString("oldPassword"));
		}
		if (pObj.containsKey("password")){
			columnNames.add("password");
			values.add(pObj.getString("password"));
		}
		if (pObj.containsKey("profileId")){
			columnNames.add("profile-id");
			values.add(pObj.getString("profileId"));
		}
		if (pObj.containsKey("name")){
			columnNames.add("name");
			values.add(pObj.getString("name"));
		}
		if (pObj.containsKey("avatar")){
			columnNames.add("avatar");
			values.add(pObj.getString("avatar"));
		}
		
		req.setColumns(columnNames.toArray(new String[0]));
		req.addRow(values.toArray(new String[0]));
		req.send();
		
	}



//	--------------------------------------request[org.get-org-super-admin-by-mobile]--------------------------------------
//	<iq type="get" id="Vw0qW-5" from="100000001@y/iOS" to="group.y">
//	  <operate xmlns="jabber:iq:group" app="org" data="10000011000001" oper="get-org-super-admin-by-mobile">
//	    <item>
//	      <table>
//	        <h>mobile</h>
//	        <r>1863312617</r>
//	      </table>
//	    </item>
//	  </operate>
//	</iq>
//	--------------------------------------response[org.get-org-super-admin-by-mobile]--------------------------------------
//	<iq type="result" id="Vw0qW-5" from="group@1.y/1" to="100000001@y/iOS">
//	  <operate xmlns="jabber:iq:group" status="200" desp="成功" app="org" code="0000000" data="10000011000001" oper="get-org-super-admin-by-mobile" text="OK">
//	    <item status="200" desp="成功" code="0000000" text="OK">
//	      <table>
//	        <h>org-id,name,abbr-name,type</h>
//	        <r>1000308,org27,org27,0</r>
//	      </table>
//	    </item>
//	  </operate>
//	</iq>
//	--------------------------------------response[org.get-org-super-admin-by-mobile]--------------------------------------
//	<iq type="result" id="9b6k7-3" from="group@1.y/1" to="100000001@y/iOS">
//	  <operate xmlns="jabber:iq:group" status="200" desp="成功" app="org" code="0000000" data="10000011000001" oper="get-org-super-admin-by-mobile" text="OK">
//	    <item status="404" desp="未查找到相关内容" code="0000404" text="Not Found"/>
//	  </operate>
//	</iq>
//	String orgId = findOrgIdByPhone("18700000004");
	
//	--------------------------------------request[profile.get-profile-mobile]--------------------------------------
//	<iq type="get" id="9b6k7-17" from="message@1.y/2" to="group@1.y">
//	  <operate xmlns="jabber:iq:group" app="profile" oper="get-profile-mobile">
//	    <item>
//	      <table>
//	        <h>mobile</h>
//	        <r>18420007150</r>
//	        <r>18420007151</r>
//	      </table>
//	    </item>
//	  </operate>
//	</iq>
//	--------------------------------------response[profile.get-profile-mobile]--------------------------------------
//	<iq type="result" id="9b6k7-17" from="group@1.y/1" to="message@1.y/2">
//	  <operate xmlns="jabber:iq:group" status="200" desp="成功" app="profile" code="0000000" oper="get-profile-mobile" text="OK">
//	    <item status="200" desp="成功" code="0000000" etag="2016-07-30 13:16:14.000" text="OK">
//	      <table>
//	        <h>profile-id,QuestionBank-name,py-name,pyjc-name,password,avatar,name,email,sex,mobile,birthday,tel,address,hometown,school,qr-code,actived,logined,improved,type,invalid,edu-id,orgs,terminal-type,invite-time,s-mobile,in-server,on-server,modify-time,create-time</h>
//	        <r>100132839,100132839,CeShi18407409,cs18407409,111111,,测试18407140,184cs@7303.com,1,18420007150,2016-07-30 13:03:45.000,18420007150,广东省@/深圳市@/南山区@/南山科技园高新中一道长圆新材10栋7148楼,,,,0,0,1,0,0,,1000096,,1970-01-01 00:00:00.000,,group@1.y/3,group@1.y/3,2016-07-30 13:16:14.000,2016-07-30 13:12:01.000</r>
//	        <r>100132840,100132840,CeShi18407409,cs18407409,111111,,测试18407141,184cs@7304.com,1,18420007151,2016-07-30 13:03:45.000,18420007151,广东省@/深圳市@/南山区@/南山科技园高新中一道长圆新材10栋7149楼,,,,0,0,1,0,0,,1000096,,1970-01-01 00:00:00.000,,group@1.y/3,group@1.y/3,2016-07-30 13:16:14.000,2016-07-30 13:12:01.000</r>
//	      </table>
//	    </item>
//	  </operate>
//	</iq>
//	List<String> profileIds = new ArrayList<String>();
//	profileIds.add("100000001");
//	profileIds.add("100000002");
//	getProfileInfoMap(profileIds);
	
	
//	--------------------------------------request[org.add-org-join]--------------------------------------
//	<iq type="set" id="9b6k7-5" from="100801438@y/web" to="group.y">
//	  <operate xmlns="jabber:iq:group" app="org" ser="add-org-join" oper="add-org-join">
//	    <item>
//	      <table>
//	        <h>name,abbr-name,description,logo</h>
//	        <r>org508,org508,test,111</r>
//	      </table>
//	    </item>
//	  </operate>
//	</iq>
//	<iq type="result" id="9b6k7-5" from="group@1.y/1" to="100801438@y/web">
//	  <operate xmlns="jabber:iq:group" status="200" desp="成功" app="org" ser="add-org-join" code="0000000" oper="add-org-join" text="OK">
//	    <item status="200" desp="成功" code="0000000" text="OK">
//	      <table>
//	        <h>org-id</h>
//	        <r>1005916</r>
//	      </table>
//	      <table>
//	        <h>dept-id</h>
//	        <r>10059161000001</r>
//	      </table>
//	      <table>
//	        <h>dept-id</h>
//	        <r>10059161000002</r>
//	        <r>10059161000003</r>
//	        <r>10059161000004</r>
//	      </table>
//	      <table>
//	        <h>member-id</h>
//	        <r>10059161000001</r>
//	      </table>
//	      <table>
//	        <h>member-dept-id</h>
//	        <r>10059161000001</r>
//	      </table>
//	      <table>
//	        <h>member-role-id</h>
//	        <r>10059161000001</r>
//	      </table>
//	      <table>
//	        <h>role-right-id</h>
//	        <r>10059161000001</r>
//	        <r>10059161000002</r>
//	        <r>10059161000003</r>
//	        <r>10059161000004</r>
//	        <r>10059161000005</r>
//	        <r>10059161000006</r>
//	        <r>10059161000007</r>
//	        <r>10059161000008</r>
//	        <r>10059161000009</r>
//...
//	      </table>
//	    </item>
//	  </operate>
//	</iq>
//	<iq type="set" id="8aDOH-9" from="sys@test1/pc_win" to="group.y">
//	  <operate xmlns="jabber:iq:group" app="org" oper="add-role-school">
//	    <item>
//	      <table>
//	        <h>org-id</h>
//	        <r>1000007</r>
//	      </table>
//	    </item>
//	  </operate>
//	</iq>
//	<iq type="result" id="8aDOH-9" from="group@1.y/1" to="sys@test1/pc_win">
//	  <operate xmlns="jabber:iq:group" status="200" desp="成功" app="org" code="0000000" oper="add-role-school" text="OK">
//	    <item status="200" desp="成功" code="0000000" text="OK">
//	      <table>
//	        <h>role-id</h>
//	        <r>10000071000003</r>
//	      </table>
//	      <table>
//	        <h>role-right-id</h>
//	        <r>10000071000024</r>
//	        <r>10000071000025</r>
//	        <r>10000071000026</r>
//	      </table>
//	    </item>
//	  </operate>
//	</iq>
//	createOrg("13600000009","org009","org009","http://xxxx");
	
//	--------------------------------------request[profile.register-QuestionBank]--------------------------------------
//	<iq type="set" id="9b6k7-7" from="sys@test1/pc_win" to="group.y">
//	  <operate xmlns="jabber:iq:group" app="profile" oper="register-QuestionBank">
//	    <item>
//	      <table>
//	        <h>mobile,name</h>
//	        <r>15601626342,xxx</r>
//	      </table>
//	    </item>
//	  </operate>
//	</iq>
//	--------------------------------------response[profile.register-QuestionBank]--------------------------------------
//	<iq type="result" id="9b6k7-7" from="group@1.y/1" to="sys@test1/pc_win">
//	  <operate xmlns="jabber:iq:group" status="200" desp="成功" app="profile" code="0000000" oper="register-QuestionBank" text="OK">
//	    <item status="200" desp="成功" code="0000000" text="OK">
//	      <table>
//	        <h>profile-id</h>
//	        <r>100825269</r>
//	      </table>
//	    </item>
//	  </operate>
//	</iq>
//	HashMap<String,String> mobileNameMap = new HashMap<String, String>();
//	mobileNameMap.put("13400000001", "13401");
//	mobileNameMap.put("13400000002", "13402");
//	register(mobileNameMap);
	
//	List<String> deptIds = new ArrayList<String>();
//	deptIds.add("20065181000093");
//	deptIds.add("20065181000100");
//	getDeptInfoMap(deptIds);
	
//	<iq type="set" id="DJpRi-217" from="100804872@y/web" to="group.y">
//	  <operate xmlns="jabber:iq:group" app="org" ser="reject-member" oper="modify-member-apply" QuestionBank="100804872">
//	    <item>
//	      <table>
//	        <h>member-apply-id</h>
//	        <r>10004601000003</r>
//	      </table>
//	    </item>
//	  </operate>
//	</iq>^M
//	<iq type="result" id="DJpRi-217" from="group@1.y/2" to="100804872@y/web">
//	  <operate xmlns="jabber:iq:group" status="200" desp="成功" app="org" ser="reject-member" code="0000000" oper="modify-member-apply" text="OK">
//	    <item status="200" desp="成功" code="0000000" text="OK"/>
//	  </operate>
//	</iq>	

	
}

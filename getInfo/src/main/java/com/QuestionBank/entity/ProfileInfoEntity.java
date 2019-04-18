package com.QuestionBank.entity;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileInfoEntity {
	private String profileId = "";
	public  static  final  String PROFILEID_ID="profileId";
	private String avatar = "";
	private String name = "";
	private String email = "";
	private String sex = "";
	private String mobile = "";
	private String birthday = "";
	private String tel = "";
	private String address = "";
	private String password = "";
	private String actived = "";
	private String improved = "";
	private String type = "";
	private String eduId = "";
	private String hometown = "";
	private String school = "";
	private String tokenIosType = "";
	private String tokenIosPush = "";
	private String logined = "";
	private String qrCode = "";
	private String pyName = "";
	private String pyjcName = "";
	private String invalid = "";
	private String orgs = "";
	private String terminalType = "";
	private String inviteTime = "";
	private String sMobile = "";
	private String modifyFlag = "";
	private String modifyTime = "";
	private String createTime = "";
	public  static  final  String CREATE_TIME="createTime";
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getActived() {
		return actived;
	}
	public void setActived(String actived) {
		this.actived = actived;
	}
	public String getImproved() {
		return improved;
	}
	public void setImproved(String improved) {
		this.improved = improved;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEduId() {
		return eduId;
	}
	public void setEduId(String eduId) {
		this.eduId = eduId;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getTokenIosType() {
		return tokenIosType;
	}
	public void setTokenIosType(String tokenIosType) {
		this.tokenIosType = tokenIosType;
	}
	public String getTokenIosPush() {
		return tokenIosPush;
	}
	public void setTokenIosPush(String tokenIosPush) {
		this.tokenIosPush = tokenIosPush;
	}
	public String getLogined() {
		return logined;
	}
	public void setLogined(String logined) {
		this.logined = logined;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getPyName() {
		return pyName;
	}
	public void setPyName(String pyName) {
		this.pyName = pyName;
	}
	public String getPyjcName() {
		return pyjcName;
	}
	public void setPyjcName(String pyjcName) {
		this.pyjcName = pyjcName;
	}
	public String getInvalid() {
		return invalid;
	}
	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}
	public String getOrgs() {
		return orgs;
	}
	public void setOrgs(String orgs) {
		this.orgs = orgs;
	}
	public String getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}
	public String getInviteTime() {
		return inviteTime;
	}
	public void setInviteTime(String inviteTime) {
		this.inviteTime = inviteTime;
	}
	public String getsMobile() {
		return sMobile;
	}
	public void setsMobile(String sMobile) {
		this.sMobile = sMobile;
	}
	public String getModifyFlag() {
		return modifyFlag;
	}
	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	static public HashMap<String,String> getProfileMobileMap(List<ProfileInfoEntity> entitys){
		HashMap<String,String> map = new HashMap<String, String>();
		for (int i=0;i<entitys.size();i++){
			ProfileInfoEntity e = entitys.get(i);
			map.put(e.getProfileId(), e.getMobile());
		}
		return map;
	}
	static public HashMap<String,String> getMobileProfileMap(List<ProfileInfoEntity> entitys){
		HashMap<String,String> map = new HashMap<String, String>();
		for (int i=0;i<entitys.size();i++){
			ProfileInfoEntity e = entitys.get(i);
			
			map.put(e.getMobile(),e.getProfileId());
		}
		return map;
	}
	
	
	public static Map<String, ProfileInfoEntity> parseToMap(List<Map<String, String>> list)
	{
		Map<String, ProfileInfoEntity> profileMap = new HashMap<String, ProfileInfoEntity>();
		for (Map<String, String> map : list)
		{
			ProfileInfoEntity user = new ProfileInfoEntity();
			if (user.getInvalid().equals("1")){
				continue;
			}
			user.setName(map.get("name"));
			user.setProfileId(map.get("profile-id"));
			user.setMobile(map.get("mobile"));
			user.setPassword(map.get("password"));
			user.setAvatar(map.get("avatar"));
			user.setActived(map.get("actived"));
			user.setOrgs(map.get("orgs"));
			user.setInvalid(map.get("invalid"));
			user.setType(map.get("type"));
			user.setImproved(map.get("improved"));
			
			profileMap.put(user.getProfileId(), user);
		}
		return profileMap;
	}
	
	public static Map<String, ProfileInfoEntity> parseToOrgAdminMap(List<Map<String, String>> list)
	{
		Map<String, ProfileInfoEntity> profileMap = new HashMap<String, ProfileInfoEntity>();
		for (Map<String, String> map : list)
		{
			ProfileInfoEntity user = new ProfileInfoEntity();
			if (user.getInvalid().equals("1")){
				continue;
			}
			user.setName(map.get("name"));
			user.setProfileId(map.get("profile-id"));
			user.setMobile(map.get("mobile"));
			user.setPassword(map.get("password"));
			user.setAvatar(map.get("avatar"));
			user.setActived(map.get("actived"));
			user.setOrgs(map.get("orgs"));
			user.setInvalid(map.get("invalid"));
			user.setType(map.get("type"));
			user.setImproved(map.get("improved"));
			
			profileMap.put(map.get("org-id"), user);
		}
		return profileMap;
	}
	
	public static Map<String, ProfileInfoEntity> parseToMobileMap(List<Map<String, String>> list)
	{
		Map<String, ProfileInfoEntity> profileMap = new HashMap<String, ProfileInfoEntity>();
		for (Map<String, String> map : list)
		{
			ProfileInfoEntity user = new ProfileInfoEntity();
			if (user.getInvalid().equals("1")){
				continue;
			}
			user.setName(map.get("name"));
			user.setProfileId(map.get("profile-id"));
			user.setMobile(map.get("mobile"));
			user.setPassword(map.get("password"));
			user.setAvatar(map.get("avatar"));
			user.setActived(map.get("actived"));
			user.setOrgs(map.get("orgs"));
			user.setInvalid(map.get("invalid"));
			user.setType(map.get("type"));
			user.setImproved(map.get("improved"));
			
			profileMap.put(user.getMobile(), user);
		}
		return profileMap;
	}
	
	public JSONObject toJSONObject() {
		JSONObject obj = JSONObject.fromObject(this);
		return obj;
	}

	@Override
	public String toString() {
		JSONObject obj = this.toJSONObject();
		return obj.toString();
	}
	
}

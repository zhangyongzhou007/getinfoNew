package com.QuestionBank.entity;

import com.QuestionBank.utils.StringUtils;

import java.util.*;

public class ClassMemberEntity {

	String classMemberId = "";
	public static final String CLASS_MEMBER_ID = "classMemberId";
	String schoolId = "";
	public static final String SCHOOL_ID = "schoolId";
	String classId = "";
	public static final String CLASS_ID = "classId";
	String profileId = "";
	public static final String PROFILE_ID = "profileId";
	String memberId = "";
	public static final String MEMBER_ID = "memberId";
	String name = "";
	public static final String NAME = "name";
	String pyName = "";
	public static final String PYNAME = "pyName";
	String pyjcName = "";
	public static final String PYJCNAME = "pyjcName";
	String role = "";
	public static final String ROLE = "role";
	public static final String ROLE_ONE = "班主任";
	public static final String ROLE_TWO = "普通成员";
	
	int actived = 0;
	public static final String ACTIVED = "actived";
	String activedTime = "1970-01-01 00:00:00";
	public static final String ACTIVEDTIME = "activedTime";
	
	long ver = 0;
	public static final String VER = "ver";
	int update = 1;
	public static final String UPDATE = "update";
	private String createTime;
	public static final String CREATE_TIME = "createTime";
	private String modifyTime;
	public static final String MODIFY_TIME = "modifyTime";
	
	private String operProfileId;
	
	
	public String getClassMemberId() {
		return classMemberId;
	}
	public void setClassMemberId(String classMemberId) {
		this.classMemberId = classMemberId;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPyjcName() {
		return pyjcName;
	}
	public void setPyjcName(String pyjcName) {
		this.pyjcName = pyjcName;
	}
	public String getPyName() {
		return pyName;
	}
	public void setPyName(String pyName) {
		this.pyName = pyName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getActived() {
		return actived;
	}
	public void setActived(int actived) {
		this.actived = actived;
	}
	public long getVer() {
		return ver;
	}
	public void setVer(long ver) {
		this.ver = ver;
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

	public String getOperProfileId() {
		return operProfileId;
	}

	public void setOperProfileId(String operProfileId) {
		this.operProfileId = operProfileId;
	}

	public int getUpdate() {
		return update;
	}
	public void setUpdate(int update) {
		this.update = update;
	}
	public String getActivedTime() {
		return activedTime;
	}
	public void setActivedTime(String activedTime) {
		this.activedTime = activedTime;
	}
	public static List<String> getIds(List<ClassMemberEntity> entitys){
		List<String> ids = new ArrayList<String>();
		for (int i=0;i<entitys.size();i++){
			ids.add(entitys.get(i).getClassMemberId());
		}
		return ids;
	}
	public static List<String> getClassIds(List<ClassMemberEntity> entitys,boolean merge){
		List<String> ids = new ArrayList<String>();
		for (int i=0;i<entitys.size();i++){
			String id = entitys.get(i).getClassId();
			if (!merge || !ids.contains(id)){
				ids.add(id);
			}
		}
		return ids;
	}
	public static List<String> getProfileIds(List<ClassMemberEntity> entitys,boolean merge){
		List<String> ids = new ArrayList<String>();
		for (int i=0;i<entitys.size();i++){
			String id = entitys.get(i).getProfileId();
			if (!merge || !ids.contains(id)){
				ids.add(id);
			}
		}
		return ids;
	}
	public static HashSet<String> getClassIdSet(List<ClassMemberEntity> entitys){
		HashSet<String> idSet = new HashSet<String>();
		for (int i=0;i<entitys.size();i++){
			String id = entitys.get(i).getClassId();
			idSet.add(id);
		}
		return idSet;
	}
	
	public static String getHash(List<ClassMemberEntity> entitys){
		List<String> ids = new ArrayList<String>();
		for (int i=0;i<entitys.size();i++){
			ids.add(entitys.get(i).getClassMemberId());
		}
		Collections.sort(ids,new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<ids.size();i++){
			sb.append(ids.get(i));
		}
		String md5 = StringUtils.hash(sb.toString());
		return md5;
	}
	public static String getClassHash(List<ClassMemberEntity> entitys){
		List<String> ids = new ArrayList<String>();
		for (int i=0;i<entitys.size();i++){
			ids.add(entitys.get(i).getClassId());
		}
		Collections.sort(ids,new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<ids.size();i++){
			sb.append(ids.get(i));
		}
		String md5 = StringUtils.hash(sb.toString());
		return md5;
	}
	
	public static String getEtag(List<ClassMemberEntity> entitys){
		long maxVer = 0;
		for (int i=0;i<entitys.size();i++){
			if (entitys.get(i).getVer() > maxVer){
				maxVer = entitys.get(i).getVer();
			}
		}
		return String.valueOf(maxVer);
	}
	
	public static Map<String,ClassMemberEntity> getClassMap(List<ClassMemberEntity> classMembers){
		Map<String,ClassMemberEntity> map = new HashMap<String, ClassMemberEntity>();
		for(int i=0;i<classMembers.size();i++){
			ClassMemberEntity cm = classMembers.get(i);
			map.put(cm.getClassId(), cm);
		}
		return map;
	}
	
	public static Map<String,List<ClassMemberEntity>> getMemberMap(List<ClassMemberEntity> classMembers){
		Map<String,List<ClassMemberEntity>> map = new HashMap<String, List<ClassMemberEntity>>();
		
		for(int i=0;i<classMembers.size();i++){
			ClassMemberEntity cm = classMembers.get(i);
			List<ClassMemberEntity> members = null;
			if (map.containsKey(cm.getMemberId())){
				members = map.get(cm.getMemberId());
			}
			else{
				members = new ArrayList<ClassMemberEntity>();
				map.put(cm.getMemberId(), members);
			}
			members.add(cm);
		}
		return map;
	}
	
	public static Map<String,List<ClassMemberEntity>> getProfileMap(List<ClassMemberEntity> classMembers){
		Map<String,List<ClassMemberEntity>> map = new HashMap<String, List<ClassMemberEntity>>();
		
		for(int i=0;i<classMembers.size();i++){
			ClassMemberEntity cm = classMembers.get(i);
			List<ClassMemberEntity> members = null;
			if (map.containsKey(cm.getProfileId())){
				members = map.get(cm.getProfileId());
			}
			else{
				members = new ArrayList<ClassMemberEntity>();
				map.put(cm.getProfileId(), members);
			}
			members.add(cm);
		}
		return map;
	}
	
//	public static int getUpdate(List<ClassMemberEntity> entitys){
//		for (int i=0;i<entitys.size();i++){
//			ClassMemberEntity entity = entitys.get(i);
//			if (entity.update == -1){
//				return -1;
//			}
//			
//		}
//	}
	
}

package com.QuestionBank.entity;

import java.util.ArrayList;
import java.util.List;

public class RegInfoEntity {
	public static class RegClassInfoEntity {
		String classId = "";
		String className = "";
		String adviserProfileId = "";
		String adviserClassMemberName = "";
		public String getClassId() {
			return classId;
		}
		public void setClassId(String classId) {
			this.classId = classId;
		}
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		public String getAdviserProfileId() {
			return adviserProfileId;
		}
		public void setAdviserProfileId(String adviserProfileId) {
			this.adviserProfileId = adviserProfileId;
		}
		public String getAdviserClassMemberName() {
			return adviserClassMemberName;
		}
		public void setAdviserClassMemberName(String adviserClassMemberName) {
			this.adviserClassMemberName = adviserClassMemberName;
		}
	}
	
	String mobile = "";
	String password = "";
	String name = "";
	String avatar = "";
	String sex = "1";
	String schoolId = "";
	String schoolName = "";
	List<RegClassInfoEntity> regClassInfos = new ArrayList<RegClassInfoEntity>();
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public List<RegClassInfoEntity> getRegClassInfos() {
		return regClassInfos;
	}
	public void setRegClassInfos(List<RegClassInfoEntity> regClassInfos) {
		this.regClassInfos = regClassInfos;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}

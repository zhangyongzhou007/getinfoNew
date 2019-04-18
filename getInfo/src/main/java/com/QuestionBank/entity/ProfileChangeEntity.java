package com.QuestionBank.entity;

import net.sf.json.JSONObject;

public class ProfileChangeEntity {
	private String profileId = "";
	private String avatar = "";
	private String name = "";
	private String mobile = "";
	
	
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

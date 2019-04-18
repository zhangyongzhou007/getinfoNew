package com.QuestionBank.entity;

import java.io.Serializable;

/**
 * 单直播访客量统计
 * @ClassName: LiveStatisUserEntity 
 * @Description: 
 * @author jiangchunlin
 * @date 2018-3-2 下午4:40:45
 */
public class LiveStatisUserEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 118307208671230360L;
	
	/**
	 * 数据统计用户访问ID
	 */
	private String liveStatisUserId;
	public static final String LIVE_STATIS_USER_ID = "liveStatisUserId";
	
	/**
	 * 当天日期
	 */
	private String currDate;
	public static final String CURR_DATE = "currDate";
	
	/**
	 * subjectId
	 */
	private String subjectId;
	public static final String SUBJECT_ID = "subjectId";
	
	/**
	 * profileId
	 */
	private String profileId;
	public static final String PROFILE_ID = "profileId";
	
	/**
	 * 创建时间
	 */
	private String createTime;
	public static final String CREATE_TIME = "createTime";
	
	
	public String getLiveStatisUserId() {
		return liveStatisUserId;
	}
	public String getCurrDate() {
		return currDate;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public String getProfileId() {
		return profileId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setLiveStatisUserId(String liveStatisUserId) {
		this.liveStatisUserId = liveStatisUserId;
	}
	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "LiveStatisUserEntity{" +
				"liveStatisUserId='" + liveStatisUserId + '\'' +
				", currDate='" + currDate + '\'' +
				", subjectId='" + subjectId + '\'' +
				", profileId='" + profileId + '\'' +
				", createTime='" + createTime + '\'' +
				'}';
	}
}

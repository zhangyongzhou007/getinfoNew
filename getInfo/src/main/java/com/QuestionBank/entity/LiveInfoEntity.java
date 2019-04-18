package com.QuestionBank.entity;

import net.sf.json.JSONObject;

import java.io.Serializable;

public class LiveInfoEntity implements Serializable {

    private String subjectId;
    public  static  final  String  SUBJECT_ID= "subjectId";

    private String title;
    public  static  final  String  TITLE= "title";

    private String schoolName;
    public  static  final  String  SCHOOL_NAME= "schoolName";

    private String createTime;
    public  static  final  String CREATE_TIME= "createTime";

    private String liveId;
    public  static  final  String LIVE_ID = "liveId";

    private String playTimes="0";
    public  static  final  String PLAY_TIMES = "playTimes";

    private String visitTimes="0";
    public  static  final  String VISIT_TIMES = "visitTimes";

    private String reVisitTimes="0";
    public  static  final  String REVISIT_TIMES = "reVisitTimes";

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId;
    }

    public String getPlayTimes() {
        return playTimes;
    }

    public void setPlayTimes(String playTimes) {
        this.playTimes = playTimes;
    }

    public String getVisitTimes() {
        return visitTimes;
    }

    public void setVisitTimes(String visitTimes) {
        this.visitTimes = visitTimes;
    }

    public String getReVisitTimes() {
        return reVisitTimes;
    }

    public void setReVisitTimes(String reVisitTimes) {
        this.reVisitTimes = reVisitTimes;
    }

    public JSONObject toJSONObject(){
        return  JSONObject.fromObject(this);
    }

    @Override
    public String toString() {
        return this.toJSONObject().toString();
    }
}

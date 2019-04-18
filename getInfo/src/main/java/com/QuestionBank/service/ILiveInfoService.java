package com.QuestionBank.service;

import com.QuestionBank.entity.LiveInfoEntity;
import com.QuestionBank.exception.MException;

import java.util.List;

public interface ILiveInfoService {

    List<LiveInfoEntity> getLivesInfo(String startTime,String endTime) throws MException;
}

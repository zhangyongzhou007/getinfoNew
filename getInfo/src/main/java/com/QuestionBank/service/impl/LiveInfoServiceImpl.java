package com.QuestionBank.service.impl;

import com.QuestionBank.dao.mongo.LiveInfoProvider;
import com.QuestionBank.entity.LiveInfoEntity;
import com.QuestionBank.exception.MException;
import com.QuestionBank.service.ILiveInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiveInfoServiceImpl implements ILiveInfoService {

    @Override
    public List<LiveInfoEntity> getLivesInfo(String startTime, String endTime) throws MException {
        return LiveInfoProvider.getInstance().getLiveInfoList(startTime,endTime);
    }
}

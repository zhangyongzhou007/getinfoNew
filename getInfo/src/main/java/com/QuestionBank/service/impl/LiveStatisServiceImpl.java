package com.QuestionBank.service.impl;

import com.QuestionBank.dao.mongo.LiveStatisUserProvider;
import com.QuestionBank.entity.LiveStatisUserEntity;
import com.QuestionBank.service.LiveStatisService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiveStatisServiceImpl implements LiveStatisService {
	private Logger logger = Logger.getLogger(LiveStatisServiceImpl.class);
	



	@Override
	public List<String> getUserList(String subjectId) throws Exception {
		return LiveStatisUserProvider.getInstance().getUserList(subjectId);
	}
	
}

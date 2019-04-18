package com.QuestionBank.service;

import java.util.List;

public interface LiveStatisService {


	/**
	 * @Author ZhangFuGui
	 * @Description 通过sujiectId查profileId
	 * @Date 上午 10:17 2018/11/26 0026
	 * @Param [userEntity]
	 * @return java.util.List<com.moocs.entity.LiveStatisUserEntity>
	 */
	public List<String> getUserList(String subjectId) throws  Exception;
}

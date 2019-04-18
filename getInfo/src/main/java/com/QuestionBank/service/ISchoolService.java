package com.QuestionBank.service;


import com.QuestionBank.entity.GetResultEntity;
import com.QuestionBank.entity.SchoolEntity;
import com.QuestionBank.entity.SetResultEntity;
import com.QuestionBank.entity.vo.PageVo;
import com.QuestionBank.exception.MException;

import java.util.List;

/**
 * 慕校信息Service
* @ClassName: SchoolService  
* @author LeonFung  
* @date 2017年9月6日  下午7:00:46 
*
 */

public interface ISchoolService {

	/**
	 * 
	 * @Title: querySchoolList
	 * @Description: 根据条件查询慕校列表
	 * @param queryCondition
	 * @return List<SchoolEntity>
	 * @throws
	 */
	GetResultEntity<List<SchoolEntity>> querySchoolList(SchoolEntity queryCondition, PageVo pageVo);

	/**
	 * 
	 * @Title: querySchoolCount
	 * @Description: 查询慕校总数
	 * @param queryCondition
	 * @return int
	 * @throws
	 */
	long querySchoolCount(SchoolEntity queryCondition);

	/**
	 * 
	 * @Title: updateSchool
	 * @Description: 修改慕校
	 *
	 * @return
	 * @throws
	 */
	SetResultEntity saveOrUpdateSchool(SchoolEntity entity);
	
	/**
	 * 根据慕校ID删除慕校
	* @Title: deleteSchool  
	* @param  schoolId    参数  
	* @return void    返回类型  
	* @author LeonFung  
	* @date 2017年9月6日 下午7:02:46 
	* @throws
	 */
	void deleteSchool(String schoolId);

	/**
	 * 根据条件查询慕校信息
	* @Title: findSchoolInfo  
	* @param  entity
	* @return SchoolEntity      
	* @author LeonFung  
	* @date 2017年9月6日 下午7:41:51 
	* @throws
	 */
	GetResultEntity<SchoolEntity> findSchoolInfo(SchoolEntity entity);
	
	/**
	 * 根据组织ID查询慕校信息
	 * @Title: findSchoolByOrgId 
	 * @param  orgId
	 * @return SchoolEntity      
	 * @author LeonFung  
	 * @date 2017年9月6日 下午7:41:51 
	 * @throws
	 */
	SchoolEntity findSchoolByOrgId(String orgId);
	
	/**
	 * 根据schoolIds查询慕校List
	* @Title: querySchoolList  
	* @param  schoolIds
	* @return List<SchoolEntity> 
	* @author LeonFung  
	* @date 2017年9月8日 下午12:29:18 
	* @throws
	 */
	List<SchoolEntity> querySchoolList(List<String> schoolIds);

	/**
	 * 根据名称和schoolId查询慕校
	* @Title: findSchoolInfo  
	* @param  schoolId
	* @param  name
	* @return SchoolEntity 
	* @author LeonFung  
	* @date 2017年9月11日 上午10:00:39 
	* @throws
	 */
	SchoolEntity findSchoolInfo(String schoolId, String name);

	/**
	 * 申请开通慕校直播服务
	* @Title: applicationOpenLive  
	* @param  orgId
	* @return GetResultEntity<SchoolEntity> 
	* @author LeonFung  
	* @date 2017年9月12日 上午9:59:51 
	* @throws
	 */
	SetResultEntity applicationOpenLive(String orgId);

	/**
	 *  打开或者屏蔽慕校
	* @Title: openOrCloseSchool  
	* @param  schoolId
	*
	* @return SetResultEntity 
	* @author LeonFung  
	* @date 2017年9月12日 下午8:04:39 
	* @throws
	 */
	SetResultEntity openOrCloseSchool(String schoolId);
	
	/**
	 * 申请开通慕校直播服务
	* @Title: applicationOpenLiveBySchoolId  
	* @param @param schoolId
	* @return SetResultEntity
	* @author LeonFung  
	* @date 2017年9月21日 上午10:16:46 
	* @throws
	 */
	SetResultEntity applicationOpenLiveBySchoolId(String schoolId);


	/**
	 * @Title: getMoosInfo
	 * @Description: 获取慕校信息（终端）
	 * @Return: GetResultEntity
	 * @Author: LeonFung
	 * @Date: 2017-10-26 18:09:25
	 */
	GetResultEntity getMoosInfo(String schoolId, String profileId);

	//根据慕校名称模糊查询所有 ids
    List<String> querySchoolListBySchoolName(String schoolName);
	//批量获取慕校 ids
	List<String> findSchoolsByOrgIds(List<String> orgIds);

	GetResultEntity myManagerMoosList(String profileId) throws MException;
	
	List<SchoolEntity> querySchoolListByName(String name);
	List<SchoolEntity> searchClassByName(String name, PageVo pageVo);
	long searchClassByNameCount(String name);
}

package com.QuestionBank.service.impl;


import com.QuestionBank.entity.GetResultEntity;
import com.QuestionBank.entity.SchoolEntity;
import com.QuestionBank.entity.SetResultEntity;
import com.QuestionBank.entity.vo.PageVo;
import com.QuestionBank.exception.MException;
import com.QuestionBank.service.IGroupService;
import com.QuestionBank.service.ISchoolService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 慕校信息ServiceImpl  
* @ClassName: SchoolServiceImpl  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author LeonFung  
* @date 2017年9月6日  下午7:18:48 
*
 */
@Service("schoolService")
public class SchoolServiceImpl implements ISchoolService {
	
	private Logger log = Logger.getLogger(SchoolServiceImpl.class);

	@Autowired
	private IGroupService groupService;

	@Override
	public GetResultEntity<List<SchoolEntity>> querySchoolList(SchoolEntity queryCondition, PageVo pageVo) {
		return null;
	}

	@Override
	public long querySchoolCount(SchoolEntity queryCondition) {
		return 0;
	}

	@Override
	public SetResultEntity saveOrUpdateSchool(SchoolEntity entity) {
		return null;
	}

	@Override
	public void deleteSchool(String schoolId) {

	}

	@Override
	public GetResultEntity<SchoolEntity> findSchoolInfo(SchoolEntity entity) {
		return null;
	}

	@Override
	public SchoolEntity findSchoolByOrgId(String orgId) {
		return null;
	}

	@Override
	public List<SchoolEntity> querySchoolList(List<String> schoolIds) {
		return null;
	}

	@Override
	public SchoolEntity findSchoolInfo(String schoolId, String name) {
		return null;
	}

	@Override
	public SetResultEntity applicationOpenLive(String orgId) {
		return null;
	}

	@Override
	public SetResultEntity openOrCloseSchool(String schoolId) {
		return null;
	}

	@Override
	public SetResultEntity applicationOpenLiveBySchoolId(String schoolId) {
		return null;
	}

	@Override
	public GetResultEntity getMoosInfo(String schoolId, String profileId) {
		return null;
	}

	@Override
	public List<String> querySchoolListBySchoolName(String schoolName) {
		return null;
	}

	@Override
	public List<String> findSchoolsByOrgIds(List<String> orgIds) {
		return null;
	}

	@Override
	public GetResultEntity myManagerMoosList(String profileId) throws MException {
		return null;
	}

	@Override
	public List<SchoolEntity> querySchoolListByName(String name) {
		return null;
	}

	@Override
	public List<SchoolEntity> searchClassByName(String name, PageVo pageVo) {
		return null;
	}

	@Override
	public long searchClassByNameCount(String name) {
		return 0;
	}
//
////	@Autowired
////	private ISchoolCorpService schoolCorpService;
////
////	@Autowired
////	private ISchoolAuthorizationService schoolAuthorizationService;
////
////	@Autowired
////	private ISchoolAccountService schoolAccountService;
////
////	@Autowired
////	private ISubjectService iSubjectService;
////
////	@Autowired
////	private LiveAuthorizeServiceImpl liveAuthorizeService;
////
////	@Autowired
////	private IMyFollowService myFollowService;
////
////	@Autowired
////	private IJstDeviceService jstDeviceService;
//
//	@Override
//	public GetResultEntity<List<SchoolEntity>> querySchoolList(SchoolEntity queryCondition,PageVo pageVo) {
//
//		List<SchoolEntity> list = MongoProviderFactory.getSchoolProvider().querySchoolList(queryCondition, pageVo);
//		if (list.size() == 0){
//			return GetResultEntity.create404();
//		}
//
//		List<String> orgList = new ArrayList<String>();
//
//		for (SchoolEntity entity : list) {
//			if (StringUtils.isBlank(entity.getProfileId())) {
//				orgList.add(entity.getOrgId());
//			}
//		}
//
//		Map<String, ProfileInfoEntity> profileInfoMap = new HashMap<String, ProfileInfoEntity>();
//
//		if (orgList.size() > 0) {
//			try {
//				profileInfoMap = groupService.getSuperAdmins(orgList);
//			} catch (MException e) {
//				e.printStackTrace();
//			}
//		}
//
//		for (SchoolEntity entity : list) {
//			//关联
//			if (StringUtils.isBlank(entity.getProfileId())) {
//				//遍历map中的值
//				for (Map.Entry<String, ProfileInfoEntity> entry : profileInfoMap.entrySet()) {
//					ProfileInfoEntity profileInfo = entry.getValue();
//					if (entry.getKey().equals(entity.getOrgId())) {
//						if (null != profileInfo) {
//							entity.setPhone(profileInfo.getMobile());
//							entity.setProfileId(profileInfo.getProfileId());
//						}
//					}
//				}
//			}
//		}
//
//		GetResultEntity<List<SchoolEntity>> result = GetResultEntity.create200();
//		result.setData(list);
//		return result;
//	}
//
//	@Override
//	public long querySchoolCount(SchoolEntity queryCondition) {
//
//		return MongoProviderFactory.getSchoolProvider().querySchoolCount(queryCondition);
//	}
//
//	@Override
//	public SetResultEntity saveOrUpdateSchool(SchoolEntity entity) {
//		SchoolEntity qryEntity = new SchoolEntity();
//		qryEntity.setSchoolId(entity.getSchoolId());
//		SchoolEntity oldSchool = MongoProviderFactory.getSchoolProvider().getSchoolByQueryCondition(qryEntity);
//
//		String orgId = entity.getOrgId();
//
//		if (!StringUtils.isBlank(orgId)) {
//			if ("3".equals(entity.getLiveServiceStatus())) {
//				SchoolEntity sEntity = findSchoolByOrgId(orgId);
//				//当慕校直播服务状态为0：未申请，的情况下不能直接操作关闭直播服务状态
//				if ("0".equals(sEntity.getLiveServiceStatus())) {
//					return SetResultEntity.resultEntity(10000300, "保存失败，该慕校未申请直播服务，不能选择关闭选项");
//				}
//			}
//		}
//
//		String ywAccount = entity.getCompanyId();
//		String ywPassword =  entity.getCompanyPassword();
//		String zbbAccount = entity.getZbbAccount();
//		SetResultEntity sc = null;
//		//慕校账号管理关联服务
// 		if ((!StringUtils.isBlank(ywAccount)&&!StringUtils.isBlank(zbbAccount) && !StringUtils.isBlank(ywPassword))||(!StringUtils.isBlank(entity.getJstSchoolId()))) {
//			sc =  schoolCorpService.saveOrUpdate(entity.getSchoolId(), ywAccount, entity.getOrgId(),zbbAccount, ywPassword,entity.getJstSchoolId());
//		}
//		//保存当前慕校授权的其他慕校信息
//		if (entity.getAuthSchools() != null && entity.getAuthSchools().length > 0){
// 			List<SchoolAuthorizationEntity> authIds = new ArrayList<SchoolAuthorizationEntity>();
//			String[] authSchools = entity.getAuthSchools();
//			for (int i = 0;i < authSchools.length;i ++){
//				SchoolAuthorizationEntity authSchool = new SchoolAuthorizationEntity();
//				authSchool.setSchoolId(entity.getSchoolId());
//				authSchool.setAuthSchoolId(authSchools[i]);
//				authIds.add(authSchool);
//			}
//
//			sc =  schoolAuthorizationService.saveAuthSchoolIds(entity.getSchoolId(),authIds);
//		}
//		if (null != sc && ( sc.getStatusCode() == 10000300)){
//			return sc;
//		}
//
//		MongoProviderFactory.getSchoolProvider().saveOrupdateSchool(entity);
//
//		//慕校名称修改，通知单直播,教视通一起修改
//		if(oldSchool != null){
//			if(!oldSchool.getName().equals(entity.getName()) || !oldSchool.getImageUrl().equals(entity.getImageUrl())){
//				iSubjectService.modifySchoolName(entity.getSchoolId(), entity);
//				try {
//					jstDeviceService.modifySchoolName(entity.getSchoolId(), entity.getName());
//				} catch (MException e) {
//				}
//			}
//		}
//
//		return SetResultEntity.create200(0);
//	}
//
//	@Override
//	public GetResultEntity<List<SchoolEntity>> querySchoolList(SchoolEntity queryCondition, PageVo pageVo) {
//		return null;
//	}
//
//	@Override
//	public void deleteSchool(String schoolId) {
//
//		MongoProviderFactory.getSchoolProvider().deleteSchool(schoolId);
//	}
//
//	@Override
//	public GetResultEntity<SchoolEntity> findSchoolInfo(SchoolEntity e) {
//
//		SchoolEntity entity = MongoProviderFactory.getSchoolProvider().getSchoolByQueryCondition(e);
//
//		GetResultEntity<SchoolEntity> result = new GetResultEntity<SchoolEntity>();
//		if (entity == null){
//			result.setStatusCode(10000404);
//			result.setInfo("没有查询到数据");
//			result.setData(e);
//			return result;
//		}
//		try {
//			SchoolAccountEntity schoolAccountEntity = null;
//			ProfileInfoEntity profileInfo =  groupService.getSuperAdmin(entity.getOrgId());
//			if (null != profileInfo) {
//				entity.setPhone(profileInfo.getMobile());
//				entity.setProfileId(profileInfo.getProfileId());
//
//				schoolAccountEntity = new SchoolAccountEntity();
//				schoolAccountEntity.setSchoolId(entity.getSchoolId());
//				schoolAccountEntity.setProfileId(profileInfo.getProfileId());
//			}
//
//			schoolAccountEntity = schoolAccountService.findOneBySchoolIdAndProfileId(schoolAccountEntity);
//			entity.setJstSchoolId(schoolAccountEntity.getJstSchoolId());
//		} catch (Exception e1) {
//			log.error(e1.getMessage());
//		}
//
//		//获取当前慕校授权的其他慕校信息
//		List<SchoolAuthorizationEntity> authorizations = schoolAuthorizationService.findAuthSchoolIdByCurSchoolId(entity.getSchoolId());
//		entity.setAuthList(authorizations);
//		result = GetResultEntity.create200();
//		result.setData(entity);
//		return result;
//	}
//
//	@Override
//	public SchoolEntity findSchoolByOrgId(String orgId) {
//
//		SchoolEntity entity = new SchoolEntity();
//	    entity.setOrgId(orgId);
//
//	    return  MongoProviderFactory.getSchoolProvider().getSchoolByQueryCondition(entity);
//
//	}
//
//	/*orgIds修改为schoolIds*/
//	@Override
//	public List<SchoolEntity> querySchoolList(List<String> schoolIds) {
//
//		return MongoProviderFactory.getSchoolProvider().querySchoolList(schoolIds);
//	}
//
//	@Override
//	public SchoolEntity findSchoolInfo(String schoolId, String name) {
//		SchoolEntity entity = new SchoolEntity();
//
//		if (!StringUtils.isBlank(schoolId)) {
//			entity.setSchoolId(schoolId);
//		}
//		entity.setName(name);
//	    return  MongoProviderFactory.getSchoolProvider().getSchoolByQueryCondition(entity);
//	}
//
//	@Override
//	public GetResultEntity<SchoolEntity> getMoosInfo(String schoolId, String profileId) {
//
//		GetResultEntity<SchoolEntity> result;
//
//		//获取慕校基本信息
//		SchoolEntity entity = new SchoolEntity();
//		entity.setSchoolId(schoolId);
//		entity.setStatus("1");
//		entity = MongoProviderFactory.getSchoolProvider().getSchoolByQueryCondition(entity);
//
//		entity.setProfileId(profileId);
//		entity.setIsTerminal(true);
//
//		entity = MongoProviderFactory.getSchoolProvider().countSchoolInfo(entity);
//
//		/*//统计课程数量
//		CourseEntity courseEntity = new CourseEntity();
//		courseEntity.setSchoolId(entity.getSchoolId());
//		long courseNum = MongoProviderFactory.getCourseProvider().queryCourseCount(courseEntity);
//		entity.setCourseNum((int) courseNum);
//
//		//统计关注数量
//		MyFollowEntity myFollowEntity = new MyFollowEntity();
//		myFollowEntity.setSchoolId(entity.getSchoolId());
//		long followNum = MongoProviderFactory.getMyFollowProvider().queryCount(myFollowEntity);
//		entity.setFollowNum((int)followNum);
//
//		//查询当前登录人是否关注了该慕校，是否是属于该慕校的成员
//		myFollowEntity.setProfileId(profileId);
//		long num = MongoProviderFactory.getMyFollowProvider().queryCount(myFollowEntity);
//		if (num > 0) {
//			entity.setIsFollow("1");
//			//查询他属于的慕校列表
//			List<MemberEntity> memberList = MongoProviderFactory.getMemberProvider().getMemberOfProfile(profileId, false);
//			for (MemberEntity member : memberList ) {
//				if (entity.getSchoolId().equals(member.getSchoolId())) {
//					entity.setIsBelong("1");
//				}
//			}
//		}
//
//		//统计名师数量
//		SpeakerEntity speakerEntity = new SpeakerEntity();
//		speakerEntity.setSchoolId(schoolId);
//		int teacherNum = MongoProviderFactory.getSpeakerProvider().getSpeakerCount(speakerEntity);
//		entity.setTeacherNum(teacherNum);
//
//		//TODO:统计学生数量：*/
//
//		result = GetResultEntity.create200();
//		result.setData(entity);
//
//		//更改慕校有内容更新的标识
//		myFollowService.cancelNewFlag(schoolId,profileId);
//
//		return result;
//	}
//	@Override
//	public SetResultEntity applicationOpenLive(String orgId) {
//
//		SchoolEntity entity = findSchoolByOrgId(orgId);
//
//		if (null == entity) {
//			{return SetResultEntity.resultEntity(10000300, "慕校不存在，请联系管理员");}
//		}
//
//		if ("2".equals(entity.getStatus())) {
//			{return SetResultEntity.resultEntity(10000300, "慕校已屏蔽，请联系管理员");}
//		}
//
//		entity.setLiveServiceStatus("1");
//
//		return saveOrUpdateSchool(entity);
//	}
//
//	@Override
//	public SetResultEntity openOrCloseSchool(String schoolId) {
//
//		SchoolEntity entity  = new SchoolEntity();
//		entity.setSchoolId(schoolId);
//
//	    entity = MongoProviderFactory.getSchoolProvider().getSchoolByQueryCondition(entity);
//
//		if (null == entity) {
//			{return SetResultEntity.resultEntity(10000300, "慕校不存在，请联系管理员");}
//		}
//
//		String status = entity.getStatus();
//
//		//如果是打开的，则关闭（屏蔽），否则就打开（正常）
//		if ("1".equals(status)) {
//			entity.setStatus("2");
//		}
//
//        if ("2".equals(status)){
//			entity.setStatus("1");
//		}
//
//        //保存
//        MongoProviderFactory.getSchoolProvider().saveOrupdateSchool(entity);
//
//		return SetResultEntity.create200(0);
//	}
//
//	public SetResultEntity applicationOpenLiveBySchoolId(String schoolId) {
//
//		SchoolEntity entity = findSchoolInfo(schoolId, null);
//
//		if (null == entity) {
//			{return SetResultEntity.resultEntity(10000300, "慕校不存在，请联系管理员");}
//		}
//
//		//直播服务状态是已开通的情况下，不能在申请开通
//		if ("2".equals(entity.getLiveServiceStatus())) {
//			{return SetResultEntity.resultEntity(10000300, "慕校直播服务状态是已开通，不能在申请开通");}
//		}
//
//		if ("3".equals(entity.getLiveServiceStatus())) {
//			{return SetResultEntity.resultEntity(10000300, "慕校直播服务状态已关闭，不能在申请开通，请联系管理员");}
//		}
//
//		if ("2".equals(entity.getStatus())) {
//			{return SetResultEntity.resultEntity(10000300, "慕校已屏蔽，请联系管理员");}
//		}
//
//		entity.setLiveServiceStatus("1");
//
//		return saveOrUpdateSchool(entity);
//	}
//
//
//	@Override
//	public List<String> querySchoolListBySchoolName(String schoolName) {
//		return MongoProviderFactory.getSchoolProvider().querySchoolListBySchoolName(schoolName);
//	}
//
//	@Override
//	public List<String> findSchoolsByOrgIds(List<String> orgIds) {
//		return MongoProviderFactory.getSchoolProvider().querySchoolsByOrgIds(orgIds);
//	}
//
//	@Override
//	public GetResultEntity myManagerMoosList(String profileId) {
//
//		GetResultEntity<List<SchoolEntity>> result;
//		List<SchoolEntity> schoolEntityList = new ArrayList<SchoolEntity>();
//		List<String> orgIds = new ArrayList<String>();
//		List<String> schoolIds = new ArrayList<String>();
//		List<String> authSchoolIds = new ArrayList<String>();
//
//		try{
//
//			//获取有管理权限的组织
//			List<OrgAdminEntity> orgAdmins = groupService.getOrgAdmins(profileId);
//			//获取慕校管理员
//			List<OrgAdminEntity> admins = OrgAdminEntity.getSchoolAdmins(orgAdmins);
//			//获取慕校的超级管理员
//			List<OrgAdminEntity>  superAdmins = OrgAdminEntity.getSuperAdmins(orgAdmins);
//			//获取被授权管理的慕校
//			if (!StringUtils.isBlank(profileId)) {
//				authSchoolIds = liveAuthorizeService.queryAuthorizeSchoolList(profileId);
//			}
//
//			for (OrgAdminEntity admin : admins) {
//				orgIds.add(admin.getOrgId());
//			}
//
//			for (OrgAdminEntity superAdmin : superAdmins ) {
//				if (!orgIds.contains(superAdmin.getOrgId())) {
//					orgIds.add(superAdmin.getOrgId());
//				}
//			}
//
//		}catch (MException e){
//			result = GetResultEntity.create404();
//			return result;
//		}
//
//		for (String orgId : orgIds){
//			SchoolEntity qEntity = new SchoolEntity();
//			qEntity.setOrgId(orgId);
//			SchoolEntity schoolEntity = MongoProviderFactory.getSchoolProvider().getSchoolByQueryCondition(qEntity);
//			if (schoolEntity != null) {
//				schoolEntity.setDetail("");
//				schoolIds.add(schoolEntity.getSchoolId());
//				schoolEntityList.add(schoolEntity);
//			}
//		}
//
//		if (authSchoolIds.size() > 0) {
//			List<SchoolEntity> authSchoolEntityList = MongoProviderFactory.getSchoolProvider().querySchoolList(authSchoolIds);
//				for (SchoolEntity schoolEntity : authSchoolEntityList ) {
//					if (!schoolIds.contains(schoolEntity.getSchoolId())) {
//						schoolEntity.setDetail("");
//						schoolEntityList.add(schoolEntity);
//					}
//				}
//		}
//
//		result = GetResultEntity.create200();
//		result.setData(schoolEntityList);
//		return result;
//	}
//
//	@Override
//	public List<SchoolEntity> querySchoolListByName(String name){
//		List<SchoolEntity> list = MongoProviderFactory.getSchoolProvider().querySchoolListByName(name);
//
//		/*List<String> orgList = new ArrayList<String>();
//
//		for (SchoolEntity entity : list) {
//			if (StringUtils.isBlank(entity.getProfileId())) {
//				orgList.add(entity.getOrgId());
//			}
//		}
//
//		Map<String, ProfileInfoEntity> profileInfoMap = new HashMap<String, ProfileInfoEntity>();
//
//		if (orgList.size() > 0) {
//			try {
//				profileInfoMap = groupService.getSuperAdmins(orgList);
//			} catch (MException e) {
//				e.printStackTrace();
//			}
//		}
//
//		for (SchoolEntity entity : list) {
//			//关联
//			if (StringUtils.isBlank(entity.getProfileId())) {
//				//遍历map中的值
//				for (Map.Entry<String, ProfileInfoEntity> entry : profileInfoMap.entrySet()) {
//					ProfileInfoEntity profileInfo = entry.getValue();
//					if (entry.getKey().equals(entity.getOrgId())) {
//						if (null != profileInfo) {
//							entity.setPhone(profileInfo.getMobile());
//							entity.setProfileId(profileInfo.getProfileId());
//						}
//					}
//				}
//			}
//		}*/
//
//
//		return list;
//	}
//
//	@Override
//	public List<SchoolEntity> searchClassByName(String name, PageVo pageVo) {
//		int skip = (pageVo.getCurrPage()-1)*pageVo.getPageSize();
//		return MongoProviderFactory.getSchoolProvider().querySchoolListByName(name, skip, pageVo.getPageSize());
//	}
//
//	@Override
//	public long searchClassByNameCount(String name) {
//		return MongoProviderFactory.getSchoolProvider().querySchoolListByNameCount(name);
//	}
}

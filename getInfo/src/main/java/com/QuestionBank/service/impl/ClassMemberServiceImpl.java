package com.QuestionBank.service.impl;


import com.QuestionBank.service.IClassMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("classMemberService")
public class ClassMemberServiceImpl implements IClassMemberService {
	private static Logger logger = LoggerFactory.getLogger(ClassMemberServiceImpl.class);
//	@Autowired
//	private IdServiceImpl idService;
//	@Autowired
//	private VersionServiceImpl verService;
//	@Autowired
//	private ValidServiceImpl validService;
//	@Autowired
//	private NotifyServiceImpl notifyService;
//
//
//	@Override
//	public void addClassMember(ClassMemberEntity member) throws MException {
//		validService.validClassMember(member);
//		String now = DateUtil.getMoosNow();
//		if (member.getClassMemberId().equals("")){
//			member.setClassMemberId(idService.getClassMemberId());
//		}
//		member.setCreateTime(now);
//		member.setModifyTime(now);
//		member.setVer(verService.getClassMemberVersion());
//		member.setPyName(PinYin.toPinYin(member.getName()));
//		member.setPyjcName(PinYin.toPYJC(member.getName()));
//		member.setUpdate(1);
//		member.setRole(ClassMemberEntity.ROLE_TWO);
//		MongoProviderFactory.getClassMemberProvider().addClassMember(member);
//		//notifyService.sendJoinClass(member.getProfileId(),member.getName(), member.getClassId());
//	}
//
//	@Override
//	public void modifyClassMember(ClassMemberEntity cls) throws MException {
//		String now = DateUtil.getMoosNow();
//		cls.setModifyTime(now);
//		cls.setVer(verService.getClassMemberVersion());
//		cls.setPyName(PinYin.toPinYin(cls.getName()));
//		cls.setPyjcName(PinYin.toPYJC(cls.getName()));
//		cls.setUpdate(0);
//		MongoProviderFactory.getClassMemberProvider().modifyClassMember(cls);
//	}
//
//	@Override
//	public void removeClassMember(String classMemberId) throws MException {
//		//MongoProviderFactory.getClassMemberProvider().removeClassMember(classMemberId);
//		ClassMemberEntity cm = getClassMember(classMemberId);
//		if (cm == null){return;}
//		String now = DateUtil.getMoosNow();
//		cm.setModifyTime(now);
//		cm.setVer(verService.getClassMemberVersion());
//		cm.setUpdate(-1);
//		validService.validRemoveClassMember(cm);
//		MongoProviderFactory.getClassMemberProvider().modifyClassMember(cm);
//	}
//
//	@Override
//	public ClassMemberEntity getClassMember(String classMemberId)
//			throws MException {
//		return MongoProviderFactory.getClassMemberProvider().getClassMember(classMemberId);
//	}
//
//	@Override
//	public void addClassMembers(List<ClassMemberEntity> members)
//			throws MException {
//		if (members.size() == 0){
//			return ;
//		}
//		validService.validClassMembers(members);
//		if(members.size()==0){
//			return;
//		}
//		String now = DateUtil.getMoosNow();
//		for(int i=0;i<members.size();i++){
//			ClassMemberEntity cm = members.get(i);
//			if (cm.getClassMemberId().equals("")){
//				cm.setClassMemberId(idService.getClassMemberId());
//			}
//			cm.setCreateTime(now);
//			cm.setModifyTime(now);
//			cm.setPyName(PinYin.toPinYin(cm.getName()));
//			cm.setPyjcName(PinYin.toPYJC(cm.getName()));
//			cm.setVer(verService.getClassMemberVersion());
//			cm.setUpdate(1);
//		}
//		MongoProviderFactory.getClassMemberProvider().addClassMembers(members);
//		for(int i=0;i<members.size();i++){
//			ClassMemberEntity cm = members.get(i);
//			//notifyService.sendJoinClass(cm.getProfileId(),cm.getName(), cm.getClassId());
//		}
//	}
//
//	@Override
//	public void modifyClassMembers(List<ClassMemberEntity> classes)
//			throws MException {
//		String now = DateUtil.getMoosNow();
//		for(int i=0;i<classes.size();i++){
//			ClassMemberEntity cm = classes.get(i);
//			cm.setModifyTime(now);
//			cm.setVer(verService.getClassMemberVersion());
//			cm.setPyName(PinYin.toPinYin(cm.getName()));
//			cm.setPyjcName(PinYin.toPYJC(cm.getName()));
//			cm.setUpdate(0);
//		}
//		MongoProviderFactory.getClassMemberProvider().modifyClassMembers(classes);
//	}
//
//	@Override
//	public void removeClassMembers(List<String> classMemberIds)
//			throws MException {
//		if(classMemberIds.size()==0){
//			return;
//		}
//		//MongoProviderFactory.getClassMemberProvider().removeClassMembers(classMemberIds);
//		List<ClassMemberEntity> cms =  getClassMembers(classMemberIds);
//		String now = DateUtil.getMoosNow();
//		for(int i=0;i<cms.size();i++){
//			ClassMemberEntity cm = cms.get(i);
//			cm.setModifyTime(now);
//			cm.setVer(verService.getClassMemberVersion());
//			cm.setUpdate(-1);
//		}
//		validService.validRemoveClassMembers(cms);
//		MongoProviderFactory.getClassMemberProvider().modifyClassMembers(cms);
//	}
//
//	@Override
//	public List<ClassMemberEntity> getClassMembers(List<String> classMemberIds)
//			throws MException {
//		return MongoProviderFactory.getClassMemberProvider().getClassMembersByIds(classMemberIds);
//	}
//
//	@Override
//	public List<ClassMemberEntity> searchClassMember(String filter)
//			throws MException {
//		return MongoProviderFactory.getClassMemberProvider().searchClassMember(filter);
//	}
//
//	@Override
//	public List<ClassMemberEntity> getClassMembersOfProfileInSchool(String profileId,
//			String schoolId,boolean etag) {
//		return MongoProviderFactory.getClassMemberProvider().getClassMembersOfProfileInSchool(profileId, schoolId,etag);
//	}
//
//	@Override
//	public List<ClassMemberEntity> getClassMembersOfClass(String classId,boolean etag) {
//		return MongoProviderFactory.getClassMemberProvider().getClassMembersOfClass(classId,etag);
//	}
//
//	@Override
//	public List<ClassMemberEntity> getClassMembersOfProfile(String profileId) throws MException {
//		return MongoProviderFactory.getClassMemberProvider().getClassMembersOfProfile(profileId);
//	}
//
//	@Override
//	public List<ClassMemberEntity> getClassMembersOfClassByPage(String classId, int skip, int limit) throws MException {
//		return MongoProviderFactory.getClassMemberProvider().getClassMembersOfClassByPage(classId, skip, limit);
//	}
//
//	@Override
//	public List<ClassMemberEntity> getClassMembersOfMember(String memberId) throws MException {
//		return MongoProviderFactory.getClassMemberProvider().getClassMembersOfMember(memberId);
//	}
//
//	@Override
//	public List<ClassMemberEntity> searchClassMembersOfClasses(String name, List<String> classIds) throws MException {
//		return MongoProviderFactory.getClassMemberProvider().searchClassMembersOfClasses(name, classIds);
//	}
//
//	@Override
//	public ClassMemberEntity getClassMemberOfMemberInClass(String memberId,
//			String classId) throws MException {
//		return MongoProviderFactory.getClassMemberProvider().getClassMemberOfMemberInClass(memberId, classId);
//	}
//
//	@Override
//	public ClassMemberEntity getClassAdviser(String classId) throws MException {
//		return MongoProviderFactory.getClassMemberProvider().getClassAdviser(classId);
//	}
//
//	@Override
//	public List<ClassMemberEntity> getClassMembersOfMembers(
//			List<String> memberIds) throws MException {
//		return MongoProviderFactory.getClassMemberProvider().getClassMembersOfMembers(memberIds);
//	}
//
//	@Override
//	public List<ClassMemberEntity> searchClassMembersOfSchool(String filter,
//			String schoolId,String profielId) throws MException {
//		return MongoProviderFactory.getClassMemberProvider().searchClassMembersOfSchool(filter, schoolId,profielId);
//	}
//
//	@Override
//	public List<ClassMemberEntity> getClassMembersOfClasses(
//			List<String> classIds) throws MException {
//		return MongoProviderFactory.getClassMemberProvider().getClassMembersOfClasses(classIds);
//	}
//
//	@Override
//	public int getClassMemberRowCountByClass(String classId) throws MException {
//		return MongoProviderFactory.getClassMemberProvider().getClassMemberRowCountByClass(classId);
//	}
//
//	@Override
//	public ClassMemberEntity getClassMemberOfProfileInClass(String profileId,
//			String classId) throws MException {
//		return MongoProviderFactory.getClassMemberProvider().getClassMemberOfProfileInClass(profileId, classId);
//	}
//
//	@Override
//	public Map<String, List<ClassMemberEntity>> getClassMembersOfProfileMap(
//			List<String> profileIds) throws MException {
//		List<ClassMemberEntity> classMembers = MongoProviderFactory.getClassMemberProvider().getClassMembersOfProfiles(profileIds);
//		Map<String, List<ClassMemberEntity>> map = new HashMap<String, List<ClassMemberEntity>>();
//		for (int i=0;i<classMembers.size();i++){
//			ClassMemberEntity cm = classMembers.get(i);
//			List<ClassMemberEntity> cms = new ArrayList<ClassMemberEntity>();
//			if (map.containsKey(cm.getProfileId())){
//				cms = map.get(cm.getProfileId());
//			}
//			else{
//				cms = new ArrayList<ClassMemberEntity>();
//				map.put(cm.getProfileId(), cms);
//			}
//			cms.add(cm);
//		}
//		return map;
//	}
//
//	@Override
//	public void active(String profileId,String activedTime) throws MException {
//		Map<String,String> profileMap = new HashMap<String, String>();
//		profileMap.put(profileId, activedTime);
//		active(profileMap);
//	}
//	@Override
//	public void active(Map<String,String> profileMap) throws MException {
//		List<String> profileIds = new ArrayList<String>();
//		for (String p : profileMap.keySet()){
//			profileIds.add(p);
//		}
//
//		List<ClassMemberEntity> classMembers = MongoProviderFactory.getClassMemberProvider().getClassMembersOfProfiles(profileIds);
//		if (classMembers.size() == 0){
//			return;
//		}
//		List<ClassMemberEntity> modifyEntitys = new ArrayList<ClassMemberEntity>();
//		for(int i=0;i<classMembers.size();i++){
//			ClassMemberEntity cm = classMembers.get(i);
//			if (cm.getActived() == 0){
//				cm.setActived(1);
//				cm.setActivedTime(profileMap.get(cm.getProfileId()));
//				logger.info("active profile " + cm.getProfileId() + ",activedTime " + cm.getActivedTime());
//				modifyEntitys.add(cm);
//			}
//		}
//		if (modifyEntitys.size() == 0){
//			return;
//		}
//		IVersionService ver = new VersionServiceImpl();
//		String now = DateUtil.getMoosNow();
//		for(int i=0;i<modifyEntitys.size();i++){
//			ClassMemberEntity cm = modifyEntitys.get(i);
//			cm.setModifyTime(now);
//			cm.setVer(ver.getClassMemberVersion());
//			cm.setUpdate(0);
//		}
//		MongoProviderFactory.getClassMemberProvider().modifyClassMembers(modifyEntitys);
//	}
//	@Override
//	public void setActived(Map<String,Integer> activeMap) throws MException {
//		List<String> profileIds = new ArrayList<String>();
//		for(String profileId : activeMap.keySet()){
//			profileIds.add(profileId);
//		}
//		List<ClassMemberEntity> classMembers = MongoProviderFactory.getClassMemberProvider().getClassMembersOfProfiles(profileIds);
//		if (classMembers.size() == 0){
//			return;
//		}
//		List<ClassMemberEntity> modifyEntitys = new ArrayList<ClassMemberEntity>();
//		for(int i=0;i<classMembers.size();i++){
//			ClassMemberEntity cm = classMembers.get(i);
//			cm.setActived(activeMap.get(cm.getProfileId()));
//			modifyEntitys.add(cm);
//		}
//		if (modifyEntitys.size() == 0){
//			return;
//		}
//		IVersionService ver = new VersionServiceImpl();
//		String now = DateUtil.getMoosNow();
//		for(int i=0;i<modifyEntitys.size();i++){
//			ClassMemberEntity cm = modifyEntitys.get(i);
//			cm.setModifyTime(now);
//			cm.setVer(ver.getClassMemberVersion());
//			cm.setUpdate(0);
//		}
//		MongoProviderFactory.getClassMemberProvider().modifyClassMembers(modifyEntitys);
//	}
//
//
//	@Override
//	public List<ClassMemberEntity> getClassMembersByPage(int skip, int limit)
//			throws MException {
//		List<ClassMemberEntity> result = MongoProviderFactory.getClassMemberProvider().getClassMembersByPage(skip,limit);
//		return result;
//	}
//	@Override
//	public int getClassMembersCount() throws MException {
//		int result = MongoProviderFactory.getClassMemberProvider().getClassMembersCount();
//		return result;
//	}
//
//	@Override
//	public void modifyProfile(String oldProfileId, String newProfileId)
//			throws MException {
//		List<ClassMemberEntity> members = this.getClassMembersOfProfile(oldProfileId);
//		for (int i=0;i<members.size();i++){
//			members.get(i).setProfileId(newProfileId);
//		}
//		modifyClassMembers(members);
//	}
//
//	@Override
//	public Map<String, Boolean> searchMemberInfo(String profileId, List<String> classIds) throws MException {
//
//		return MongoProviderFactory.getClassMemberProvider().getMemberInfo(profileId, classIds);
//	}

}

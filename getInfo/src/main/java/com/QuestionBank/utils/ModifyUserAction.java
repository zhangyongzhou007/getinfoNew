package com.QuestionBank.utils;


import com.QuestionBank.entity.ProfileChangeEntity;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ModifyUserAction implements IAction {
	private static Logger logger = LoggerFactory.getLogger(ModifyUserAction.class);
	@Override
	public Element doAct(Element doc) {
		Map<String,String> paraMap = SubServiceMoos.getParameters(doc);
		logger.info("doAct:"+StringUtils.print(paraMap));
		
		String profileId = "";
		String avatar = "";
		String name = "";
		String mobile = "";
		if (paraMap.containsKey("profile-id")){
			profileId = paraMap.get("profile-id");
		}
		if (paraMap.containsKey("avatar")){
			avatar = paraMap.get("avatar");
		}
		if (paraMap.containsKey("name")){
			name = paraMap.get("name");
		}
		if (paraMap.containsKey("mobile")){
			mobile = paraMap.get("mobile");
		}
		ProfileChangeEntity profile = new ProfileChangeEntity();
		profile.setAvatar(avatar);
		profile.setMobile(mobile);
		profile.setName(name);
		profile.setProfileId(profileId);

		Map<String,String> resultMap = new HashMap<String, String>();
		Element resultEle = null;
//		try {
//			logger.info("notify profile change:"+profile.toString());
//			ProfileChangeService.getInstance().profileChanged(profile);
//		} catch (Exception e) {
//			resultEle = SubServiceMoos.getResult(doc, resultMap,e.getMessage());
//			return resultEle;
//		}
		resultEle = SubServiceMoos.getResult(doc, resultMap);
		return resultEle;
	}

	@Override
	public String getName() {
		return "modify-QuestionBank";
	}

}

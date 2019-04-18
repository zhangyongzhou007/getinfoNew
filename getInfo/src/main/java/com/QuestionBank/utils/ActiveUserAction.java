package com.QuestionBank.utils;


import com.QuestionBank.service.IClassMemberService;
import com.QuestionBank.service.impl.ClassMemberServiceImpl;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ActiveUserAction implements IAction {
	private static Logger logger = LoggerFactory.getLogger(ActiveUserAction.class);
	@Override
	public Element doAct(Element doc) {
		Map<String, String> paraMap = SubServiceMoos.getParameters(doc);
		logger.info("doAct:" + StringUtils.print(paraMap));

		String profileId = "";
		String activedTime = "";
		if (paraMap.containsKey("profile-id")) {
			profileId = paraMap.get("profile-id");
		}
		if (paraMap.containsKey("actived-time")) {
			activedTime = paraMap.get("actived-time");
		}
		IClassMemberService classMemberService = new ClassMemberServiceImpl();
		Map<String, String> resultMap = new HashMap<String, String>();
		Element resultEle = null;

//			classMemberService.active(profileId,activedTime);
//		} catch (MException e) {
//			resultEle = SubServiceMoos.getResult(doc, resultMap,e.getMessage());
//			return resultEle;
//		}
		resultEle = SubServiceMoos.getResult(doc, resultMap);
		return resultEle;
	}

	@Override
	public String getName() {
		return "active-QuestionBank";
	}


	}


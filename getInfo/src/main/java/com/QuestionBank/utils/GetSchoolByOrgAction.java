package com.QuestionBank.utils;


import com.QuestionBank.entity.SchoolEntity;
import com.QuestionBank.service.ISchoolService;
import com.QuestionBank.service.impl.SchoolServiceImpl;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;

public class GetSchoolByOrgAction implements IAction {

	@Override
	public Element doAct(Element doc) {
		Map<String,String> paraMap = SubServiceMoos.getParameters(doc);
		String orgId = "";
		if (paraMap.containsKey("org-id")){
			orgId = paraMap.get("org-id");
		}
		ISchoolService schoolService = (ISchoolService) new SchoolServiceImpl();
		SchoolEntity school = schoolService.findSchoolByOrgId(orgId);
		Map<String,String> resultMap = new HashMap<String, String>();
		if (school != null){
			resultMap.put("school-id", school.getSchoolId());
			resultMap.put("school-name", school.getName());
		}
		Element resultEle = SubServiceMoos.getResult(doc, resultMap);
		return resultEle;
	}

	@Override
	public String getName() {
		return "get-school-org";
	}

}

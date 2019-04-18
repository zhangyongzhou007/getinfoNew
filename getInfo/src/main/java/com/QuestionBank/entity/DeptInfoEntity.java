package com.QuestionBank.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeptInfoEntity {
	
	private String deptId;
	
	private String deptPathName;
	
	private String deptName;

	public String getDeptId() {
		return deptId;
	}

	public String getDeptPathName() {
		return deptPathName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public void setDeptPathName(String deptPathName) {
		this.deptPathName = deptPathName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public static Map<String, DeptInfoEntity> parseToMap(List<Map<String, String>> list)
	{
		Map<String, DeptInfoEntity> deptMap = new HashMap<String, DeptInfoEntity>();
		for (Map<String, String> map : list)
		{
			DeptInfoEntity dept = new DeptInfoEntity();
			dept.setDeptId(map.get("dept-id"));
			dept.setDeptName(map.get("name"));
			dept.setDeptPathName(map.get("path-name"));
			deptMap.put(dept.getDeptId(), dept);
		}
		return deptMap;
	}
	
}

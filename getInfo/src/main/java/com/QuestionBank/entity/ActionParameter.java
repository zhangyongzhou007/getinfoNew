package com.QuestionBank.entity;

import java.util.HashMap;
import java.util.Map;

public class ActionParameter {
	String code = "";
	HashMap<String,String> paraMap = new HashMap<String, String>();
	
	public ActionParameter(String code){
		this.code = code;
	}
	public void put(String name,String value){
		paraMap.put(name, value);
	}
	
	static public ActionParameter none = new ActionParameter("-1");
	
	@Override
	public String toString() {
		if (paraMap.size() == 0){
			return "code:"+code;
		}
		else{
			StringBuilder sb = new StringBuilder();
			sb.append("code:").append(code);
			int index=0;
			for(Map.Entry<String, String> entry:paraMap.entrySet()){
				if (index == 0){
					sb.append("?");
				}
				else{
					sb.append(";");
				}
				sb.append(entry.getKey()).append("=").append(entry.getValue());
				index++;
			}
			return sb.toString();
		}
	
	}
}

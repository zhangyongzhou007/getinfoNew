package com.QuestionBank.utils;


import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SubServiceMoos extends AbstractSubService
{
	private static final Logger Log = LoggerFactory.getLogger(SubServiceMoos.class);
	 
	private XmppService server =XmppService.getInstance();
	private XmppRouter router =server.getRouter();
    private XMPPPacketReader xmppReader = new XMPPPacketReader();

   

	public XMPPPacketReader getXmppReader() {
		return new XMPPPacketReader();
	}

	public void setXmppreader(XMPPPacketReader xmppReader) {
		this.xmppReader = xmppReader;
	}
	@Override
	public String getName()
	{
		return "moos";
	}

	@Override
	public Element handler(Element packet)
	{	
		String action ="";
		Element ecommerce = packet.element("do");
		action = ecommerce.attributeValue("action");
	    return this.getActions().get(action).doAct(packet);
	}

	
	public static Map<String,String> getParameters(Element packet){
		Map<String,String> resultMap = new HashMap<String, String>();
		List pEles = packet.element("do").elements("p");
		for (int i=0;i<pEles.size();i++){
			Element pEle = (Element)pEles.get(i);
			String name = pEle.attribute("name").getValue();
			String value = pEle.getTextTrim();
			resultMap.put(name, value);
		}
		return resultMap;
	}
	public static Element getResult(Element doc,Map<String,String> result){
		return getResult(doc,result,null);
	}
	
	public static Element getResult(Element doc,Map<String,String> result,String errInfo){
		Element root = (Element)doc.clone();
		
		String serviceJid = XmppService.getInstance().getRouter().getServiveJid();
		root.attribute("to").setValue(root.attribute("from").getValue());
		root.attribute("from").setValue(serviceJid);
		root.attribute("type").setValue("result");
		root.element("do").setName("result");
		Element resultEle = root.element("result");
		if (errInfo != null){
			resultEle.addAttribute("status", "500");
			resultEle.addAttribute("info", errInfo);
		}
		else{
			if (result.size() == 0){
				resultEle.addAttribute("status", "404");
				resultEle.addAttribute("info", "Not Found");
			}
			else{
				resultEle.addAttribute("status", "200");
				resultEle.addAttribute("info", "OK");
			}
		}

		resultEle.clearContent();
		for (Map.Entry<String, String> e:result.entrySet()){
			resultEle.addElement("v").addAttribute("name", e.getKey()).setText(e.getValue());
		}
		return root;
	}
	
	@Override
	public void init()
	{
		addAction(new GetSchoolByOrgAction());	
		addAction(new ActiveUserAction());	
		addAction(new ModifyUserAction());	
		 
	}
    

}

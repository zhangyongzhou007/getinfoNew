package com.QuestionBank.mq;

import com.QuestionBank.utils.IQSchema;
import com.star.kafkamq.MQContext;
import com.star.kafkamq.utils.MQMessage;
import com.star.kafkamq.utils.MQReqRespFuture;
import com.star.kafkamq.utils.MessageType;
import org.dom4j.Element;
import org.dom4j.io.XMPPPacketReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;
import org.xmpp.packet.IQ.Type;
import org.xmpp.packet.JID;

import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupMq {
	
	private static Logger logger = LoggerFactory.getLogger(GroupMq.class);
	
	private static GroupMq instance = new GroupMq();
	
	private XMPPPacketReader xmppReader;
	
	private MQContext mqcontext;
	
	public GroupMq() {
		mqcontext = RouterManager.getInstance().getMqcontext();
		xmppReader = new XMPPPacketReader();
	}
	
	public static GroupMq getInstance() {
		return instance;
	}
	
	/**
	 * 获取临时码
	 * @Title: getLoginCode 
	 * @Description: 获取临时码 
	 * @param profileId
	 * @return
	 * @throws Exception String
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public Set<String> getDeptId(String profileId) throws Exception {
		MQMessage queryMsg = mqcontext.createMQMessage(new JID("group@1.y/*"), MessageType.PUSH, null);
	    IQ iq = new IQ();
		iq.setType(Type.get);
     	iq.setFrom(profileId + "@y/web");
		iq.setTo("group@1.y/*");
		Element operate = iq.setChildElement("operate", "jabber:iq:group");
		IQSchema iqSchema = new IQSchema(operate);
		IQSchema.Item item = iqSchema.addItem();
		IQSchema.Item.Table table = item.addTable();
		
		iqSchema.setAttribute("app", "org");
		iqSchema.setAttribute("oper", "get-member-dept-profile");
		table.setAttribute("p", "dept-id");
		table.addElement("h").setText("profile-id");	
		table.addElement("r").setText(profileId);
		
		queryMsg.setContent(iq.toXML());
		MQReqRespFuture future = mqcontext.request(queryMsg);
		future.join();
		
		MQMessage returnMessage = future.getResponse();
	    if (returnMessage == null) {
	    	logger.debug("returnMessage is null...");
	    	return null;   
	    }
	    if (returnMessage.getContent() == null) {
	    	logger.debug("returnMessage.getContent() is null...");
	    	return null; 
	    }
	    
	    Element doc = null;
		try {
			synchronized (this) {
				doc = xmppReader.read(new StringReader(returnMessage.getContent()))
						.getRootElement();
			}
		} catch (Exception e) {
			logger.debug("returnMessage handler error, exception: ", e);
			return null; 
		}
	    
	    IQ iqreturn = new IQ(doc);
    	if (iqreturn.getType().equals(Type.error)) {
    		logger.debug("iqreturn.type is error...");
	    	return null; 
    	}
    	
        Element returnoperate = iqreturn.getChildElement();
		Element returnitem = returnoperate.element("item");
		if (returnitem==null) {
			logger.debug("returnitem is null...");
	    	return null; 
		}
		Element returntable = returnitem.element("table");
		if (returntable==null) {
			logger.debug("returntable is null...");
	    	return null; 
		}
		List<Element> returnrList = returntable.elements("r");
		if (returnrList==null) {
			logger.debug("returnr is null...");
	    	return null; 
		}
		
		Set<String> set = new HashSet<String>();
		for(Element e : returnrList) {
			set.add(e.getText());
		}
		
		return set;
	}
	
	/**
	 * 获取临时码
	 * @Title: getLoginCode 
	 * @Description: 获取临时码 
	 * @param profileId
	 * @return
	 * @throws Exception String
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public Set<String> getPathDeptId(Set<String> deptIds, String profileId) throws Exception {
		MQMessage queryMsg = mqcontext.createMQMessage(new JID("group@1.y/*"), MessageType.PUSH, null);
	    IQ iq = new IQ();
		iq.setType(Type.get);
     	iq.setFrom(profileId + "@y/web");
		iq.setTo("group@1.y/*");
		Element operate = iq.setChildElement("operate", "jabber:iq:group");
		IQSchema iqSchema = new IQSchema(operate);
		IQSchema.Item item = iqSchema.addItem();
		IQSchema.Item.Table table = item.addTable();
		
		iqSchema.setAttribute("app", "org");
		iqSchema.setAttribute("oper", "get-dept");
		table.setAttribute("p", "path");
		table.addElement("h").setText("dept-id");
		if(deptIds != null && deptIds.size() > 0) {
			for(String deptId : deptIds) {
				table.addElement("r").setText(deptId);
			}
		}
		
		queryMsg.setContent(iq.toXML());
		MQReqRespFuture future = mqcontext.request(queryMsg);
		future.join();
		
		MQMessage returnMessage = future.getResponse();
	    if (returnMessage == null) {
	    	logger.debug("returnMessage is null...");
	    	return null;   
	    }
	    if (returnMessage.getContent() == null) {
	    	logger.debug("returnMessage.getContent() is null...");
	    	return null; 
	    }
	    
	    Element doc = null;
		try {
			synchronized (this) {
				doc = xmppReader.read(new StringReader(returnMessage.getContent()))
						.getRootElement();
			}
		} catch (Exception e) {
			logger.debug("returnMessage handler error, exception: ", e);
			return null; 
		}
	    
	    IQ iqreturn = new IQ(doc);
    	if (iqreturn.getType().equals(Type.error)) {
    		logger.debug("iqreturn.type is error...");
	    	return null; 
    	}
    	
        Element returnoperate = iqreturn.getChildElement();
		Element returnitem = returnoperate.element("item");
		if (returnitem==null) {
			logger.debug("returnitem is null...");
	    	return null; 
		}
		Element returntable = returnitem.element("table");
		if (returntable==null) {
			logger.debug("returntable is null...");
	    	return null; 
		}
		List<Element> returnrList = returntable.elements("r");
		if (returnrList==null) {
			logger.debug("returnr is null...");
	    	return null; 
		}
		
		Set<String> set = new HashSet<String>();
		for(Element e : returnrList) {
			set.add(e.getText());
		}
		
		return set;
	}

}

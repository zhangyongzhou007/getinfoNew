package com.QuestionBank.utils;


import com.star.kafkamq.MQContext;
import com.star.kafkamq.MQContextFactory;
import com.star.kafkamq.utils.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;
import org.xmpp.packet.JID;
import org.xmpp.packet.Packet;

import java.io.StringReader;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class XmppRouter implements Module
{
	private static final Logger Log = LoggerFactory.getLogger(XmppRouter.class);

	private Map<String, SubService> subServices = null;

	private String serviceJid;

	/**
	 * 消息路由mq实例
	 */
	private MQContext mqcontext = null;

	MQPullListner pushPulllistner;

	@Override
	public String getName()
	{
		return "moocs.router";
	}

	public void putSubService(SubService service)
	{
		subServices.put(service.getName(), service);
	}

	public void removeSubService(SubService service)
	{
		subServices.remove(service.getName());
	}

	public SubService getSubService(String name)
	{
		return subServices.get(name);
	}

	@Override
	public void initialize(XmppService server)
	{
		subServices = new ConcurrentHashMap<String, SubService>();
		pushPulllistner = new EmmerceMQPullListner();
	}

	@Override
	public void start()
	{
		Log.info("kafka initial");
		String path =this.getClass().getClassLoader().getResource("/").getPath();
		/*if (path.indexOf("/") ==0 ) {
			path =path.substring(1);
		}*/
		System.setProperty("home", path);
//		String mqPath  = path + "mqConfig.xml" ;
		String mqPath  = "conf/mqConfig.xml";
		mqcontext = MQContextFactory.getNewMQContext(mqPath);
		serviceJid = KafkaGeneralUtils.getInstance().getXMLConfigProperties()
				.getProperty("jid");
		Log.info("kafka end");

		mqcontext.setPushPullListener(pushPulllistner);

	}

	@Override
	public void stop()
	{
		if (mqcontext != null)
		{
			mqcontext.stop();
			mqcontext = null;
		}
	}

	@Override
	public void destroy()
	{
		if (mqcontext != null)
		{
			mqcontext.stop();
			mqcontext = null;
		}
	}

	public String getServiveJid()
	{
		return serviceJid;
	}
	
	public MQReqRespFuture request(MQMessage mqMessage)
	{
		MQReqRespFuture future = mqcontext.request(mqMessage);
		return future;
	}
	
	public void route(JID to, Element packet)
	{
		MQMessage mqMessage = mqcontext.createMQMessage(to, MessageType.PUSH,
				UUID.randomUUID().toString());
		mqMessage.setContent(packet.asXML());

		mqcontext.push(mqMessage);
	}
	public void route(JID to, Packet packet)
	{
		MQMessage mqMessage = mqcontext.createMQMessage(to, MessageType.PUSH,
				UUID.randomUUID().toString());
		mqMessage.setContent(packet.toXML());

		mqcontext.push(mqMessage);
	}
	/**
	 * 
	 * @Title: routeSyn 
	 * @Description: 通过消息路由，用同步的方式向其他服务查询 
	 * @param packet
	 * @param to 
	 * @return Packet  
	 * @throws
	 */
	public Document routeSyn(JID to,Element packet)
	{
		// 组装请求
		MQMessage mqMsg = new MQMessage(serviceJid, to.toString(),
				MessageType.PULL);
		mqMsg.setId(UUID.randomUUID().toString());
		mqMsg.setContent(packet.asXML());

		// 2、路由发送请求，接收返回结果
		MQReqRespFuture request = mqcontext.request(mqMsg);
		request.join();
		MQMessage response = request.getResponse();

		if (null == response)
		{
			Log.error("response is null.");
			return null;
		}

		String resultStr = response.getContent();

		if (StringUtils.isNull(resultStr))
		{
			Log.error("response content is null.");
			return null;
		}

		// 3、将结果解析成packet
		XMPPPacketReader reader = new XMPPPacketReader();

		Document doc = null;
		try
		{
			doc = reader.parseDocument(resultStr);
		} catch (Exception e)
		{
			Log.error("parse document failed.", e);
		}
		return doc;
	}

	public void push(MQMessage mqmessage)
	{
		mqcontext.push(mqmessage);
	}
	
	public MQMessage createMessage(JID to, MessageType type, String id)
	{
		return mqcontext.createMQMessage(to, type, id);
	}

	private class EmmerceMQPullListner implements MQPullListner {
		private XMPPPacketReader sxreader;

		public EmmerceMQPullListner() {
			sxreader = new XMPPPacketReader();
		}

		@Override
		public void messageRecived(MQMessage mqMessage) {
			try {
				String content = mqMessage.getContent();
				Log.info("message coming:{}", content);

				Element doc = null;
				try {
					synchronized (this) {
						doc = sxreader.read(new StringReader(content))
								.getRootElement();
					}
				} catch (Exception e) {
					Log.debug("message handler error, exception: ", e);
					return;
				}
				if ("iq".equals(doc.getName())){
					IQ iq = new IQ(doc);
					String servicename;

					Element childElement = iq.getChildElement();
					String namespace = null;
					if (childElement == null)
					{
						Log.debug("message handler error, childElement is null, message: " + iq.toXML());
						return;
					}

					namespace = childElement.getNamespaceURI();

					servicename = namespace.substring(0, namespace.indexOf("."));
					SubService subservice = subServices.get(servicename);
                 
                    if (subservice != null) {
    					Element result = subservice.handler(doc);
    					if (result != null) {
    						Log.debug("message result: {}", result.asXML());
    						MQMessage mqresult = new MQMessage(serviceJid,
    								mqMessage.getFrom(), MessageType.PUSH);
    						mqresult.setId(mqMessage.getId());
    						mqresult.setContent(result.asXML());
        					mqcontext.push(mqresult);
    					}
    				}
				}

			} catch (Exception e) {
				Log.info("messageRecived catch exception: ", e);
			}
		}

	}

}

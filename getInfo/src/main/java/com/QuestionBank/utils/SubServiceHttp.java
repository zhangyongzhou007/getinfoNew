package com.QuestionBank.utils;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SubServiceHttp extends AbstractSubService
{
	private static final Logger Log = LoggerFactory.getLogger(SubServiceHttp.class);
	 
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
		return "http";
	}

	@Override
	public Element handler(Element packet)
	{	
		String action ="";
		 
		 
		Element ecommerce = packet.element("do");
		 
		action = ecommerce.attributeValue("action");
	 
	 
	    return this.getActions().get(action).doAct(packet);
	   //return this.getActions().get(packet.getName()).doAct(packet); 
	}

	
	@Override
	public void init()
	{
		//addAction(new BarCodeLoginAction());	
		 
	//	TaskEngine.getInstance().schedule(new ReturnStockTask(), 20000, 600000);
	}
    
//	class  ReturnStockTask extends TimerTask{
//
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			MQMessage routeMsg =router.createMessage(new JID("auth","1.y","*"), com.star.kafkamq.utils.MessageType.PUSH, null);
//			String content="<token-response><t-type>2</t-type><params><jid>100774833@y/pc_win</jid><pwd>111111</pwd><res>pc_win</res></params></token-response>";
//			  content="<token-change><jid>100774833@y/pc_win</jid><reason-code>111111</reason-code></token-change>";
//			  content="<t-auth><sequence-id>pc_win_00000000000007</sequence-id><device-token>232132312</device-token><jid>100774833@y/pc_win</jid><version-info><version>2.0.0.23345</version><channel-id>aasd</channel-id></version-info><sign>asdads</sign></t-auth>";
//			routeMsg.setContent(content);
//			router.push(routeMsg);
//		}
//		
//	}
}

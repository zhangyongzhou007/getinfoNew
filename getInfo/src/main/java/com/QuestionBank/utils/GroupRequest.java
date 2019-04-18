package com.QuestionBank.utils;


import com.star.kafkamq.utils.KafkaGeneralUtils;
import com.QuestionBank.exception.MException;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;
import org.xmpp.packet.JID;

import java.util.*;

public class GroupRequest {
	private static final Logger log = LoggerFactory.getLogger(GroupRequest.class);
	String[] columnNames = null;
	String[] filters = null;
	List<String[]> rows = new ArrayList<String[]>();
	IQ.Type type = IQ.Type.get;
	String app = "";
	String oper = "";
	String user = "";
	String ver = "";
	String ser = "";
	String serviceJid = "";
	String from = "";
	String[] columnNamesTwo = null;
	List<String[]> rowsTwo = new ArrayList<String[]>();
	public GroupRequest(){
		serviceJid = KafkaGeneralUtils.getInstance().getXMLConfigProperties()
				.getProperty("jid");
	}
	
	public GroupRequest(String app, String oper){
		this();
		this.app = app;
		this.oper = oper;
	}
	
	public String getSer() {
		return ser;
	}

	public void setSer(String ser) {
		this.ser = ser;
	}

	public GroupRequest(IQ.Type type, String app, String oper){
		this();
		this.type = type;
		this.app = app;
		this.oper = oper;
	}
	
	public void setUser(String user){
		this.user = user;
	}
	public void setType(IQ.Type type){
		this.type = type;
	}
	public void setApp(String app){
		this.app = app;
	}
	public void setOper(String oper){
		this.oper = oper;
	}
	public void setVer(String ver){
		this.ver = ver;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setColumnNamesTwo(String... columnNamesTwo) {
		this.columnNamesTwo = columnNamesTwo;
	}

	public void setFilters(String... filters){
		this.filters = filters;
	}
	public void setColumns(String... columnNames){
		this.columnNames = columnNames;
	}
	public void addRow(String... cells){
		rows.add(encode(cells));
	}
	public void addRowTwo(String... cells){
		rowsTwo.add(encode(cells));
	}
	public void addRowsOfOne(List<String> rows){
		for(int i=0;i<rows.size();i++){
			addRow(encode(rows.get(i)));
		}
	}
	
	private String encode(String s)
	{
		if (s == null || s.equals(""))
		{
			return "";
		}
		String s1 = s.replaceAll("&#44", ",");
		s1 = s1.replaceAll("&", "&amp;");//这条顺序不能放后面！！
		s1 = s1.replaceAll("\"", "&quot;");
		s1 = s1.replaceAll("<", "&lt;");
		s1 = s1.replaceAll(">", "&gt;");
		s1 = s1.replaceAll("'", "&#39;");
		return s1;
	}
	private String[] encode(String[] ss)
	{
		for (int i=0;i<ss.length;i++){
			ss[i] = encode(ss[i]);
		}
		return ss;
	}

	
	public String toXml(){
		StringBuilder sb = new StringBuilder();
		String iqFrom = serviceJid;
		if (!from.equals("")){
			iqFrom = from;
		}
		sb.append("<iq")
			.append(" id='").append(new Date().getTime()).append("'")
			.append(" from='").append(iqFrom).append("'")
			.append(" type=").append((type == IQ.Type.get)?"'get'":"'set'")
			.append(">");
		sb.append("<operate xmlns='jabber:iq:group'")
			.append(" app='").append(app).append("'")
			.append(" oper='").append(oper).append("'")
			.append((user.equals("")?"":" QuestionBank='"+user+"'"))
			.append((ver.equals("")?"":" ver='"+ver+"'"))
			.append((ser.equals("")?"":" ser='"+ser+"'"))
			.append(">");
		sb.append("<item>");
		
		sb.append("<table");
		if (filters != null){
			sb.append(" p=")
			.append("'").append(getString(filters)).append("'>");
		}
		else{
			sb.append(">");
		}
		sb.append("<h>");
		sb.append(getString(columnNames));
		sb.append("</h>");
		for(int i=0;i<rows.size();i++){
			sb.append("<r>");
			sb.append(getString(rows.get(i)));
			sb.append("</r>");
		}
		sb.append("</table>");
		if(columnNamesTwo!= null && columnNamesTwo.length>0 && rowsTwo.size()>0){
			sb.append("<table>");
			sb.append("<h>");
			sb.append(getString(columnNamesTwo));
			sb.append("</h>");
			for(int i=0;i<rowsTwo.size();i++){
				sb.append("<r>");
				sb.append(getString(rowsTwo.get(i)));
				sb.append("</r>");
			}
			sb.append("</table>");
		}
		sb.append("</item>");
		sb.append("</operate>");
		sb.append("</iq>");
		
		return sb.toString();
	}
	
	public List<Map<String, String>> send() throws MException
	{
		String xml = this.toXml();
		log.info("send request:"+xml);
		Element el = InteractUtils.parserStrToElement(xml);
		XmppRouter router = XmppService.getInstance().getRouter();
		JID serviceJid = new JID(router.getServiveJid());
		Document resp = router.routeSyn(new JID("group",
				serviceJid.getDomain(), "*"), el);
		log.info("receive response:"+ ((resp == null)?"null":resp.asXML()));
		List<Map<String, String>> result = parseDoc(resp);
		return result;
		
	}
	
	private List<Map<String, String>> parseDoc(Document document) throws MException
	{
		if (null == document)
		{
			throw MException.create500("result packet is null.");
		}

		// 1、获取根节点
		Element root = document.getRootElement();
		String resultType = root.attributeValue("type");
		if ("error".equals(resultType))
		{
			String code = root.element("error").attributeValue("code");
			String desp = root.element("error").attributeValue("desp");
			String text = root.element("error").attributeValue("text");
			log.error("query result is error.code:" + code + ",desp:" + desp + ",text:" + text);
			throw MException.create500("query result is error.code:" + code + ",desp:" + desp + ",text:" + text);
		}

		// 2、解析返回结果状态
		Element itemElement = root.element("operate").element("item");
		String resultCode = itemElement.attributeValue("code");
		String resultDesp = itemElement.attributeValue("desp");
		String resultText = itemElement.attributeValue("text");
		if (StringUtils.isNull(resultCode))
		{
			log.error("item result code is lacked");
			throw MException.create500("item result code is lacked");
		}
		
		if ("0000404".equals(resultCode)){
			log.debug("result is not found");
			return new ArrayList<Map<String, String>>();
		}
		
		if (!"0000000".equals(resultCode)){
			log.error("item result code is "+resultCode);
			throw MException.create500("item result code:"+resultCode+ ",desp:" + resultDesp + ",text:" + resultText);
		}

		// 3、解析返回结果
		Element tableElement = itemElement.element("table");

		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		if (tableElement == null){
			return resultList;
		}
		// 3.1 解析h
		Element hElement = tableElement.element("h");
		String hStr = hElement.getStringValue();
		if (StringUtils.isNull(hStr))
		{
			log.error("h value is null.");
			throw MException.create500("h value is null.");
		}
		String[] hArr = hStr.split(",",-1);
		int hSize = hArr.length;

		// 3.2 解析r
		@SuppressWarnings("rawtypes")
		Iterator iterator = tableElement.elementIterator("r");

		
		Element rElement = null;
		while (iterator.hasNext())
		{
			rElement = (Element) iterator.next();
			String rStr = rElement.getStringValue();
			if (StringUtils.isNull(rStr))
			{
				log.error("r value is null.");
				continue;
			}
			String[] rArr = rStr.split(",",-1);

			Map<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < hSize; i++)
			{
				map.put(hArr[i], rArr[i]);
			}

			resultList.add(map);
		}

		return resultList;
	}
	
	
	private String getString(String... strs){
		if (strs == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<strs.length;i++){
			if (i != 0){
				sb.append(",");
			}
			sb.append(strs[i]);
		}
		return sb.toString();
	}
}

package com.QuestionBank.utils;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;

public class InteractUtils
{
	public static Element parserStrToElement(String str) 
	{
		Element el = null;
		try 
		{
			XMPPPacketReader xreader = new XMPPPacketReader();
			el = xreader.read(new StringReader(str)).getRootElement();
		} 
		catch (DocumentException e) 
		{
			
		}catch (IOException e) 
		{
			
		}catch (XmlPullParserException e) 
		{
			
		}
		return el;
	}
}

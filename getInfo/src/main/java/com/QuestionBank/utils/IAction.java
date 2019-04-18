package com.QuestionBank.utils;

import org.dom4j.Element;

public interface IAction
{
	Element doAct(Element doc);
	
	String getName();
}


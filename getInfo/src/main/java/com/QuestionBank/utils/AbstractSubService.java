package com.QuestionBank.utils;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractSubService implements SubService, Module
{
	private static final Logger Log = LoggerFactory.getLogger(AbstractSubService.class);
	
	private Map<String, IAction> actions = new ConcurrentHashMap<String, IAction>();

	public Map<String, IAction> getActions() {
		return actions;
	}

	private XmppService server;
	
	private XmppRouter router;
	
	@Override
	public void initialize(XmppService server)
	{
		this.server = server;
		init();
	}

	@Override
	public void start()
	{
		router = server.getRouter();
		router.putSubService(this);
	}

	@Override
	public void stop()
	{
		
	}

	@Override
	public void destroy()
	{
		
	}

	@Override
	public Element handler(Element packet)
	{
		 
		 
		String name = packet.getName();
		
		if (null == name)
		{
			Log.error("action name is null. pack: " + packet.asXML());
			return null;
		}
		
		IAction action = actions.get(name);
		
		if (null == action)
		{
			Log.error("can not find action for {}.", name);
			return null;
		}
		
		return action.doAct(packet);
	}
	
	protected void addAction(IAction action)
	{
		actions.put(action.getName(), action);
	}
	
	protected abstract void init();
	
}

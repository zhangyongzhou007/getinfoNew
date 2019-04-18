package com.QuestionBank.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class XmppService
{
	private static final Logger Log = LoggerFactory.getLogger(XmppService.class);

	private static XmppService instance = new XmppService();

	private Map<String, Module> modules = new ConcurrentHashMap<String, Module>();

	private ScheduledThreadPoolExecutor executor = null;

	private ClassLoader loader;

	public static XmppService getInstance()
	{
		return instance;
	}

	public void start()
	{
		executor = new ScheduledThreadPoolExecutor(1);

		executor.scheduleWithFixedDelay(new StartRunnable(), 0, 20, TimeUnit.SECONDS);

		loader = Thread.currentThread().getContextClassLoader();
		
		 
		
		try
		{
			loadModules();
			initialModules();
			startModules();

		}
		catch (Exception e)
		{
			e.printStackTrace();

		}

	}

	public XmppRouter getRouter()
	{
		return (XmppRouter) modules.get(XmppRouter.class.getName());
	}

	private void loadModules()
	{
    	loadModule(XmppRouter.class.getName());
    	loadModule(SubServiceHttp.class.getName());
    	loadModule(SubServiceMoos.class.getName());
	}

	private void startModules()
	{

		for (Module module : modules.values())
		{
			try
			{

				module.start();

			}
			catch (Exception e)
			{
				e.printStackTrace();

				if (module != null)
				{
					module.stop();
					module.destroy();
				}
				Log.error("module start failure:" + module.getName(), e);

			}

		}

	}

	private void loadModule(String module)
	{
		try
		{

			Class modClass = loader.loadClass(module);
			Module mod = (Module) modClass.newInstance();
			this.modules.put(module, mod);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log.error("load module error:" + module, e);
		}
	}

	private void initialModules()
	{
		for (Module module : modules.values())
		{
			boolean isInitialized = false;

			try
			{
				module.initialize(instance);
				isInitialized = true;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				// Remove the failed initialized module
				this.modules.remove(module.getClass().getName());

				if (isInitialized)
				{
					module.stop();
					module.destroy();
				}

				Log.error("module initial failure:" + module.getName(), e);

			}

		}
	}
}

package com.QuestionBank.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class RedisUtils {
	
	Logger logger = Logger.getLogger(RedisUtils.class);
	
	//XMLConfigProperties config;
	
	static JedisSentinelPool jspool = null;
	
	private static RedisUtils instance = new RedisUtils();
	
	private static AtomicBoolean init = new AtomicBoolean(false);
	
	static {
		instance = new RedisUtils();
	}
	
	private RedisUtils(){
		
	}
	
	public static synchronized RedisUtils getInstance(){
		if (init.compareAndSet(false, true)) {
			initialRedis();
		}

		return instance;
	}
	
	public Jedis getJedis()
	{
		if (jspool == null)
		{
			initialRedis();
		}
		Jedis jedis = jspool.getResource();
		return jedis;
	}
	
	private static void initialRedis() {
		String masterAlis = PropertiesUtils.getInstance().getProperty("masterAlis");
		String redishosts = PropertiesUtils.getInstance().getProperty("redishosts");
		String password = PropertiesUtils.getInstance().getProperty("redispassword");
		int index = Integer.parseInt(PropertiesUtils.getInstance().getProperty("dbindex"));
		
		int maxTotal = Integer.parseInt(PropertiesUtils.getInstance().getProperty("maxTotal"));
		int maxIdle = Integer.parseInt(PropertiesUtils.getInstance().getProperty("maxIdle"));
		int maxWaitMillis = Integer.parseInt(PropertiesUtils.getInstance().getProperty("maxWaitMillis"));
		boolean testWhileIdle = Boolean.parseBoolean(PropertiesUtils.getInstance().getProperty("testWhileIdle"));
		
		int timeout = Integer.parseInt(PropertiesUtils.getInstance().getProperty("timeout"));

		//if (jspool == null)
		//{
			String[] redishostsArray = redishosts.split(",");
			Set<String> ses = new HashSet<String>();
			for (String s : redishostsArray)
			{
				ses.add(s);
			}

			GenericObjectPoolConfig pconfig = new GenericObjectPoolConfig();
			pconfig.setMaxTotal(maxTotal);
			pconfig.setMaxIdle(maxIdle);
			pconfig.setMaxWaitMillis(maxWaitMillis);
			pconfig.setTestWhileIdle(testWhileIdle);
			jspool = new JedisSentinelPool(masterAlis, ses, pconfig, timeout, password, index);
		//}
	}

}

package com.fnst.paper.web.interceptor;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * 缓存方法拦截器<br>
 * 参考地址：http://www.cnblogs.com/hoojo/archive/2012/07/12/2587556.html
 */
public class MethodCacheInterceptor implements MethodInterceptor,
		InitializingBean {

	private Logger log = LoggerFactory.getLogger(MethodCacheInterceptor.class);

	private Cache cache;

	public MethodCacheInterceptor() {
		super();
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	/**
	 * 拦截Service/DAO的方法，并查找该结果是否存在，如果存在就返回cache中的值，<br>
	 * 否则，返回数据库查询结果，并将查询结果放入cache
	 */
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// 这个表示哪个类调用（或触发）了这个MethodCacheInterceptor。例如：com.cache.service.UserServiceImpl
		String targetName = invocation.getThis().getClass().getName();
		// 这个表示哪个方法触发了这个类（MethodCacheInterceptor）方法（invoke）的调用，例如：getAllUser
		String methodName = invocation.getMethod().getName();
		// 方法调用的参数
		Object[] arguments = invocation.getArguments();
		Object result;

		String cacheKey = this.getCacheKey(targetName, methodName, arguments);
		Element element = null;
		synchronized (this) {
			element = cache.get(cacheKey);
			if (element == null) {
				log.info(cacheKey + "加入到缓存：" + cache.getName());
				// 调用实际的方法
				result = invocation.proceed();
				element = new Element(cacheKey, (Serializable) result);
				cache.put(element);//放入cache中
			} else {
				log.info(cacheKey + "使用缓存：" + cache.getName());
			}
		}
		return element.getObjectValue();
	}

	/**
	 * 返回具体的方法全路径名称、参数<br>
	 * cache key包括包名+类名+方法名，例如：com.cache.service.UserServiceImpl.getAllUser
	 * 
	 * @param targetName
	 *            全路径
	 * @param methodName
	 *            方法名称
	 * @param arguments
	 *            参数
	 * @return 完整方法名称
	 */
	private String getCacheKey(String targetName, String methodName,
			Object[] arguments) {
		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append(".").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				sb.append(".").append(arguments[i]);
			}
		}
		return sb.toString();
	}

	public void afterPropertiesSet() throws Exception {
		if (null == cache) {
			throw new IllegalArgumentException("Cache should not be null.");
		}
	}

}

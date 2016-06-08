package com.kingdee.ebank.utils;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware{
	private static ApplicationContext applicationContext = null;

	private static Logger logger = LoggerFactory.getLogger(SpringContextUtil.class);

	/**
	 * ȡ�ô洢�ھ�̬�����е�ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return applicationContext;
	}

	/*public static String getRootRealPath(){
		String rootRealPath ="";
		try {
			rootRealPath=getApplicationContext().getResource("").getFile().getAbsolutePath();
		} catch (IOException e) {
			logger.warn("��ȡϵͳ��Ŀ¼ʧ��");
		}
		return rootRealPath;
	}

	public static String getResourceRootRealPath(){
		String rootRealPath ="";
		try {
			rootRealPath=new DefaultResourceLoader().getResource("").getFile().getAbsolutePath();
		} catch (IOException e) {
			logger.warn("��ȡ��Դ��Ŀ¼ʧ��");
		}
		return rootRealPath;
	}*/


	/**
	 * �Ӿ�̬����applicationContext��ȡ��Bean, �Զ�ת��Ϊ����ֵ���������.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * �Ӿ�̬����applicationContext��ȡ��Bean, �Զ�ת��Ϊ����ֵ���������.
	 */
	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}

	/**
	 * ���SpringContextUtil�е�ApplicationContextΪNull.
	 */
	public static void clearHolder() {
		if (logger.isDebugEnabled()){
			logger.debug("���SpringContextUtil�е�ApplicationContext:" + applicationContext);
		}
		applicationContext = null;
	}

	/**
	 * ʵ��ApplicationContextAware�ӿ�, ע��Context����̬������.
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
//		logger.debug("ע��ApplicationContext��SpringContextUtil:{}", applicationContext);

		if (SpringContextUtil.applicationContext != null) {
			logger.info("SpringContextUtil�е�ApplicationContext������, ԭ��ApplicationContextΪ:" + SpringContextUtil.applicationContext);
		}

		SpringContextUtil.applicationContext = applicationContext; // NOSONAR
	}

	/**
	 * ʵ��DisposableBean�ӿ�, ��Context�ر�ʱ����̬����.
	 */
	public void destroy() throws Exception {
		SpringContextUtil.clearHolder();
	}

	/**
	 * ���ApplicationContext��Ϊ��.
	 */
	private static void assertContextInjected() {
		Validate.validState(applicationContext != null, "applicaitonContext����δע��, ����applicationContext.xml�ж���SpringContextUtil.");
	}
}

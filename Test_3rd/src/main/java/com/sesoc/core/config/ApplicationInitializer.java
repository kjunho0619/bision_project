package com.sesoc.core.config;

import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	private final Logger logger = LoggerFactory.getLogger("INFO");
	
	private String activeProfile;
	
	public void onStartup(ServletContext servletContext) throws ServletException{
		this.activeProfile = servletContext.getInitParameter("spring.profiles.active");
		Assert.notNull(this.activeProfile);
		try {
			LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
			context.reset();
			
			URL url = ResourceUtils.getURL("classpath:" + this.activeProfile + "/logback.xml"); 
			
			JoranConfigurator jc = new JoranConfigurator();
			jc.setContext(context);
			jc.doConfigure(url);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		super.onStartup(servletContext);
		 
		logger.info("ACTIVE_PROFIlE = [{}]", this.activeProfile);
		SystemEnvironment.setActiveProfile(this.activeProfile);
		
		servletContext.addListener(RequestContextListener.class);
	}
	
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {ApplicationConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		
		MultipartFilter multipartFilter = new MultipartFilter();
		multipartFilter.setMultipartResolverBeanName("multipartResolver");
		
		HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}
	

}

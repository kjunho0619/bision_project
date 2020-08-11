package com.sesoc.core.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.sesoc.BasePackageMarker;
import com.sesoc.core.env.EnvironmentWrapper;

@Configuration
//프로퍼티 설정
@PropertySource({
	"classpath:${spring.profiles.active:dev}/db.properties",
//	"classpath:message.properties",
//	"classpath:${spring.profiles.active:dev}/xoDefault.properties"
})
//@Import({
//	TraceConfig.class,
//})
@ImportResource({
	"classpath:etc-config.xml",
})
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = {BasePackageMarker.class}, nameGenerator = TestBeanNameGenerator.class)
public class ApplicationConfig extends WebMvcConfigurerAdapter{
	
	private final Logger logger = LoggerFactory.getLogger("INFO");
	
	@Inject
	private Environment env;
	
	public ApplicationConfig() {
		logger.info("{} Init", ApplicationConfig.class);
	}
	
	@PostConstruct
	public void postConstruct() {
		SystemEnvironment.setActiveProfile(env.getActiveProfiles()[0]);
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames(new String[] {"message"});
		
		return messageSource;
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		
		return multipartResolver;
	}
	
	@Bean
	@Inject
	public EnvironmentWrapper environmentWrapper(Environment env) {
		return new EnvironmentWrapper(env);
	}
	
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
	}
	
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		super.configureDefaultServletHandling(configurer);
	}
	
//	@Bean
//	public Validator mvcValidator() {
//		Validator mvcValidator = super.getValidator();
//		
//		if(mvcValidator instanceof InitializingBean) {
//			try {
//				((InitializingBean) mvcValidator).afterPropertiesSet();
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return mvcValidator;
//	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setViewClass(JstlView.class);
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		
		List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
		
		viewResolvers.add(internalResourceViewResolver);
		
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		
		List<View> defaultViews = new ArrayList<View>();
		defaultViews.add(jsonView);
		
		ContentNegotiatingViewResolver viewResolver = new IE89ContentNegotiatingViewResolver();
		
		viewResolver.setViewResolvers(viewResolvers);
		viewResolver.setDefaultViews(defaultViews);
		
		return viewResolver;
	}
	
	//리소스 등록
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/").addResourceLocations("/resources/*");
	}
	
	//인터셉터 등록
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
	}
	
	
}

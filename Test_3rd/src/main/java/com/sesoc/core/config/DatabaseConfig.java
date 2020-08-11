package com.sesoc.core.config;

import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
/**
 * DataBase Configuration
 * </p>
 * @author Lee
 *
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig{
	
	private static final Logger LOGGER = LoggerFactory.getLogger("INFO");
	
//	@Inject
//	Environment env;
	
	/**
	 * 기본_DB JNDI
	 */
	@Bean(name="dataSource")
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		LOGGER.info(env.getProperty("driver"));
//		dataSource.setDriverClassName(env.getProperty("driver"));
//		dataSource.setUrl(env.getProperty("url"));
//		dataSource.setUsername(env.getProperty("user"));
//		dataSource.setPassword(env.getProperty("password"));
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@database-1.ctbeoqa1vcbm.ap-northeast-2.rds.amazonaws.com:1521:ORCL");
		dataSource.setUsername("admin");
		dataSource.setPassword("admin1234");
		
		
		
		return dataSource;
	}
	
	
	@Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryXO(@Qualifier("dataSource") DataSource dataSource) throws Exception {
		PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setConfigLocation(resourceResolver.getResource(getConfigLocation()));
        factoryBean.setMapperLocations(resourceResolver.getResources(getMapperLocationPattern()));
        factoryBean.setConfigurationProperties(getConfigurationProperties());
        return factoryBean.getObject();
    }
	
	@Bean(name="sqlSession")
    public SqlSessionTemplate sqlSessionXO(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
    	return new SqlSessionTemplate(sqlSessionFactory);
    }
	
	/**
	 * XO TransactionManager
	 * @param dataSourceMSR
	 * @return
	 */
	@Bean(name="transactionManager")
	public PlatformTransactionManager transactionManagerXO(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean(name="transactionTemplate")
	public TransactionTemplate transactionTemplateXO(@Qualifier("transactionManager") PlatformTransactionManager transactionManager){
		TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		return transactionTemplate;
	}
	
	/**
	 * 기본 Mapper Scan
	 * package : com.sesoc.**
	 * @return
	 */
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(){
		MapperScannerConfigurer mc = new MapperScannerConfigurer();
		mc.setNameGenerator(new TestBeanNameGenerator());
		mc.setBasePackage("com.sesoc.**");
		mc.setSqlSessionFactoryBeanName("sqlSessionFactory");
		return mc;
	}
	
	protected String getMapperLocationPattern() {
		return "classpath:com/sesoc/**/*Mapper.xml";
	}

	protected String getConfigLocation() {
		return "classpath:mybatis-config.xml";
	}
	
	protected Properties getConfigurationProperties() {
		return null;
	}
}

package com.greenmarket.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;


@Configuration
@EnableWebMvc
@ComponentScan("com.greenmarket.*")
@PropertySource(value= {"classpath:db.properties"})
@EnableTransactionManagement
public class SpringConfig extends WebMvcConfigurerAdapter {

	@Autowired
	Environment enviroment;
	
	//config Stastic resource
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/resources/");
	}
	//configuration propersource
	@Bean
	public static PropertySourcesPlaceholderConfigurer PlaceholderConfigurer()
	{
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	/*configurate tiles3*/
	@Bean
	public TilesConfigurer titleConfigurer()
	{
		TilesConfigurer tilesConfigurer=new TilesConfigurer();
		tilesConfigurer.setDefinitions("classpath:tiles.xml");
		tilesConfigurer.setCheckRefresh(true);
		
		return tilesConfigurer;
	}
	
	@Bean(name = "viewResolver")
    public ViewResolver getViewResolver() {
        TilesViewResolver viewResolver=new TilesViewResolver();
        
        return viewResolver;
    }
	
	@Bean(name="messageSource")
	public MessageSource messageSource()
	{
		ReloadableResourceBundleMessageSource bundleMessageSource=new ReloadableResourceBundleMessageSource();
		bundleMessageSource.addBasenames("classpath:messages");
		bundleMessageSource.setDefaultEncoding("utf-8");
		return bundleMessageSource;
	}
	
	@Bean(name="dataSource")
	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName(enviroment.getProperty("driver"));
		dataSource.setUrl(enviroment.getProperty("url"));
		dataSource.setUsername("root");
		dataSource.setPassword("12345");
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean()
	{
		LocalSessionFactoryBean sessionFactoryBean=new LocalSessionFactoryBean();
	sessionFactoryBean.setDataSource(dataSource());
	sessionFactoryBean.setPackagesToScan(new String[] {"com.greenmarket.entity"});
	
	Properties properties=new Properties();
	properties.put("hibernate.dialect", enviroment.getProperty("hibernate.dialect"));
	properties.put("hibernate.show_sql",enviroment.getProperty("hibernate.show_sql"));
	
	sessionFactoryBean.setHibernateProperties(properties);
	return sessionFactoryBean;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory  sessionFactory)
	{
		HibernateTransactionManager transactionManager=new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		
		return transactionManager;
		
	}
	
	@Bean
	ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
	

}

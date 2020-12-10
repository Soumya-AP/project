package com.springMVC;


import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
 
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.springMVC")
@EnableJpaRepositories(basePackages = {"com.springMVC"})
@EnableTransactionManagement
public class SpringConfiguration implements WebMvcConfigurer {
 
 
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("index");
  }
 
  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver bean = new InternalResourceViewResolver();
 
    bean.setViewClass(JstlView.class);
    bean.setPrefix("/WEB-INF/view/");
    bean.setSuffix(".jsp");
 
    return bean;
  }
  
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
     LocalContainerEntityManagerFactoryBean em 
       = new LocalContainerEntityManagerFactoryBean();
     em.setDataSource(dataSource());
     em.setPackagesToScan(new String[] { "com.springMVC" });

     JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
     em.setJpaVendorAdapter(vendorAdapter);
     em.setJpaProperties(additionalProperties());

     return em;
  }
  @Bean
  public DataSource dataSource(){
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://localhost:3306/Database1");
      dataSource.setUsername( "root" );
      dataSource.setPassword( "MySQLpassword" );
      return dataSource;
  }
  @Bean
  public PlatformTransactionManager transactionManager() {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
   
      return transactionManager;
  }
   
  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
      return new PersistenceExceptionTranslationPostProcessor();
  }
   
  Properties additionalProperties() {
      Properties properties = new Properties();
      properties.setProperty("hibernate.hbm2ddl.auto", "update");
      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
         
      return properties;
  }
  
 
}
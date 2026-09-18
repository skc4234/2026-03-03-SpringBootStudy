package com.sist.web.config;

import java.util.Objects;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class OracleMyBatisConfig {
   
   @Bean(name="oracleSqlSessionFactory")
   public SqlSessionFactory oracleSqlSessionFactory(
      @Qualifier("oracleDataSource") DataSource dataSource
   )throws Exception
   {
	   SqlSessionFactoryBean factory=new SqlSessionFactoryBean();
	   
	   factory.setDataSource(dataSource);
	   
	   PathMatchingResourcePatternResolver resolver =
               new PathMatchingResourcePatternResolver();
	   
	   factory.setMapperLocations(
		  resolver.getResources("classpath*:/mapper/oracle/*.xml")
	   );
	   
	   return factory.getObject();
   }
   @Bean(name="oracleSessionTemplate")
   public SqlSessionTemplate oracleSessionTemplate(
	  @Qualifier("oracleSqlSessionFactory")	  
	  SqlSessionFactory sqlSessionFactory
   ) 
   {
	  return new SqlSessionTemplate(sqlSessionFactory);   
   }
}

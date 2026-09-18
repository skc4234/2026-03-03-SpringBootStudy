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
public class PostgresMyBatisConfig {
	   @Bean(name="postgresSqlSessionFactory")
	   public SqlSessionFactory postgresSqlSessionFactory(
	      @Qualifier("postgresDataSource") DataSource dataSource
	   )throws Exception
	   {
		   SqlSessionFactoryBean factory=new SqlSessionFactoryBean();
		   
		   factory.setDataSource(dataSource);
		   
		  
		   factory.setMapperLocations(
				   new PathMatchingResourcePatternResolver()
				      .getResources("classpath*:/mapper/postgres/*.xml")
		   );
		   
		   return factory.getObject();
	   }
	   @Bean(name="postgresSessionTemplate")
	   public SqlSessionTemplate postgresSessionTemplate(
		  @Qualifier("postgresSqlSessionFactory")	  
		  SqlSessionFactory sqlSessionFactory
	   ) 
	   {
		  return new SqlSessionTemplate(sqlSessionFactory);   
	   }
}

package com.shop.Juliicasta.Config.DB;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.shop.Juliicasta.Config.AES;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {
	@Value("${db.datasource.url}")
	private String urlDB;

	@Value("${db.datasource.driver-class-name}")
	private String driverClassname;

	@Value("${db.datasource.username}")
	private String username;

	@Value("${db.datasource.password}")
	private String password;
	
	private String secretKey = "Aa@123456";

	@Bean(name = "dataSource")
	public DataSource datasource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(AES.decrypt(urlDB, secretKey));
		hikariConfig.setDriverClassName(driverClassname);
		hikariConfig.setUsername(AES.decrypt(username, secretKey));
		hikariConfig.setPassword(AES.decrypt(password, secretKey));

		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

		return hikariDataSource;
	}

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager dataSourceTransactionManager() {
		return new DataSourceTransactionManager(datasource());
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);

		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:com/shop/Juliicasta/Mapper/Sql/*.xml"));
		
		return sqlSessionFactoryBean.getObject();
	}
}

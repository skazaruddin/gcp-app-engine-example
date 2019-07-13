package com.gdg.chatapp.websocket;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
public class WebsocketApplication extends SpringBootServletInitializer {

	@Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		 return application.sources(WebsocketApplication.class);
	  }
	
	public static void main(String[] args) {
		SpringApplication.run(WebsocketApplication.class, args);
	}

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setUsername("postgres");
		config.setPassword("admin");
		config.setAutoCommit(true);
		config.setMaximumPoolSize(12);
		config.setMinimumIdle(5);
		config.setConnectionTimeout(20000); // 20 seconds
		config.setIdleTimeout(600000); // 10 minutes
		config.setJdbcUrl(String.format("jdbc:postgresql:///%s","chat-db"));
		config.setDriverClassName("org.postgresql.Driver");
		config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.postgres.SocketFactory");
		config.addDataSourceProperty("cloudSqlInstance", "bionic-baton-245410:asia-south1:chat-db-dev");
	
		DataSource datasource = new HikariDataSource(config);
		return datasource;
	}
}

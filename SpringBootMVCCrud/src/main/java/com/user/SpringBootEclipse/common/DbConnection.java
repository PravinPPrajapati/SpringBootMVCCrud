package com.user.SpringBootEclipse.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource("file:application.properties")
public class DbConnection {
	
	public Connection con=null; 
	
	// Way-1
	@Value("${spring.datasource.url}")
	String url;
	
	@Value("${spring.datasource.username}")
	String userName;
	
	@Value("${spring.datasource.password}")
	String password;
	
	@Value("${spring.datasource.driver-class-name}")
	String driver;

	// Way-2
	/*
	@Autowired
    private Environment env;
	String url = null;
	String userName = null;
	String password = null;
	String driver = null;
	*/


	public Connection getConnection()
	{
	/*
		url = env.getProperty("spring.datasource.url");
		userName = env.getProperty("spring.datasource.username");
		password = env.getProperty("spring.datasource.password");
		driver = env.getProperty("spring.datasource.driver-class-name");
		*/
		System.out.println(url+userName+password+driver);
		try {
			Class.forName(driver);
			//Class.forName("");
			con=DriverManager.getConnection(url,userName,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	
		return con;
	}
	
	
/*	
	public Connection getConnection()
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("");
			con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/demo","root","root");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return con;
	}
	
	*/
}

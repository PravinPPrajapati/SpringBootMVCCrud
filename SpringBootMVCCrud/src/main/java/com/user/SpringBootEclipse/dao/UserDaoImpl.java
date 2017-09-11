package com.user.SpringBootEclipse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import com.user.SpringBootEclipse.common.DbConnection;
import com.user.SpringBootEclipse.model.User;

@Component
public class UserDaoImpl {
	
	@Autowired
	DbConnection dbConnection = null;
	
	Connection dbConn = null;
	
	public List<User> getAllUsers()
	{
		
		List<User> allUsers = new ArrayList<User>();
		PreparedStatement stmt = null;
		ResultSet result = null ;
		try {
			dbConn = dbConnection.getConnection();
			stmt = dbConn.prepareStatement("SELECT * FROM USER");
			result = stmt.executeQuery();
			
			while(result.next())
			{
				Integer userId = result.getInt("USER_ID");
				String userName = result.getString("USER_NAME");
				String password = result.getString("PASSWORD");
				String email = result.getString("EMAIL");
				User userObj = new User(userId, userName, password, email);
				allUsers.add(userObj);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return allUsers;
	}
	
	public void insertUser(User user)
	{
		try {
			dbConn = dbConnection.getConnection();
			PreparedStatement stmt = dbConn.prepareStatement("INSERT INTO USER (USER_NAME,PASSWORD,EMAIL) VALUES (?,?,?)");
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public User getUser(Integer id){
		User user = null;
		PreparedStatement stmt = null;
		ResultSet result = null ;
		try {
			dbConn = dbConnection.getConnection();
			stmt = dbConn.prepareStatement("SELECT * FROM USER where user_id=?");
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			
			while(result.next())
			{	
				Integer userId = result.getInt("USER_ID");
				String userName = result.getString("USER_NAME");
				String password = result.getString("PASSWORD");
				String email = result.getString("EMAIL");
				user = new User(userId, userName, password, email);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	public User updateUser(User user) {
		// TODO Auto-generated method stub

		try {
			dbConn = dbConnection.getConnection();
			PreparedStatement stmt = dbConn.prepareStatement(" UPDATE USER SET USER_NAME=?, PASSWORD=?, EMAIL=? WHERE USER_ID=? ");
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			stmt.setInt(4, user.getUserId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return user;
	}

	public void deleteUser(int id) {
		try {
			dbConn = dbConnection.getConnection();
			PreparedStatement stmt = dbConn.prepareStatement(" DELETE FROM USER WHERE USER_ID=? ");
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
	}
}

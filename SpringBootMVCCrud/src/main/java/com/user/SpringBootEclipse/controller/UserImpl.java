package com.user.SpringBootEclipse.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import com.user.SpringBootEclipse.common.DbConnection;
import com.user.SpringBootEclipse.dao.UserDaoImpl;
import com.user.SpringBootEclipse.model.User;

@Controller
public class UserImpl {
	@Autowired
	UserDaoImpl userDao = null;
	List<User> allUsers = null;
	
	@RequestMapping("/")
	public String getAllUsers(Model model)
	{
		allUsers = userDao.getAllUsers();
		model.addAttribute("allUsers", allUsers);
        return "manageUser";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(Model model,@RequestParam(value="id", required=false, defaultValue="0") int userId)
	{
		userDao.deleteUser(userId);
		
		allUsers = userDao.getAllUsers();
		model.addAttribute("allUsers", allUsers);
		return "manageUser";
	}
	
	@GetMapping("/insertUser")
	public String insertUserPre(Model model)
	{
		model.addAttribute("user", new User());
		return "insertUser";
	}
	
	@PostMapping(value="/inserUserObject")
	public String insertUser(@ModelAttribute User user, Model model,@RequestParam Map<String,String> paramMap /*, 
			@RequestParam(value="userName") String userName1, @RequestParam(value="password") String password1, @RequestParam(value="userEmail") String email1*/)
	{
		//int id = Integer.parseInt(request.getParameter("id"));
		String userName=paramMap.get("userName");
		String password=paramMap.get("password");
		String email=paramMap.get("email");
		

		//User user = new User(userName,password,email);
		userDao.insertUser(user);
		
		allUsers = userDao.getAllUsers();
		model.addAttribute("allUsers", allUsers);
		return "manageUser";
	}

	@GetMapping("/updateUser")
	public String updateUserPre(Model model,@RequestParam(value="id", required=false, defaultValue="0") int userId)
	{
		User user = userDao.getUser(userId);
		
		model.addAttribute("user", user);
		return "updateUser";
	}
	
	@PostMapping(value="/updateUserObject")
	public String updateUser(@ModelAttribute User user, Model model,@RequestParam Map<String,Object> paramMap /*, 
			@RequestParam(value="userName") String userName1, @RequestParam(value="password") String password1, @RequestParam(value="userEmail") String email1*/)
	{
		Integer id = (Integer) paramMap.get("id");
		String userName=(String) paramMap.get("userName");
		String password=(String) paramMap.get("password");
		String email=(String) paramMap.get("email");
		

		//User user = new User(userName,password,email);
		userDao.updateUser(user);
		
		allUsers = userDao.getAllUsers();
		model.addAttribute("allUsers", allUsers);
		return "manageUser";
	}
	
	/*@PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting) {
        return "result";
    }*/
	
	
	
	/* private static ITemplateResolver htmlTemplateResolver() {
         final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
         templateResolver.setOrder(Integer.valueOf(0));
         templateResolver.setPrefix("classpath:/templates/");
         templateResolver.setSuffix(".jsp");
         templateResolver.setTemplateMode(TemplateResolver.DEFAULT_TEMPLATE_MODE);
         templateResolver.setCharacterEncoding("UTF-8");
         templateResolver.setCacheable(false);
         return templateResolver;
     }*/
}

package com.omg.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.app.bean.User;
import com.omg.app.dao.UserDao;
@Service
public class UserService {
	
	@Autowired
	private UserDao dao;
	
	public boolean changePassword( String userName,String password) {
		//String userName = dao.getUserName(code);
		return dao.changePass(userName, password);

	}
	public List<User> getAllUSers()
	{
		  List<User> users = dao.getAllUSers();
		  return users;
		
		
		
	}
	public User getuser(int id)
	 {
		User user= dao.get(id);
	  return user;
	 }
	
	public int addUser(User user)
	 {
	 int user2= dao.registerUser(user);
	return user2;
	 }
	
	public boolean docheck(String exPass, String currentUserName) {
		return dao.docheck(exPass, currentUserName);

	}
}

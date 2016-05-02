package com.omg.app;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.omg.app.bean.Donor;
import com.omg.app.bean.User;

public class Main {
	public static void main(String[] args)  {
		
		
	/*	
	User user=new User();
	user.setName("ravi");
	user.setPassword("ravi");
	user.setEmail("ravi@gamil.com");
	user.setContactnumber("8679697");
	user.setBloodgroup("a naegtive");
	user.setSex("male");
	user.setLatitude(40.366633);
	user.setLongitude( 74.640832);
	*/
		
		
		Session session=HibernateUtil.getSession();
		
	Transaction	transaction= session.beginTransaction();
	User user=(User) session.get(User.class, 1);
	//System.out.println(user.getUser().getName());
	Donor donor=new Donor();
	donor.setDonordate(new Date());
	donor.setUser(user);
	donor.setBloodgroup(user.getBloodgroup());
	session.save(donor);
	transaction.commit();
	session.close();
	
	}
	
}

package com.omg.app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omg.app.bean.User;
@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public boolean changePass(String email,String password) {
		System.out.println(email+""+password);
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("Update User set password=? where email=?");
		query.setParameter(0, password);
		query.setParameter(1, email);
		int i=query.executeUpdate();
		transaction.commit();

		return i>0?true:false;
		
	}
	
	public List<User> getAllUSers()
	{
		List<User> userDTOs = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			System.out.println("HIIII");
			userDTOs = session
					.createQuery("from User").list();
			transaction.commit();
		} catch (Exception e) {
e.printStackTrace();
		} finally {
			session.close();
		}
		return userDTOs;
		
		
	}
	
	//to login validate
	public boolean docheck(String email,String password) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		/*Query query = session.createQuery("from  User where email =:user and password =:pass");
		query.setParameter("user", email);
		query.setParameter("pass", password);
		//int i=query.list().size();
		query.uniqueResult();
		System.out.println("size"+i);
		transaction.commit();

		return i>0?true:false;*/
		boolean userFound = false;
		//Query using Hibernate Query Language
		String SQL_QUERY =" from User as o where o.email=? and o.password=?";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter(0,email);
		query.setParameter(1,password);
		List list = query.list();

		if ((list != null) && (list.size() > 0)) {
			userFound= true;
			System.out.println(userFound);
		}

		session.close();
		return userFound;
	}

	
	//save user
	public int registerUser(User user)  {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		int i = 0;
		try {
			 i=(Integer) session.save(user);
			//session.save(user.getCompany());
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return i;
	}
	
	//getUSerByID
	public User get(int userId)  {
		User userDTO = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			userDTO = (User) session
					.createQuery(
							"select u.id as id,u.name as name  "
									/*+ "u.lname as lname  "*/
									+ "from User u "
									+ "where  u.id=:uid")
					.setResultTransformer(
							new AliasToBeanResultTransformer(User.class))
					.setParameter("uid", userId).list().get(0);

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return userDTO;
	}
}

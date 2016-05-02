package com.omg.app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omg.app.bean.Seva;
import com.omg.app.bean.User;
@Repository
public class SevaDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	
	public List<Seva> getAllSevas()
	{
		List<Seva> sevaDTOs = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			System.out.println("HIIII");
			sevaDTOs = session
					.createQuery("from Seva").list();
			transaction.commit();
		} catch (Exception e) {
e.printStackTrace();
		} finally {
			session.close();
		}
		return sevaDTOs;
		
		
	}
	
	
	
	//save user
	public int registerSeva(Seva seva)  {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		int i = 0;
		try {
			 i=(Integer) session.save(seva);
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
	public Seva get(int sevaId)  {
		Seva sevaDTO = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			sevaDTO = (Seva) session
					.createQuery(
							"select u.id as id,u.name as name  "
									/*+ "u.lname as lname  "*/
									+ "from Seva u "
									+ "where  u.id=:uid")
					.setResultTransformer(
							new AliasToBeanResultTransformer(Seva.class))
					.setParameter("uid", sevaId).list().get(0);

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return sevaDTO;
	}
}

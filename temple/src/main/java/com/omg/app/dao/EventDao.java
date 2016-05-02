package com.omg.app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omg.app.bean.Event;
import com.omg.app.bean.User;
@Repository
public class EventDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	
	public List<Event> getAllEvents()
	{
		List<Event> eventDTOs = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			System.out.println("HIIII");
			eventDTOs = session
					.createQuery("from Event").list();
			transaction.commit();
		} catch (Exception e) {
e.printStackTrace();
		} finally {
			session.close();
		}
		return eventDTOs;
		
		
	}
	
	
	
	//save user
	public int registerEvent(Event event)  {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		int i = 0;
		try {
			 i=(Integer) session.save(event);
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
	public Event get(int eventId)  {
		Event eventDTO = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			eventDTO = (Event) session
					.createQuery(
							"select u.id as id,u.name as name  "
									/*+ "u.lname as lname  "*/
									+ "from User u "
									+ "where  u.id=:uid")
					.setResultTransformer(
							new AliasToBeanResultTransformer(Event.class))
					.setParameter("uid", eventId).list().get(0);

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return eventDTO;
	}
}

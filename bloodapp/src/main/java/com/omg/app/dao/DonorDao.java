package com.omg.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omg.app.bean.Donor;
import com.omg.app.bean.User;

@Repository
public class DonorDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List<Donor> getAllDonors()
	{
		List<Donor> userDTOs = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			System.out.println("HIIII");
			userDTOs = session
					.createQuery("from Donor").list();
			transaction.commit();
		} catch (Exception e) {
e.printStackTrace();
		} finally {
			session.close();
		}
		return userDTOs;
		
		
	}
	
	
	//save user
		public Donor registerDonor(Donor user)  {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Donor donor=null;
			try {
				 //user=get(Integer.parseInt(user.getUser().toString()));
				//donor.setBloodgroup(user.getBloodgroup());*/
				session.save(user);
				//session.save(user.getCompany());
				transaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}

			return user;
		}
		
		//getUSerByID
		public Donor get(int donorId)  {
			Donor userDTO = null;
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			try {
				userDTO = (Donor) session.get(Donor.class, donorId);
				transaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
			return userDTO;
		}
	
	
}

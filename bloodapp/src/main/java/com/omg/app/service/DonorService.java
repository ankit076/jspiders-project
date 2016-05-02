package com.omg.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.app.bean.Donor;
import com.omg.app.bean.User;
import com.omg.app.dao.DonorDao;

@Service
public class DonorService {
	
	@Autowired
	private DonorDao dao;
	//getalldonor
	public List<Donor> getAllDonors()
	{
		  List<Donor> donors = dao.getAllDonors();
		  return donors;
		
		
		
	}
	//by id
	public Donor getDonor(int id)
	 {
		Donor donor= dao.get(id);
	  return donor;
	 }
	//save donor
	public Donor addDonor(Donor donor)
	 {
		Donor donor2= dao.registerDonor(donor);
	return donor2;
	 }

}

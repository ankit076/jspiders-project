package com.omg.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.app.bean.Seva;
import com.omg.app.dao.SevaDao;
@Service
public class SevaService {
	
	@Autowired
	private SevaDao dao;
	

	public List<Seva> getAllSevas()
	{
		  List<Seva> Sevas = dao.getAllSevas();
		  return Sevas;
		
		
		
	}
	public Seva getSeva(int id)
	 {
		Seva Seva= dao.get(id);
	  return Seva;
	 }
	
	public int addSeva(Seva Seva)
	 {
	 int Seva2= dao.registerSeva(Seva);
	return Seva2;
	 }
	
	
}

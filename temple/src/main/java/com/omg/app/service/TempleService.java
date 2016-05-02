package com.omg.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.app.bean.Temple;
import com.omg.app.dao.TempleDao;
import com.omg.app.dao.TempleDao;
@Service
public class TempleService {
	
	@Autowired
	private TempleDao dao;
	
	
	public List<Temple> getAllTemples()
	{
		  List<Temple> Temples = dao.getAllTemples();
		  return Temples;
		
		
		
	}
	public Temple getTemple(int id)
	 {
		Temple Temple= dao.get(id);
	  return Temple;
	 }
	
	public int addTemple(Temple Temple)
	 {
	 int Temple2= dao.registerTemple(Temple);
	return Temple2;
	 }
	
	
}

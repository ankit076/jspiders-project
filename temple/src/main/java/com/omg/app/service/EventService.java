package com.omg.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.app.bean.Event;
import com.omg.app.dao.EventDao;
@Service
public class EventService {
	
	@Autowired
	private EventDao dao;
	
	
	public List<Event> getAllEvents()
	{
		  List<Event> Events = dao.getAllEvents();
		  return Events;
		
		
		
	}
	public Event getEvent(int id)
	 {
		Event Event= dao.get(id);
	  return Event;
	 }
	
	public int addEvent(Event Event)
	 {
	 int Event2= dao.registerEvent(Event);
	return Event2;
	 }
	
	
}

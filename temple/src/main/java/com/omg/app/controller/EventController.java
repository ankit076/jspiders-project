package com.omg.app.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.omg.app.bean.Event;
import com.omg.app.bean.User;
import com.omg.app.service.EventService;
@RestController
@RequestMapping("event")
public class EventController {
@Autowired
EventService  eventService;
	

	@RequestMapping(value = "/getAllEvents", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Event> getEvents() {
		List<Event> listofusers = eventService.getAllEvents();
		return listofusers;
	}

	@RequestMapping(value = "/geteventbyid/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Event getUserById(@PathVariable int id) {
		return eventService.getEvent(id);
	}

	@RequestMapping(value = "/saveEvent", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	//@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	/*@ResponseStatus(HttpStatus.OK)
	@RequestBody*/
	 //@Produces(MediaType.APPLICATION_JSON)  @ModelAttribute 
	public  ResponseEntity<Event>  saveUser(@RequestBody   Event event,UriComponentsBuilder ucBuilder) {
		System.out.println("jjjjj"+event.getName());		
		int uid= eventService.addEvent(event);
		System.out.println("inside Controller"+uid);
		//return user.toString();
		if(uid==0){
			 HttpHeaders headers = new HttpHeaders();
		        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(event.getId()).toUri());
		        return new ResponseEntity<Event>(headers, HttpStatus.UNAUTHORIZED);
		}
		 HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(event.getId()).toUri());
	        return new ResponseEntity<Event>(headers, HttpStatus.OK);
	}

	/*@RequestMapping(value = "/countries", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Country updateCountry(@RequestBody Country country) {
		return eventService.updateCountry(country);

	}

	@RequestMapping(value = "/country/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteCountry(@PathVariable("id") int id) {
		eventService.deleteCountry(id);

	}	*/
}

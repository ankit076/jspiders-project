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

import com.omg.app.bean.Temple;
import com.omg.app.bean.User;
import com.omg.app.service.TempleService;
import com.omg.app.service.UserService;
@RestController
@RequestMapping("temple")
public class TempleController {
@Autowired
 TempleService  userService;
	

	@RequestMapping(value = "/getAllTemples", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Temple> getTemples() {
		List<Temple> listofusers = userService.getAllTemples();
		return listofusers;
	}

	@RequestMapping(value = "/gettemplebyid/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Temple getTempleById(@PathVariable int id) {
		return userService.getTemple(id);
	}

	@RequestMapping(value = "/savetemple", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	//@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	/*@ResponseStatus(HttpStatus.OK)
	@RequestBody*/
	 //@Produces(MediaType.APPLICATION_JSON)  @ModelAttribute 
	public  ResponseEntity<Temple>  saveTemple(@RequestBody   Temple temple,UriComponentsBuilder ucBuilder) {
		System.out.println("jjjjj"+temple.getName());		
		int uid= userService.addTemple(temple);
		System.out.println("inside Controller"+uid);
		//return user.toString();
		if(uid==0){
			 HttpHeaders headers = new HttpHeaders();
		        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(temple.getId()).toUri());
		        return new ResponseEntity<Temple>(headers, HttpStatus.UNAUTHORIZED);
		}
		 HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(temple.getId()).toUri());
	        return new ResponseEntity<Temple>(headers, HttpStatus.OK);
	}


	/*@RequestMapping(value = "/countries", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Country updateCountry(@RequestBody Country country) {
		return userService.updateCountry(country);

	}

	@RequestMapping(value = "/country/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteCountry(@PathVariable("id") int id) {
		userService.deleteCountry(id);

	}	*/
}

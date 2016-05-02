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

import com.omg.app.bean.Seva;
import com.omg.app.bean.User;
import com.omg.app.service.SevaService;
@RestController
@RequestMapping("seva")
public class SevaController {
@Autowired
SevaService sevaService;
	

	@RequestMapping(value = "/getAllSevas", method = RequestMethod.GET, headers = "Accept=application/json")
	public List< Seva> getSevas() {
		List<Seva> listofusers = sevaService.getAllSevas();
		return listofusers;
	}

	@RequestMapping(value = "/getsevabyid/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public  Seva getSevaById(@PathVariable int id) {
		return sevaService.getSeva(id);
	}

	@RequestMapping(value = "/saveseva", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	//@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	/*@ResponseStatus(HttpStatus.OK)
	@RequestBody*/
	 //@Produces(MediaType.APPLICATION_JSON)  @ModelAttribute 
	public  ResponseEntity<Seva>  saveSeva(@RequestBody    Seva  seva,UriComponentsBuilder ucBuilder) {
		System.out.println("jjjjj"+seva.getName());		
		int uid= sevaService.addSeva(seva);
		System.out.println("inside Controller"+uid);
		//return user.toString();
		if(uid==0){
			 HttpHeaders headers = new HttpHeaders();
		        headers.setLocation(ucBuilder.path("/saveseva/{id}").buildAndExpand(seva.getId()).toUri());
		        return new ResponseEntity<Seva>(headers, HttpStatus.UNAUTHORIZED);
		}
		 HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/saveseva/{id}").buildAndExpand(seva.getId()).toUri());
	        return new ResponseEntity<Seva>(headers, HttpStatus.OK);
	}

	
	
	/*@RequestMapping(value = "/countries", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Country updateCountry(@RequestBody Country country) {
		return sevaService.updateCountry(country);

	}

	@RequestMapping(value = "/country/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteCountry(@PathVariable("id") int id) {
		sevaService.deleteCountry(id);

	}	*/
}

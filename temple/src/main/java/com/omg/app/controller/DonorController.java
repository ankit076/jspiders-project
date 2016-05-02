/*package com.omg.app.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.omg.app.bean.Donor;
import com.omg.app.bean.User;
import com.omg.app.service.DonorService;
import com.omg.app.service.UserService;

@RestController
@RequestMapping(value="donor")
public class DonorController {
	
	@Autowired
	DonorService donorService;
	
	
	@RequestMapping(value = "/getAllDonors", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Donor> getUSers() {
		List<Donor> listofusers = donorService.getAllDonors();
		return listofusers;
	}

	@RequestMapping(value = "/getdonorbyid/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Donor getUserById(@PathVariable int id) {
		return donorService.getDonor(id);
	}

	@RequestMapping(value = "/savedonor", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	//@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseStatus(HttpStatus.OK)
	@RequestBody
	 //@Produces(MediaType.APPLICATION_JSON)  @ModelAttribute 
	public  ResponseEntity<Donor>  saveUser(@RequestBody   Donor donor,UriComponentsBuilder ucBuilder) {
	//	System.out.println("jjjjj"+donor.getUser().getId());		
		donorService.addDonor(donor);
		//return user.toString();
		 HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/donor/{id}").buildAndExpand(donor.getId()).toUri());
	        return new ResponseEntity<Donor>(headers, HttpStatus.CREATED);
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Boolean> loginUser(@RequestBody  User user,UriComponentsBuilder ucBuilder) {
		System.out.println("jjjjj"+user.getEmail());		

		boolean b= userService.docheck(user.getEmail(),user.getPassword());
		System.out.println("uuuuuuuuuuu"+Boolean.toString(b));		

		 HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/login/}"+Boolean.toString(b)).buildAndExpand(Boolean.toString(b)).toUri());
if(b==true)
{
	        //headers.set("value", Boolean.toString(b));
	        return new ResponseEntity<Boolean>(headers, HttpStatus.OK);
}
return new ResponseEntity<Boolean>(headers, HttpStatus.UNAUTHORIZED);

}

}
*/
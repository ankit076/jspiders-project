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

import com.omg.app.bean.User;
import com.omg.app.service.UserService;
@RestController
@RequestMapping("user")
public class UserController {
@Autowired
	UserService  userService;
	
@RequestMapping(value = "/change_password", method = RequestMethod.POST)
public ResponseEntity<User> changePassword(@RequestBody User password){
	System.out.println(password);
	if (userService.changePassword(password.getEmail(),
			password.getPassword())) {
		return new ResponseEntity<User>(HttpStatus.OK);
	} else 
		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "/getAllusers", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<User> getUSers() {
		List<User> listofusers = userService.getAllUSers();
		return listofusers;
	}

	@RequestMapping(value = "/getuserbyid/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public User getUserById(@PathVariable int id) {
		return userService.getuser(id);
	}

	@RequestMapping(value = "/saveuser", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	//@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	/*@ResponseStatus(HttpStatus.OK)
	@RequestBody*/
	 //@Produces(MediaType.APPLICATION_JSON)  @ModelAttribute 
	public  ResponseEntity<User>  saveUser(@RequestBody   User user,UriComponentsBuilder ucBuilder) {
		System.out.println("jjjjj"+user.getName());		
		int uid= userService.addUser(user);
		System.out.println("inside Controller"+uid);
		//return user.toString();
		if(uid==0){
			 HttpHeaders headers = new HttpHeaders();
		        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
		        return new ResponseEntity<User>(headers, HttpStatus.UNAUTHORIZED);
		}
		 HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
	        return new ResponseEntity<User>(headers, HttpStatus.OK);
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
	/*@RequestMapping(value = "/countries", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Country updateCountry(@RequestBody Country country) {
		return userService.updateCountry(country);

	}

	@RequestMapping(value = "/country/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteCountry(@PathVariable("id") int id) {
		userService.deleteCountry(id);

	}	*/
}

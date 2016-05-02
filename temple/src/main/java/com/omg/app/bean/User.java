package com.omg.app.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.omg.app.util.CustomDateDeserializer;
import com.omg.app.util.CustomDateSerializer;


@Entity
@XmlRootElement
public class User implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String password;
	@Column(name = "email",nullable=false,unique=true,updatable=false)
		private String email;
	private String contactnumber;
	  
	//@Column(name = "SEX")
	private String sex;
	
	
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}
	
	
	
	/*public User() {
		// TODO Auto-generated constructor stub
	}
	public User( String name, String password, String email,
			String contactnumber, String bloodgroup) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.contactnumber = contactnumber;
		this.bloodgroup = bloodgroup;
	}
	public User(String password, String email) {
		super();
		this.password = password;
		this.email = email;
	}*/
	
	 @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        User other = (User) obj;
	        if (id != other.id)
	            return false;
	        return true;
	    }
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", email=" + email + ", contactnumber=" + contactnumber
				+ ", sex=" + sex + "]";
	}
	
	
	
	

}

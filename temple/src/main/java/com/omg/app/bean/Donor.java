/*package com.omg.app.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.omg.app.util.CustomDateDeserializer;
import com.omg.app.util.CustomDateSerializer;
@Entity
@Table(name="donor_table")
@XmlRootElement
public class Donor  implements Serializable{
	@Id
	@GeneratedValue
	private int id;
	@Temporal(TemporalType.DATE)
	@org.codehaus.jackson.map.annotate.JsonSerialize(using = CustomDateSerializer.class)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@Column(name = "donordate")
	private Date donordate;
	private String bloodgroup;

	@ManyToOne(cascade=CascadeType.ALL)
	private User user;
	
	
	public String getBloodgroup() {
		return bloodgroup;
	}
	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDonordate() {
		return donordate;
	}
	public void setDonordate(Date donordate) {
		this.donordate = donordate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

}
*/
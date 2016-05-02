package com.omg.app.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.omg.app.util.CustomDateDeserializer;
import com.omg.app.util.CustomDateSerializer;

@Entity
@Table(name="seva_information")
@XmlRootElement

public class Seva implements Serializable{
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@Temporal(TemporalType.DATE)
	@org.codehaus.jackson.map.annotate.JsonSerialize(using = CustomDateSerializer.class)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@Column(name = "sevadate")
	private Date sevadate;
	@ManyToOne(cascade=CascadeType.ALL)
	private Temple temple;
	
	public Temple getTemple() {
		return temple;
	}
	public void setTemple(Temple temple) {
		this.temple = temple;
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
	public Date getSevadate() {
		return sevadate;
	}
	public void setSevadate(Date sevadate) {
		this.sevadate = sevadate;
	}
	
	

}

package com.truyentranh.webtruyen.products.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.truyentranh.webtruyen.parameters.models.Client;
import com.truyentranh.webtruyen.parameters.models.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryHire {
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="storyid", insertable=false, updatable=false)
	private Story story;
	private Integer storyid;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date dateOut;
	
	private String timeOut;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date dateIn;
	
	private String timeIn;
	
	@ManyToOne
	@JoinColumn(name="clientid", insertable=false, updatable=false)
	private Client client;
	private Integer clientid;
	
	@ManyToOne
	@JoinColumn(name="locationid", insertable=false, updatable=false)
	private Location location;
	private Integer locationid;
	
	private String price;
	
	private String remarks;

	public StoryHire(int id, Story story, Integer storyid, Date dateOut, String timeOut, Date dateIn, String timeIn,
			Client client, Integer clientid, Location location, Integer locationid, String price, String remarks) {
		super();
		this.id = id;
		this.story = story;
		this.storyid = storyid;
		this.dateOut = dateOut;
		this.timeOut = timeOut;
		this.dateIn = dateIn;
		this.timeIn = timeIn;
		this.client = client;
		this.clientid = clientid;
		this.location = location;
		this.locationid = locationid;
		this.price = price;
		this.remarks = remarks;
	}

	public StoryHire() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public Integer getStoryid() {
		return storyid;
	}

	public void setStoryid(Integer storyid) {
		this.storyid = storyid;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public String getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(String timeIn) {
		this.timeIn = timeIn;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Integer getClientid() {
		return clientid;
	}

	public void setClientid(Integer clientid) {
		this.clientid = clientid;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Integer getLocationid() {
		return locationid;
	}

	public void setLocationid(Integer locationid) {
		this.locationid = locationid;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}


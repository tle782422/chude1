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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.truyentranh.webtruyen.hr.models.Employee;
import com.truyentranh.webtruyen.parameters.models.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Story {
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	private String name;
	@ManyToOne
	@JoinColumn(name="storytypeid", insertable=false, updatable=false)
	private StoryType storyType;
	private Integer storytypeid;	
	
	private String storynumber;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date registrationDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date acquisitionDate;
	private String description;
	
	@ManyToOne
	@JoinColumn(name="storymakeid", insertable=false, updatable=false)
	private StoryMake storyMake;
	private Integer storymakeid;
	
	private String booklet;
	private String storyimage;
	@ManyToOne
	@JoinColumn(name="storystatusid", insertable=false, updatable=false)
	private StoryStatus storyStatus;
	private Integer storystatusid;	
	
	private String author;

	@ManyToOne
	@JoinColumn(name="employeeid", insertable=false, updatable=false)
	private Employee inCharge;
	private Integer employeeid;
	
	@ManyToOne
	@JoinColumn(name="storymodelid", insertable=false, updatable=false)
	private StoryModel storyModel;	
	private Integer storymodelid;

	@ManyToOne
	@JoinColumn(name="locationid", insertable=false, updatable=false)	
	private Location currentLocation;
	private Integer locationid;
	
	private String remarks;

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

	public StoryType getStoryType() {
		return storyType;
	}

	public void setStoryType(StoryType storyType) {
		this.storyType = storyType;
	}

	public Integer getStorytypeid() {
		return storytypeid;
	}

	public void setStorytypeid(Integer storytypeid) {
		this.storytypeid = storytypeid;
	}

	public String getStorynumber() {
		return storynumber;
	}

	public void setStorynumber(String storynumber) {
		this.storynumber = storynumber;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getAcquisitionDate() {
		return acquisitionDate;
	}

	public void setAcquisitionDate(Date acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StoryMake getStoryMake() {
		return storyMake;
	}

	public void setStoryMake(StoryMake storyMake) {
		this.storyMake = storyMake;
	}

	public Integer getStorymakeid() {
		return storymakeid;
	}

	public void setStorymakeid(Integer storymakeid) {
		this.storymakeid = storymakeid;
	}

	public String getBooklet() {
		return booklet;
	}

	public void setBooklet(String booklet) {
		this.booklet = booklet;
	}

	public String getStoryimage() {
		return storyimage;
	}

	public void setStoryimage(String storyimage) {
		this.storyimage = storyimage;
	}

	public StoryStatus getStoryStatus() {
		return storyStatus;
	}

	public void setStoryStatus(StoryStatus storyStatus) {
		this.storyStatus = storyStatus;
	}

	public Integer getStorystatusid() {
		return storystatusid;
	}

	public void setStorystatusid(Integer storystatusid) {
		this.storystatusid = storystatusid;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Employee getInCharge() {
		return inCharge;
	}

	public void setInCharge(Employee inCharge) {
		this.inCharge = inCharge;
	}

	public Integer getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}

	public StoryModel getStoryModel() {
		return storyModel;
	}

	public void setStoryModel(StoryModel storyModel) {
		this.storyModel = storyModel;
	}

	public Integer getStorymodelid() {
		return storymodelid;
	}

	public void setStorymodelid(Integer storymodelid) {
		this.storymodelid = storymodelid;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	public Integer getLocationid() {
		return locationid;
	}

	public void setLocationid(Integer locationid) {
		this.locationid = locationid;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
}


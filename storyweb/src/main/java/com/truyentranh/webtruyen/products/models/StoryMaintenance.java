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
import com.truyentranh.webtruyen.parameters.models.Supplier;
import com.truyentranh.webtruyen.security.models.Auditable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class StoryMaintenance extends Auditable<String> {
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="storyid", insertable=false, updatable=false)
	private Story story;
	private Integer storyid;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	private String price;
	
	@ManyToOne
	@JoinColumn(name="supplierid", insertable=false, updatable=false)
	private Supplier supplier;
	private Integer supplierid;
	
	private String remarks;

	public StoryMaintenance(int id, Story story, Integer storyid, Date startDate, Date endDate, String price,
			Supplier supplier, Integer supplierid, String remarks) {
		super();
		this.id = id;
		this.story = story;
		this.storyid = storyid;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.supplier = supplier;
		this.supplierid = supplierid;
		this.remarks = remarks;
	}

	public StoryMaintenance() {
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Integer getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(Integer supplierid) {
		this.supplierid = supplierid;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}


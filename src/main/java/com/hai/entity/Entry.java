package com.hai.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "entries", schema = "blog")
public class Entry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "en_id")
	private Integer id;

	@Column(name = "title")
	private String title;

	@Column(name = "text")
	private String text;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	public Entry() {

	}

	public Entry(String title, String text) {
		this.title = title;
		this.text = text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

}

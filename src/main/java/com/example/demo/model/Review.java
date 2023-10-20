package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Review")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reviweId;
	private Integer rating;

	private String comment;
	private Date reviweDate;

	@ManyToOne
	@JoinColumn(name = "userId")
	private UserModel user;

	@ManyToOne
	@JoinColumn(name = "animeID")
	private Anime anime;

	public Review() {
		super();
	}

	public Review(Integer reviweId, Integer rating, String comment, Date reviweDate, UserModel user, Anime anime) {
		super();
		this.reviweId = reviweId;
		this.rating = rating;
		this.comment = comment;
		this.reviweDate = reviweDate;
		this.user = user;
		this.anime = anime;
	}

	public Integer getReviweId() {
		return reviweId;
	}

	public void setReviweId(Integer reviweId) {
		this.reviweId = reviweId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getReviweDate() {
		return reviweDate;
	}

	public void setReviweDate(Date reviweDate) {
		this.reviweDate = reviweDate;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public Anime getAnime() {
		return anime;
	}

	public void setAnime(Anime anime) {
		this.anime = anime;
	}



}

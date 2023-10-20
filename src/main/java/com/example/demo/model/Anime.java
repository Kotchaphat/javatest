package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Anime")
public class Anime {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer animeID;
	private String animename;
	private String animesynopsis;
	private String animeType;
	private String animeyear;
	private String animephoto;
	private String animestudio;
	private Float adminRating;
	private Float averageRating;
	
	@Lob
	@Column(length = 3048576)
	private byte[] photoData;

	@ManyToOne
	@JoinColumn(name = "userID")
	private UserModel user;

	public Anime() {
		super();
	}

	public Anime(Integer animeID, String animename, String animesynopsis, String animeType, String animeyear,
			String animephoto, String animestudio, Float adminRating, Float averageRating, byte[] photoData,
			UserModel user) {
		super();
		this.animeID = animeID;
		this.animename = animename;
		this.animesynopsis = animesynopsis;
		this.animeType = animeType;
		this.animeyear = animeyear;
		this.animephoto = animephoto;
		this.animestudio = animestudio;
		this.adminRating = adminRating;
		this.averageRating = averageRating;
		this.photoData = photoData;
		this.user = user;
	}

	public Integer getAnimeID() {
		return animeID;
	}

	public void setAnimeID(Integer animeID) {
		this.animeID = animeID;
	}

	public String getAnimename() {
		return animename;
	}

	public void setAnimename(String animename) {
		this.animename = animename;
	}

	public String getAnimesynopsis() {
		return animesynopsis;
	}

	public void setAnimesynopsis(String animesynopsis) {
		this.animesynopsis = animesynopsis;
	}

	public String getAnimeType() {
		return animeType;
	}

	public void setAnimeType(String animeType) {
		this.animeType = animeType;
	}

	public String getAnimeyear() {
		return animeyear;
	}

	public void setAnimeyear(String animeyear) {
		this.animeyear = animeyear;
	}

	public String getAnimephoto() {
		return animephoto;
	}

	public void setAnimephoto(String animephoto) {
		this.animephoto = animephoto;
	}

	public String getAnimestudio() {
		return animestudio;
	}

	public void setAnimestudio(String animestudio) {
		this.animestudio = animestudio;
	}

	public Float getAdminRating() {
		return adminRating;
	}

	public void setAdminRating(Float adminRating) {
		this.adminRating = adminRating;
	}

	public Float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Float averageRating) {
		this.averageRating = averageRating;
	}

	public byte[] getPhotoData() {
		return photoData;
	}

	public void setPhotoData(byte[] photoData) {
		this.photoData = photoData;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	

}

package com.clinivapps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "c_display_picture")
public class DisplayPictureEntity{

	private Integer dpId;
	private String imageUrl;

	public DisplayPictureEntity() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", nullable = false)
	public Integer getDpId() {
		return this.dpId;
	}

	public void setDpId(Integer dpId) {
		this.dpId = dpId;
	}


	@Column(name="IMAGE_URL")
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}

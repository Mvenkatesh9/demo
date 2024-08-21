package com.clinivapps.model;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

public class TherapeuticModel {

	private Integer therapeuticId;
	private String therapeuticName;
	private String therapeuticDesc;
	
	public Integer getTherapeuticId() {
		return this.therapeuticId;
	}

	public void setTherapeuticId(Integer therapeuticId) {
		this.therapeuticId = therapeuticId;
	}
	public String getTherapeuticName() {
		return this.therapeuticName;
	}

	public void setTherapeuticName(String therapeuticName) {
		this.therapeuticName = therapeuticName;
	}

	public String getTherapeuticDesc() {
		return this.therapeuticDesc;
	}

	public void setTherapeuticDesc(String therapeuticDesc) {
		this.therapeuticDesc = therapeuticDesc;
	}
	
}

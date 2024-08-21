package com.clinivapps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "c_therapeutic_area")
public class TherapeuticAreaEntity implements java.io.Serializable {

	private Integer therapeuticId;
	private String therapeuticName;
	private String therapeuticDesc;

	public TherapeuticAreaEntity() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", nullable = false)
	public Integer getTherapeuticId() {
		return this.therapeuticId;
	}

	public void setTherapeuticId(Integer therapeuticId) {
		this.therapeuticId = therapeuticId;
	}

	@Column(name = "NAME")
	public String getTherapeuticName() {
		return this.therapeuticName;
	}

	public void setTherapeuticName(String therapeuticName) {
		this.therapeuticName = therapeuticName;
	}

	@Column(name = "DESCRIPTION")
	public String getTherapeuticDesc() {
		return this.therapeuticDesc;
	}

	public void setTherapeuticDesc(String therapeuticDesc) {
		this.therapeuticDesc = therapeuticDesc;
	}

}

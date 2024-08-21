package com.clinivapps.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "c_log_report")
public class LogReportEntity {
	private Integer id;
	private Integer userId;
	private String activity;
	private String description;
	private String trialParticipant;
	private String originalAnswer;
	private String modifiedAnswer;
	private Integer questionId;
	private Date createdDate;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name = "USER_ID")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "ACTIVITY")
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "PARTICIPANT_ID")
	public String getTrialParticipant() {
		return trialParticipant;
	}
	public void setTrialParticipant(String trialParticipant) {
		this.trialParticipant = trialParticipant;
	}
	@Column(name = "ORIGINAL_ANSWER")
	public String getOriginalAnswer() {
		return originalAnswer;
	}
	public void setOriginalAnswer(String originalAnswer) {
		this.originalAnswer = originalAnswer;
	}
	@Column(name = "MODIFIED_ANSWER")
	public String getModifiedAnswer() {
		return modifiedAnswer;
	}
	public void setModifiedAnswer(String modifiedAnswer) {
		this.modifiedAnswer = modifiedAnswer;
	}
	@Column(name = "QUESTION_ID")
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	
	
	
}

package com.clinivapps.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "c_query_details")
public class QueryDetailsEntity {

	private Integer id;
	private QueriesEntity queryEnt;  
	private Integer updatedUserId;
	private Integer questionId;
	private String status;
	private String originalAnswer;
	private String modifiedAnswer;
	private String updatedFrom;
	private String comments;
	private Date createdDate;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "QUESTION_ID")
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="QUERY_ID",nullable=false)
	public QueriesEntity getQueryEnt() {
		return queryEnt;
	}
	public void setQueryEnt(QueriesEntity queryEnt) {
		this.queryEnt = queryEnt;
	}
	@Column(name = "UPDATED_USER_ID")
	public Integer getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(Integer updatedUserId) {
		this.updatedUserId = updatedUserId;
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
	@Column(name = "UPDATE_FROM")
	public String getUpdatedFrom() {
		return updatedFrom;
	}

	public void setUpdatedFrom(String updatedFrom) {
		this.updatedFrom = updatedFrom;
	}
	@Column(name = "COMMENTS")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
}

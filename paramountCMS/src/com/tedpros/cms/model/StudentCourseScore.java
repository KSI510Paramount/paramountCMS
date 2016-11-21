package com.tedpros.cms.model;

import java.io.Serializable;
import java.util.Date;
public class StudentCourseScore implements Serializable {

	private static final long serialVersionUID = 2131514417385238149L;
	private String scoreId;
	private String studentId;
	private String offerId;
	private String scoreTypeId;
	private String scoreValue;
	private Date entryDate;
	private Date updateDate;

	public String getScoreId() {
		return scoreId;
	}

	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getScoreTypeId() {
		return scoreTypeId;
	}

	public void setScoreTypeId(String scoreTypeId) {
		this.scoreTypeId = scoreTypeId;
	}

	public String getScoreValue() {
		return scoreValue;
	}

	public void setScoreValue(String scoreValue) {
		this.scoreValue = scoreValue;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}

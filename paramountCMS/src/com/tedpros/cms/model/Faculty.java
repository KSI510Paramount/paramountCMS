package com.tedpros.cms.model;

import java.io.Serializable;
import java.util.Date;

public class Faculty implements Serializable {

	private static final long serialVersionUID = -8428316994129462285L;
	private String facultyId;
	private String facultyPrefix;
	private String facultyName;
	private String facultyLocation;
	private Date entryDate;
	private Date updateDate;

	public String getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyPrefix() {
		return facultyPrefix;
	}

	public void setFacultyPrefix(String facultyPrefix) {
		this.facultyPrefix = facultyPrefix;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getFacultyLocation() {
		return facultyLocation;
	}

	public void setFacultyLocation(String facultyLocation) {
		this.facultyLocation = facultyLocation;
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

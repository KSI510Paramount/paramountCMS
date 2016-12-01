package com.tedpros.cms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class DomainEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="ROW_CREATED_BY", nullable=false, updatable=false, length=100, columnDefinition="nchar(100)")
	private String rowCreatedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ROW_CREATED_DATETIME", nullable=false, updatable=false)
	private Date rowCreatedDateTime;
	
	@Column(name="ROW_LAST_CHANGED_BY", nullable=false, length=100, columnDefinition="nchar(100)")
	private String rowLastChangedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ROW_LAST_CHANGED_DATETIME", nullable=false)
	private Date rowLastChangedDateTime;
	
	public abstract Long getObjectid();

	public abstract void setObjectid(Long objectid);

	public String getRowCreatedBy() {
		return rowCreatedBy;
	}

	public void setRowCreatedBy(String rowCreatedBy) {
		this.rowCreatedBy = rowCreatedBy;
	}

	public Date getRowCreatedDateTime() {
		return rowCreatedDateTime;
	}

	public void setRowCreatedDateTime(Date rowCreatedDateTime) {
		this.rowCreatedDateTime = rowCreatedDateTime;
	}

	public String getRowLastChangedBy() {
		return rowLastChangedBy;
	}

	public void setRowLastChangedBy(String rowLastChangedBy) {
		this.rowLastChangedBy = rowLastChangedBy;
	}

	public Date getRowLastChangedDateTime() {
		return rowLastChangedDateTime;
	}

	public void setRowLastChangedDateTime(Date rowLastChangedDateTime) {
		this.rowLastChangedDateTime = rowLastChangedDateTime;
	}

	public void prePersist(){
		Date currentDate = new Date();
		setRowCreatedBy("ADMIN");
		setRowCreatedDateTime(currentDate);
		setRowLastChangedBy("ADMIN");
		setRowLastChangedDateTime(currentDate);
	}
	
	public void preUpdate(){
		Date currentDate = new Date();
		setRowLastChangedBy("ADMIN");
		setRowLastChangedDateTime(currentDate);
	}
}

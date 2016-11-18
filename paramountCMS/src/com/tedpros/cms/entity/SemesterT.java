package com.tedpros.cms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="SEMESTER_T",schema="CMSWEB")
@NamedQueries({
	@NamedQuery(name="SemesterT.findAll", query="Select s from SemesterT s")
})

public class SemesterT extends DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "OBJECTID", unique=true, nullable=false, updatable=false, precision=18)
	private Long objectid;

   @Column(name = "SEMESTER_YEAR")
   private Integer semesterYear;
   
   @ManyToOne
   @NotFound(action=NotFoundAction.IGNORE)
   @JoinColumn(name = "SEMESTER_TYPE_OID")
   private CodeValueT semesterTypeOid;
   
   @Temporal(TemporalType.DATE)
   @Column(name = "SEM_START_DATE")
   private Date semStartDate;  
   
   @Temporal(TemporalType.DATE)
   @Column(name = "SEM_END_DATE")
   private Date semEndDate;  
   
	public Long getObjectid() {
		return objectid;
	}
	
	public void setObjectid(Long objectid) {
		this.objectid = objectid;
	}

	public Integer getSemesterYear() {
		return semesterYear;
	}

	public void setSemesterYear(Integer semesterYear) {
		this.semesterYear = semesterYear;
	}

	public CodeValueT getSemesterTypeOid() {
		return semesterTypeOid;
	}

	public void setSemesterTypeOid(CodeValueT semesterTypeOid) {
		this.semesterTypeOid = semesterTypeOid;
	}

	public Date getSemStartDate() {
		return semStartDate;
	}

	public void setSemStartDate(Date semStartDate) {
		this.semStartDate = semStartDate;
	}

	public Date getSemEndDate() {
		return semEndDate;
	}

	public void setSemEndDate(Date semEndDate) {
		this.semEndDate = semEndDate;
	}
	
}

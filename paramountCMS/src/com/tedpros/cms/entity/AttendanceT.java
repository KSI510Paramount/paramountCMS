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
@Table(name="ATTENDANCE_T",schema="CMSWEB")
@NamedQueries({
	@NamedQuery(name="AttendanceT.findAll", query="Select a from AttendanceT a"),
	@NamedQuery(name="AttendanceT.findByCourseOfferOid", query="Select a from AttendanceT a WHERE a.enrollmentOid.courseOfferOid.objectid = :courseOfferOid")
})

public class AttendanceT extends DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "OBJECTID", unique=true, nullable=false, updatable=false, precision=18)
	private Long objectid;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "ENROLLMENT_OID")
	private EnrollmentT enrollmentOid;
	
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "ATTEN_TYPE_OID")
	private CodeValueT attenTypeOid;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ATTENDANCE_DATE")
	private Date attendanceDate;
	
	@Column(name = "COMMENTS")
	private String comments;

	public Long getObjectid() {
		return objectid;
	}
	
	public void setObjectid(Long objectid) {
		this.objectid = objectid;
	}

	public EnrollmentT getEnrollmentOid() {
		return enrollmentOid;
	}

	public void setEnrollmentOid(EnrollmentT enrollmentOid) {
		this.enrollmentOid = enrollmentOid;
	}

	public CodeValueT getAttenTypeOid() {
		return attenTypeOid;
	}

	public void setAttenTypeOid(CodeValueT attenTypeOid) {
		this.attenTypeOid = attenTypeOid;
	}

	public Date getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(Date attendamceDate) {
		this.attendanceDate = attendamceDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}

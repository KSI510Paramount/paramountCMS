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
@Table(name="ENROLLMENT_T",schema="CMSWEB")
@NamedQueries({
	@NamedQuery(name="EnrollmentT.findAll", query="Select c from EnrollmentT c"),
	@NamedQuery(name="EnrollmentT.findByCourseOfferOid", query="Select c from EnrollmentT c Where c.courseOfferOid.objectid = :courseOfferOid"),
	@NamedQuery(name="EnrollmentT.findByObjectIds", query="Select c from EnrollmentT c Where c.objectid IN (:objectids)"),
	@NamedQuery(name="EnrollmentT.findByCourseOfferOidExcludeStudentOids", query="Select c from EnrollmentT c Where c.courseOfferOid.objectid = :courseOfferOid AND c.studentOid.objectid NOT IN (:studentOids)")
})

public class EnrollmentT extends DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "OBJECTID", unique=true, nullable=false, updatable=false, precision=18)
	private Long objectid;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "COURSE_OFFER_OID")
	private CourseOfferT courseOfferOid;
	
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "STUDENT_OID")
	private StudentT studentOid;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ENROLLMENT_DATE")
	private Date enrollmentDate;

	public Long getObjectid() {
		return objectid;
	}
	
	public void setObjectid(Long objectid) {
		this.objectid = objectid;
	}

	public CourseOfferT getCourseOfferOid() {
		return courseOfferOid;
	}

	public void setCourseOfferOid(CourseOfferT courseOfferOid) {
		this.courseOfferOid = courseOfferOid;
	}

	public StudentT getStudentOid() {
		return studentOid;
	}

	public void setStudentOid(StudentT studentOid) {
		this.studentOid = studentOid;
	}

	public Date getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

}

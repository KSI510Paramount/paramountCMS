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
@Table(name="COURSE_OFFER_T",schema="CMSWEB")
@NamedQueries({
	@NamedQuery(name="CourseOfferT.findAll", query="Select c from CourseOfferT c"),
	@NamedQuery(name="CourseOfferT.findByFacultyOidAndSemesterOid", query="Select c from CourseOfferT c Where c.semesterOid.objectid = :semesterOid AND c.facultyOid.objectid = :facultyOid"),
	@NamedQuery(name="CourseOfferT.findByFacultyOid", query="Select c from CourseOfferT c Where c.facultyOid.objectid = :facultyOid")
})

public class CourseOfferT extends DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "OBJECTID", unique=true, nullable=false, updatable=false, precision=18)
	private Long objectid;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "COURSE_OID")
	private CourseT courseOid;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "SEMESTER_OID")
	private SemesterT semesterOid;
	
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "FACULTY_OID")
	private FacultyT facultyOid;
	
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "LOCATION_OID")
	private CodeValueT locationOid;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE")
	private Date endDate;
	   
	public Long getObjectid() {
		return objectid;
	}
	
	public void setObjectid(Long objectid) {
		this.objectid = objectid;
	}

	public CourseT getCourseOid() {
		return courseOid;
	}

	public void setCourseOid(CourseT courseOid) {
		this.courseOid = courseOid;
	}

	public SemesterT getSemesterOid() {
		return semesterOid;
	}

	public void setSemesterOid(SemesterT semesterOid) {
		this.semesterOid = semesterOid;
	}

	public FacultyT getFacultyOid() {
		return facultyOid;
	}

	public void setFacultyOid(FacultyT facultyOid) {
		this.facultyOid = facultyOid;
	}

	public CodeValueT getLocationOid() {
		return locationOid;
	}

	public void setLocationOid(CodeValueT locationOid) {
		this.locationOid = locationOid;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}

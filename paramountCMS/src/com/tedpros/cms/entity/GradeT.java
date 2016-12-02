package com.tedpros.cms.entity;

import java.math.BigDecimal;

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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="GRADE_T",schema="CMSWEB")
@NamedQueries({
	@NamedQuery(name="GradeT.findAll", query="Select g from GradeT g"),
	@NamedQuery(name="GradeT.findByAssingmentOid", query="Select g from GradeT g where g.assignmentOid.objectid = :assignmentOid"),
	@NamedQuery(name="GradeT.findAssignmentByCourseOfferOid", query="Select DISTINCT g.assignmentOid from GradeT g where g.enrollmentOid.courseOfferOid.objectid = :courseOfferOid"),
	@NamedQuery(name="GradeT.findByAssignmentOid", query="Select DISTINCT g from GradeT g where g.assignmentOid.objectid = :assignmentOid")
})

public class GradeT extends DomainEntity{

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
	@JoinColumn(name = "GRADE_TYPE_OID")
	private CodeValueT gradeTypeOid;
	
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "ASSIGNMENT_OID")
	private CodeValueT assignmentOid;
	
	@Column(name = "ACTUAL_POINT")
	private BigDecimal actualPoint;
	
	@Column(name = "LETTER_GRADE")
	private String letterGrade;
	
	@Column(name = "REMARKS")
	private String remarks;

	@Column(name = "EXTENDED_GRADE")
	private String extendedGrade = "N";
	
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

	public CodeValueT getGradeTypeOid() {
		return gradeTypeOid;
	}

	public void setGradeTypeOid(CodeValueT gradeTypeOid) {
		this.gradeTypeOid = gradeTypeOid;
	}

	public CodeValueT getAssignmentOid() {
		return assignmentOid;
	}

	public void setAssignmentOid(CodeValueT assignmentOid) {
		this.assignmentOid = assignmentOid;
	}

	public BigDecimal getActualPoint() {
		return actualPoint;
	}

	public void setActualPoint(BigDecimal actualPoint) {
		this.actualPoint = actualPoint;
	}

	public String getLetterGrade() {
		return letterGrade;
	}

	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getExtendedGrade() {
		return extendedGrade;
	}

	public void setExtendedGrade(String extendedGrade) {
		this.extendedGrade = extendedGrade;
	}
}

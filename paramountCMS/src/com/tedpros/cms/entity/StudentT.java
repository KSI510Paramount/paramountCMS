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

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.tedpros.cms.controller.StudentController;
import com.tedpros.cms.util.Utility;

@Entity
@Table(name="STUDENT_T",schema="CMSWEB")
@NamedQueries({
	@NamedQuery(name="StudentT.findAll", query="Select s from StudentT s"),
	@NamedQuery(name="StudentT.findAllWithExcludesStudents", query="Select s from StudentT s Where s.objectid NOT IN (:excludeStudents)")
})

public class StudentT extends DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "OBJECTID", unique=true, nullable=false, updatable=false, precision=18)
	private Long objectid;

	@Column(name = "STUDENT_ID", nullable=false, length=50, columnDefinition="varchar(50)")
	private String studentId;

   @Column(name = "FIRST_NAME", nullable=false, length=50, columnDefinition="varchar(50)")
   private String firstName;

   @Column(name = "LAST_NAME", nullable=false, length=50, columnDefinition="varchar(50)")
   private String lastName;
   
   @Column(name = "MIDDLE_NAME", nullable=true, length=50, columnDefinition="varchar(50)")
   private String middleName;

   @Column(name = "BIRTHDATE")
   private Date birthdate;

   @ManyToOne
   @NotFound(action=NotFoundAction.IGNORE)
   @JoinColumn(name = "GENDER_OID")
   private CodeValueT genderOid;
   
   @ManyToOne
   @NotFound(action=NotFoundAction.IGNORE)
   @JoinColumn(name = "STUDENT_TYPE_OID")
   private CodeValueT studentTypeOid;
   
   @ManyToOne
   @NotFound(action=NotFoundAction.IGNORE)
   @JoinColumn(name = "STUDENT_STATUS_OID")
   private CodeValueT studentStatusOid;
   
   @ManyToOne
   @NotFound(action=NotFoundAction.IGNORE)
   @JoinColumn(name = "CLASS_STATUS_OID")
   private CodeValueT classStatusOid;

	public Long getObjectid() {
		return objectid;
	}
	
	public void setObjectid(Long objectid) {
		this.objectid = objectid;
	}
	
	public String getStudentId() {
		return studentId;
	}
	
	public void setStudentId(String studentId) {
		if(StringUtils.isNotBlank(studentId)){
			this.studentId = studentId;			
		}else{
			this.studentId = generateStudentId();
		}
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public Date getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public CodeValueT getGenderOid() {
		return genderOid;
	}

	public void setGenderOid(CodeValueT genderOid) {
		this.genderOid = genderOid;
	}

	public CodeValueT getStudentTypeOid() {
		return studentTypeOid;
	}

	public void setStudentTypeOid(CodeValueT studentTypeOid) {
		this.studentTypeOid = studentTypeOid;
	}

	public CodeValueT getStudentStatusOid() {
		return studentStatusOid;
	}

	public void setStudentStatusOid(CodeValueT studentStatusOid) {
		this.studentStatusOid = studentStatusOid;
	}

	public CodeValueT getClassStatusOid() {
		return classStatusOid;
	}

	public void setClassStatusOid(CodeValueT classStatusOid) {
		this.classStatusOid = classStatusOid;
	}
	
	public String generateStudentId(){
		StringBuffer buffer = new StringBuffer();
		buffer.append(getFirstName().toUpperCase().charAt(0));
		buffer.append(getLastName().toUpperCase().charAt(0));
		//TODO: Next Id number in codevalue
		//TODO: Add Join Date
		buffer.append(Utility.getFourDigitNumber(String.valueOf(++StudentController.studentCount)));
		return buffer.toString();
	}
}

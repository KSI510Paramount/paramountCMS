package com.tedpros.cms.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="FACULTY_T",schema="CMSWEB")
@NamedQueries({
	@NamedQuery(name="FacultyT.findAll", query="Select f from FacultyT f")
})

public class FacultyT extends DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "OBJECTID", unique=true, nullable=false, updatable=false, precision=18)
	private Long objectid;

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
   @JoinColumn(name = "FACULTY_PREFIX")
   private CodeValueT facultyPrefixOid;
   
   
   @OneToOne(cascade = CascadeType.ALL)
   @NotFound(action=NotFoundAction.IGNORE)
   @JoinColumn(name = "USER_OID")
   private UserT userOid;

	public Long getObjectid() {
		return objectid;
	}
	
	public void setObjectid(Long objectid) {
		this.objectid = objectid;
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

	public CodeValueT getFacultyPrefixOid() {
		return facultyPrefixOid;
	}

	public void setFacultyPrefixOid(CodeValueT facultyPrefixOid) {
		this.facultyPrefixOid = facultyPrefixOid;
	}

	public UserT getUserOid() {
		return userOid;
	}

	public void setUserOid(UserT userOid) {
		this.userOid = userOid;
	}
}

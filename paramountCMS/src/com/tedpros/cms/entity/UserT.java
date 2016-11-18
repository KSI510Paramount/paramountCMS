package com.tedpros.cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.io.FilenameUtils;

@Entity
@Table(name="USER_T",schema="CMSWEB")
@NamedQueries({
	@NamedQuery(name="UserT.findAll", query="Select u from UserT u")
})

public class UserT extends DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "OBJECTID", unique=true, nullable=false, updatable=false, precision=18)
	private Long objectid;

	@Column(name = "USERNAME", nullable=false, length=50, columnDefinition="varchar(50)")
	private String username;

   @Column(name = "PASSWORD", nullable=false, length=100, columnDefinition="varchar(100)")
   private String title;

   @Column(name = "ACTIVE_FLAG", length=1, columnDefinition="nchar")
   private String activeFlag;
   
	public Long getObjectid() {
		return objectid;
	}
	
	public void setObjectid(Long objectid) {
		this.objectid = objectid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
}

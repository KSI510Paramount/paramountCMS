package com.tedpros.cms.entity;

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
   private String password;

   @Column(name = "ACTIVE_FLAG", length=1, columnDefinition="nchar")
   private String activeFlag;
   
   @ManyToOne
   @NotFound(action=NotFoundAction.IGNORE)
   @JoinColumn(name = "ROLE_OID")
   private CodeValueT roleOid;
   
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public CodeValueT getRoleOid() {
		return roleOid;
	}

	public void setRoleOid(CodeValueT roleOid) {
		this.roleOid = roleOid;
	}
}

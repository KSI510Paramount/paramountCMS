package com.tedpros.cms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="CODE_VALUE_T",schema="CMSWEB")
@NamedQueries({
	@NamedQuery(name="CodeValueT.findAll", query="Select c from CodeValueT c"),
	@NamedQuery(name="CodeValueT.findByCodeGroup", query="Select c from CodeValueT c WHERE c.codeGroup = :codeGroup")
})

public class CodeValueT extends DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "OBJECTID", unique=true, nullable=false, updatable=false, precision=18)
	private Long objectid;

	@Column(name = "CODE", nullable=false, length=50, columnDefinition="varchar(50)")
	private String code;

   @Column(name = "CODE_GROUP", nullable=false, length=50, columnDefinition="varchar(50)")
   private String codeGroup;

   @Column(name = "SHORT_DESCRIPTION", nullable=false, length=100, columnDefinition="varchar(100)")
   private String shortDescription;  
   
   @Column(name = "LONG_DESCRIPTION", length=500, columnDefinition="varchar(500)")
   private String longDescription;  
   
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "EFFECTIVE_DATETIME")
   private Date effectiveDateTime;  
   
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "EFFECTIVE_THRU_DATETIME")
   private Date effectiveThruDateTime;

   @Column(name = "SEQ")
   private Integer seq;
   
	@Override
	public Long getObjectid() {
		return objectid;
	}
	
	@Override
	public void setObjectid(Long objectid) {
		this.objectid = objectid;
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCodeGroup() {
		return codeGroup;
	}
	
	public void setCodeGroup(String codeGroup) {
		this.codeGroup = codeGroup;
	}
	
	public String getShortDescription() {
		return shortDescription;
	}
	
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public String getLongDescription() {
		return longDescription;
	}
	
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public Date getEffectiveDateTime() {
		return effectiveDateTime;
	}

	public void setEffectiveDateTime(Date effectiveDateTime) {
		this.effectiveDateTime = effectiveDateTime;
	}

	public Date getEffectiveThruDateTime() {
		return effectiveThruDateTime;
	}

	public void setEffectiveThruDateTime(Date effectiveThruDateTime) {
		this.effectiveThruDateTime = effectiveThruDateTime;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
}

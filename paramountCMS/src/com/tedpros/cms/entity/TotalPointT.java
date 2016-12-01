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
@Table(name="TOTAL_POINT_T",schema="CMSWEB")
@NamedQueries({
	@NamedQuery(name="TotalPointT.findAll", query="Select a from TotalPointT a")
})

public class TotalPointT extends DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "OBJECTID", unique=true, nullable=false, updatable=false, precision=18)
	private Long objectid;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "GRADE_OID")
	private EnrollmentT gradeOid;
	
	@Column(name = "TOTAL_POINT")
	private BigDecimal totalPoint;
	
	public Long getObjectid() {
		return objectid;
	}
	
	public void setObjectid(Long objectid) {
		this.objectid = objectid;
	}

	public EnrollmentT getGradeOid() {
		return gradeOid;
	}

	public void setGradeOid(EnrollmentT gradeOid) {
		this.gradeOid = gradeOid;
	}

	public BigDecimal getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(BigDecimal totalPoint) {
		this.totalPoint = totalPoint;
	}
}

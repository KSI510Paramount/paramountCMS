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
@Table(name="COURSE_T",schema="CMSWEB")
@NamedQueries({
	@NamedQuery(name="CourseT.findAll", query="Select c from CourseT c")
})

public class CourseT extends DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "OBJECTID", unique=true, nullable=false, updatable=false, precision=18)
	private Long objectid;

	@Column(name = "CODE", nullable=false, length=50, columnDefinition="varchar(50)")
	private String code;

   @Column(name = "TITLE", nullable=false, length=500, columnDefinition="varchar(500)")
   private String title;

   @Column(name = "DESCRIPTION", nullable=true, length=1000, columnDefinition="varchar(1000)")
   private String description;  

   @Lob
   @Column(name = "SYLLABUS")
   private byte[] syllabus;

   @Column(name = "FILE_NAME")
   private String fileName;
   
   @Transient
   private String fileExtension;
   
	public Long getObjectid() {
		return objectid;
	}
	
	public void setObjectid(Long objectid) {
		this.objectid = objectid;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public byte[] getSyllabus() {
		return syllabus;
	}
	
	public void setSyllabus(byte[] syllabus) {
		this.syllabus = syllabus;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return FilenameUtils.getExtension(getFileName());
	}
}

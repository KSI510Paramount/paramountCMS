package com.tedpros.cms.jsptag;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.tedpros.cms.entity.CodeValueT;
import com.tedpros.cms.service.CodeValueService;

public class CodeValueTag extends RequestContextAwareTag {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CodeValueService codeValueService;

	private String codegroup = StringUtils.EMPTY;
	
	private String requried = StringUtils.EMPTY;
	
	private String code = StringUtils.EMPTY;
	
	private String name = StringUtils.EMPTY;
	
	private String id = StringUtils.EMPTY;
	
	private String multiple = StringUtils.EMPTY;
	
	private String size = StringUtils.EMPTY;
	
	private String classvalue = StringUtils.EMPTY;
	
	private String value = StringUtils.EMPTY;
	
	private String style = StringUtils.EMPTY;
	
	private String defaultcode = StringUtils.EMPTY;
	
	private String longDescription = StringUtils.EMPTY;
	
	private String dropdownText = StringUtils.EMPTY;
	
	private String excludeCodes = StringUtils.EMPTY;
	

	public String getCodegroup() {
		return codegroup;
	}


	public void setCodegroup(String codegroup) {
		this.codegroup = codegroup;
	}


	public String getRequried() {
		return requried;
	}


	public void setRequried(String requried) {
		this.requried = requried;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getMultiple() {
		return multiple;
	}


	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public String getClassvalue() {
		return classvalue;
	}


	public void setClassvalue(String classvalue) {
		this.classvalue = classvalue;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getStyle() {
		return style;
	}


	public void setStyle(String style) {
		this.style = style;
	}


	public String getDefaultcode() {
		return defaultcode;
	}


	public void setDefaultcode(String defaultcode) {
		this.defaultcode = defaultcode;
	}


	public String getLongDescription() {
		return longDescription;
	}


	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}


	public String getDropdownText() {
		return dropdownText;
	}


	public void setDropdownText(String dropdownText) {
		this.dropdownText = dropdownText;
	}


	public String getExcludeCodes() {
		return excludeCodes;
	}


	public void setExcludeCodes(String excludeCodes) {
		this.excludeCodes = excludeCodes;
	}

	private List<CodeValueT> getCodesByCodeValue(){
		if(codeValueService == null){
			WebApplicationContext wac = getRequestContext().getWebApplicationContext();
			AutowireCapableBeanFactory autowireCapableBeanFactory = wac.getAutowireCapableBeanFactory();
			autowireCapableBeanFactory.autowireBean(this);
		}
		List<CodeValueT> codeValueList = null;
		try {
			if(StringUtils.isNotBlank(getCodegroup())){
				codeValueList = codeValueService.findByCodeGroup(getCodegroup());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codeValueList;
	}
	
	@Override
	protected int doStartTagInternal() throws Exception {
		try {
			write(getCodesByCodeValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Tag.SKIP_BODY;
	}

	private void write(List<CodeValueT> codeValueList) throws IOException{
		StringBuffer buffer = new StringBuffer();
		buffer.append(generateSelectStartTag());
		buffer.append("<option").append(StringUtils.SPACE).append("value=\"").append("\"").append(">").append("--Select--").append("</option>");
		List<String> excludeCodesList = null;
		if(StringUtils.isNotEmpty(this.getExcludeCodes())){
			excludeCodesList = Arrays.asList(StringUtils.split(this.getExcludeCodes(), ","));
		}
		for(CodeValueT codeValue : codeValueList){
			if(excludeCodesList != null && !excludeCodesList.isEmpty()){
				if(!excludeCodesList.contains(codeValue.getCode())){
					buffer.append(generateOption(codeValue));
				}
			}else{
				buffer.append(generateOption(codeValue));
			}
		}
		buffer.append("</select>");
		pageContext.getOut().print(buffer.toString());
	}
	
	private String generateSelectStartTag(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<select");
		if(StringUtils.isNotBlank(this.getName())){
			buffer.append(StringUtils.SPACE);
			buffer.append("name=\"" + this.getName() + "\"");
			
		}
		if(StringUtils.isNotBlank(this.getId())){
			buffer.append(StringUtils.SPACE);
			buffer.append("id=\"" + this.getId() + "\"");
			
		}
		if(StringUtils.isNotBlank(this.getSize())){
			buffer.append(StringUtils.SPACE);
			buffer.append("size=\"" + this.getSize() + "\"");
			
		}
		if(StringUtils.isNotBlank(this.getMultiple())){
			buffer.append(StringUtils.SPACE);
			buffer.append(this.getMultiple());
			
		}
		if(StringUtils.isNotBlank(this.getClassvalue())){
			buffer.append(StringUtils.SPACE);
			buffer.append("class=\"" + this.getClassvalue() + "\"");
			
		}
		if(StringUtils.isNotBlank(this.getRequried()) && getRequried().equalsIgnoreCase("TRUE")){
			buffer.append(StringUtils.SPACE);
			buffer.append("required");
			
		}
		if(StringUtils.isNotBlank(this.getStyle())){
			buffer.append(StringUtils.SPACE);
			buffer.append("style=\"" + this.getStyle() + "\"");
			
		}
		buffer.append(">");
		return buffer.toString();
	}
	
	private String generateOption(CodeValueT codeValue){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<option");
		buffer.append(StringUtils.SPACE);
		buffer.append("value=\"" + codeValue.getObjectid() + "\"" + StringUtils.SPACE);
		
		if(StringUtils.isNotBlank(this.getValue())
				&& String.valueOf(codeValue.getObjectid()).equalsIgnoreCase(this.getValue())){
			buffer.append("selected");
		}
		
		if(StringUtils.isEmpty(this.getValue()) && StringUtils.isNotEmpty(this.getDefaultcode())
				&& this.getDefaultcode().equalsIgnoreCase(codeValue.getCode())){
			buffer.append("selected");
		}
		
		buffer.append(" code=\"" + codeValue.getCode() + "\" ");
		buffer.append(">");
		if(StringUtils.isEmpty(this.getDropdownText()) 
				&& this.getLongDescription().equalsIgnoreCase("longDescription")){
			buffer.append(codeValue.getLongDescription());
		}else{
			buffer.append(codeValue.getShortDescription());
		}
		buffer.append("</option>");
		return buffer.toString();
	}
}

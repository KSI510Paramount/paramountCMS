package com.tedpros.cms.util;

import javax.servlet.http.HttpServletResponse;

public final class Utility {

	public static void generateContentType(HttpServletResponse response, String documentType){
		StringBuffer contentType = new StringBuffer();
		if(documentType.equalsIgnoreCase("MSWORD") || documentType.equalsIgnoreCase("doc")
				|| documentType.equalsIgnoreCase("docx") || documentType.toLowerCase().contains("odt")){
			contentType.append("application/msword");
		}else if(documentType.equalsIgnoreCase("pdf")){
			contentType.append("application/pdf");
		}else{
			contentType.append("application/octet-stream");
		}
		response.setContentType(contentType.toString());
	}
	
	public static void generateContentDisposition(HttpServletResponse response, String documentType, String documentName){
		StringBuffer contentDisposition = new StringBuffer();
		contentDisposition.append("attachment; filename=");
		contentDisposition.append("\""+documentName);
		if(documentType.equalsIgnoreCase("MSWORD")){
			contentDisposition.append(".docx");
		}else if(documentType.equalsIgnoreCase("PDF")){
			contentDisposition.append(".pdf");
		}else{
			contentDisposition.append("."+documentType.toLowerCase());			
		}
		contentDisposition.append("\"");
		response.setHeader("Content-Disposition", contentDisposition.toString());
	}
	
	public static String getFourDigitNumber(String number){
		return String.format("%04d", Integer.valueOf(number));
	}
}

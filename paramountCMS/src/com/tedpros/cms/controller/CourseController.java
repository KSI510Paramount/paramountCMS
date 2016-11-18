package com.tedpros.cms.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.tedpros.cms.entity.CodeValueT;
import com.tedpros.cms.entity.CourseT;
import com.tedpros.cms.service.CourseService;
import com.tedpros.cms.util.Utility;

@Controller
@RequestMapping("/course/*")
public class CourseController extends TopController{
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value = "/getList.do", method=RequestMethod.GET)
	public String getList(WebRequest request){
		List<CourseT> courseList = courseService.findAll();
		request.setAttribute("courseList", courseList, WebRequest.SCOPE_REQUEST);
		return "course.list";
	}
	
	@RequestMapping(value = "/getAdd.do", method=RequestMethod.GET)
	public String getAdd(WebRequest request){
		return "course.add";
	}
	
	@RequestMapping(value = "/postAdd.do", method=RequestMethod.POST)
	public String postAdd(WebRequest request,HttpServletRequest httpRequest){
	    try {
	    	CourseT course = new CourseT();
		    List<FileItem> files = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(httpRequest);
		    for (FileItem fileItem : files) {
				if(!fileItem.isFormField()){
					String filename = FilenameUtils.getName(fileItem.getName());
					byte[] file = fileItem.get();
					course.setFileName(filename);
					course.setSyllabus(file);
				}else{
					if(fileItem.getFieldName().equalsIgnoreCase("code")){
						course.setCode(fileItem.getString());
					}else if(fileItem.getFieldName().equalsIgnoreCase("title")){
						course.setTitle(fileItem.getString());
					}else if(fileItem.getFieldName().equalsIgnoreCase("description")){
						course.setDescription(fileItem.getString());
					}
				}
			}
		    courseService.persist(course);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/course/getList.do";
	}
	
	@RequestMapping(value = "/getEdit.do", method=RequestMethod.GET)
	public String getEdit(WebRequest request){
		String courseOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(courseOid)){
			CourseT course = courseService.findById(CourseT.class, Long.valueOf(courseOid));
			if(course != null){
				request.setAttribute("course", course, WebRequest.SCOPE_REQUEST);
			}
		}
		return "course.edit";
	}
	
	@RequestMapping(value = "/postEdit.do", method=RequestMethod.POST)
	public String postEdit(WebRequest request, HttpServletRequest httpRequest){
		try {
			String courseOid= request.getParameter("objectid");
			if(StringUtils.isNotBlank(courseOid)){
				CourseT course = courseService.findById(CourseT.class, Long.valueOf(courseOid));
				if(course != null){
				    List<FileItem> files = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(httpRequest);
				    for (FileItem fileItem : files) {
						if(!fileItem.isFormField()){
							String filename = FilenameUtils.getName(fileItem.getName());
							if(StringUtils.isNotBlank(filename)){
								byte[] file = fileItem.get();
								course.setFileName(filename);
								course.setSyllabus(file);								
							}
						}else{
							if(fileItem.getFieldName().equalsIgnoreCase("title")){
								course.setTitle(fileItem.getString());
							}else if(fileItem.getFieldName().equalsIgnoreCase("description")){
								course.setDescription(fileItem.getString());
							}
						}
					}
				    courseService.update(course);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/course/getList.do";
	}
	
	@RequestMapping(value = "/getDocView.do", method=RequestMethod.GET)
	public void getDocView(WebRequest request, HttpServletResponse response){
		String courseOid= request.getParameter("objectid");
		try {
			if(StringUtils.isNotBlank(courseOid)){
				CourseT course = courseService.findById(CourseT.class, Long.valueOf(courseOid));
				if(course != null){
					response.addHeader("Cache-Control", "No Cache");
					response.setHeader("Expires", "0");
					Utility.generateContentDisposition(response, course.getFileExtension(), course.getFileName());
					Utility.generateContentType(response, course.getFileExtension());
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					baos.write(course.getSyllabus());
					if(baos != null && baos.size() > 0){
						response.setContentLength(baos.size());
						baos.writeTo(response.getOutputStream());
						baos.close();
					}
					response.getOutputStream().flush();
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getDelete.do", method=RequestMethod.GET)
	public String getDelete(WebRequest request){
		String courseOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(courseOid)){
			CourseT course = courseService.findById(CourseT.class, Long.valueOf(courseOid));
			if(course != null){
				//courseService.delete(course);
			}
		}
		return "redirect:/course/getList.do";
	}
}

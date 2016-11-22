package com.tedpros.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.tedpros.cms.entity.CodeValueT;
import com.tedpros.cms.service.CodeValueService;


@RestController
@RequestMapping("/rest/*")
public class ListDataRestController extends TopController{
	
	@Inject
	private CodeValueService codeValueService;
	
	@RequestMapping(value = "/codeValue/getResultList.do",method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public String getResultList(WebRequest request){
		List<CodeValueT> codeValueList = codeValueService.findAll();
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("recordsTotal", codeValueList.size());
		jsonMap.put("recordsFiltered", codeValueList.size());
		jsonMap.put("data", getCodeValueList(codeValueList));
		String jsonString = new JSONObject(jsonMap).toJSONString();
		System.out.println(jsonString);
		return jsonString;
	}
	
	private List<Object> getCodeValueList(List<CodeValueT> codeValues){
		List<Object> cv = new ArrayList<>();
		for (CodeValueT codeValue : codeValues) {
			Map<String, Object> valueMap = new HashMap<>();
			valueMap.put("codeGroup", codeValue.getCodeGroup());
			valueMap.put("code", codeValue.getCode());
			valueMap.put("shortDescription", codeValue.getShortDescription());
			valueMap.put("longDescription", codeValue.getLongDescription());
			valueMap.put("objectid", codeValue.getObjectid());
			cv.add(valueMap);
		}
		return cv;
	}
	

	/*@RequestMapping(value = "/cms/data/faculty/{facultyId}",method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Faculty getFacultyInfo(@PathVariable String facultyId){
		return userDataService.retrieveFacultyInfo(facultyId);
	}
	
	@RequestMapping(value = "/cms/data/offer/{offerId}",method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public CourseSemesterOffer getCourseSemesterOfferInfo(@PathVariable String offerId){
		return userDataService.retrieveCourseSemesterOfferInfo(offerId);
	}
	
	@RequestMapping(value = "/cms/data/course/{courseId}",method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Course getCourseInfo(@PathVariable String courseId){
		return userDataService.retrieveCourseInfo(courseId);
	}
	
	@RequestMapping(value = "/cms/data/offer/{offerId}/student/{studentId}/score",method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public StudentCourseScore getStudentCourseScoreInfo(@PathVariable String offerId,@PathVariable String studentId){
		return userDataService.retrieveStudentCourseScoreInfo(offerId, studentId);
	}
	
	@RequestMapping(value = "/cms/data/student",method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public InsertResponse createStudent(@RequestBody Student student){
		return userDataService.createStudent(student);
	}

	@RequestMapping(value = "/cms/data/faculty",method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public InsertResponse createFaculty(@RequestBody Faculty faculty){
		return userDataService.createFaculty(faculty);
	}
	
	@RequestMapping(value = "/cms/data/offer",method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public InsertResponse createCourseSemesterOffer(@RequestBody CourseSemesterOffer offer){
		return userDataService.createCourseSemesterOffer(offer);
	}
	
	@RequestMapping(value = "/cms/data/course",method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public InsertResponse getCourseInfo(@RequestBody Course course){
		return userDataService.createCourse(course);
	}
	
	@RequestMapping(value = "/cms/data/offer/score",method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public InsertResponse getStudentCourseScoreInfo(@RequestBody StudentCourseScore studentCourseScore){
		return userDataService.enterStudentCourseScore(studentCourseScore);
	}*/
}


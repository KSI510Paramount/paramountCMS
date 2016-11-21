package com.tedpros.cms.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tedpros.cms.model.Course;
import com.tedpros.cms.model.CourseSemesterOffer;
import com.tedpros.cms.model.Faculty;
import com.tedpros.cms.model.InsertResponse;
import com.tedpros.cms.model.Student;
import com.tedpros.cms.model.StudentCourseScore;
import com.tedpros.cms.service.UserDataService;

@RestController
public class UserDataController extends TopController{
	
	@Inject
	private UserDataService userDataService;
	
	@RequestMapping(value = "/cms/data/student/{studentId}",method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Student getStudentInfo(@PathVariable String studentId){
		return userDataService.retrieveStudentInfo(studentId);
	}

	@RequestMapping(value = "/cms/data/faculty/{facultyId}",method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
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
	}
}

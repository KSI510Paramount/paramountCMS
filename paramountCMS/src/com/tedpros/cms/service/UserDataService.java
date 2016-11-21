package com.tedpros.cms.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tedpros.cms.model.InsertResponse;
import com.tedpros.cms.model.Course;
import com.tedpros.cms.model.CourseSemesterOffer;
import com.tedpros.cms.model.Faculty;
import com.tedpros.cms.model.Student;
import com.tedpros.cms.model.StudentCourseScore;

@Service
public class UserDataService {
	
	private static final String SCORE_ID = "score_id";
	private static final String SCORE_TYPE_ID = "score_type_id";
	private static final String SCORE_VALUE = "score_value";
	private static final String COURSE_CODE = "code";
	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private static final String CREDIT_HOUR = "credit_hour";
	private static final String COURSE_ID = "course_id";
	private static final String SEMESTER_ID = "semester_id";
	private static final String FACULTY_ID = "faculty_id";
	private static final String COURSE_TITLE = "course_title";
	private static final String COURSE_DESCRIPTION = "course_description";
	private static final String COURSE_CREDT_HOUR = "course_credt_hour";
	private static final String COURSE_TIME = "course_time";
	private static final String COURSE_LOCATION = "course_location";
	private static final String FACULTY_LOCATION = "faculty_location";
	private static final String FACULTY_NAME = "faculty_name";
	private static final String FACULTY_PREFIX = "faculty_prefix";
	private static final String ENTRY_DATE = "entry_date";
	private static final String DATE_FORMAT = "MM/dd/yyyy";
	private static final String UPDATE_DATE = "update_date";
	private static final String ENROLLMENT_DATE = "enrollment_date";
	private static final String REGISTER_DATE = "register_date";
	private static final String SSN = "ssn";
	private static final String DATE_OF_BIRTH = "date_of_birth";
	private static final String GENDER = "gender";
	private static final String LAST_NAME = "last_name";
	private static final String MIDDLE_NAME = "middle_name";
	private static final String FIRST_NAME = "first_name";
	private static final String PROGRAM_ID = "program_id";

	public Student retrieveStudentInfo(String studentId){
		Student student = new Student();
		Map<String, String> studentData = getStudentDataFromDB(studentId);
		student.setStudentId(studentId);
		student.setProgramId(studentData.get(PROGRAM_ID));
		student.setFirstName(studentData.get(FIRST_NAME));
		student.setMiddleName(studentData.get(MIDDLE_NAME));
		student.setLastName(studentData.get(LAST_NAME));
		student.setGender(studentData.get(GENDER));
		student.setDateOfBirth(convertStringToDate(studentData.get(DATE_OF_BIRTH),DATE_FORMAT));
		student.setSSN(studentData.get(SSN));
		student.setRegisterDate(convertStringToDate(studentData.get(REGISTER_DATE),DATE_FORMAT));
		student.setEnrollmentDate(convertStringToDate(studentData.get(ENROLLMENT_DATE),DATE_FORMAT));
		student.setUpdateDate(convertStringToDate(studentData.get(UPDATE_DATE),DATE_FORMAT));
		return student;
	}
	
	public Faculty retrieveFacultyInfo(String facultyId){
		Faculty faculty = new Faculty();
		Map<String, String> facultyData = getFacultyDataFromDB(facultyId);
		faculty.setFacultyId(facultyId);
		faculty.setFacultyPrefix(facultyData.get(FACULTY_PREFIX));
		faculty.setFacultyName(facultyData.get(FACULTY_NAME));
		faculty.setFacultyLocation(facultyData.get(FACULTY_LOCATION));
		faculty.setEntryDate(convertStringToDate(facultyData.get(ENTRY_DATE),DATE_FORMAT));
		faculty.setUpdateDate(convertStringToDate(facultyData.get(UPDATE_DATE),DATE_FORMAT));
		return faculty;
	}
	
	public CourseSemesterOffer retrieveCourseSemesterOfferInfo(String offerId){
		CourseSemesterOffer courseSemesterOffer = new CourseSemesterOffer();
		Map<String, String> courseSemesterOfferData = getCourseSemesterOfferDataFromDB(offerId);
		courseSemesterOffer.setOfferId(offerId);
		courseSemesterOffer.setCourseId(courseSemesterOfferData.get(COURSE_ID));
		courseSemesterOffer.setSemesterId(courseSemesterOfferData.get(SEMESTER_ID));
		courseSemesterOffer.setFacultyId(courseSemesterOfferData.get(FACULTY_ID));
		courseSemesterOffer.setCourseTitle(courseSemesterOfferData.get(COURSE_TITLE));
		courseSemesterOffer.setCourseDescription(courseSemesterOfferData.get(COURSE_DESCRIPTION));
		courseSemesterOffer.setCourseCreditHour(courseSemesterOfferData.get("course_credit_hour"));
		courseSemesterOffer.setCourseTime(courseSemesterOfferData.get(COURSE_TIME));
		courseSemesterOffer.setCourseLocation(courseSemesterOfferData.get(COURSE_LOCATION));
		courseSemesterOffer.setEntryDate(convertStringToDate(courseSemesterOfferData.get(ENTRY_DATE),DATE_FORMAT));
		courseSemesterOffer.setUpdateDate(convertStringToDate(courseSemesterOfferData.get(UPDATE_DATE),DATE_FORMAT));
		return courseSemesterOffer;
	}
	
	public Course retrieveCourseInfo(String courseId){
		Course course = new Course();
		Map<String, String> courseData = getCourseDataFromDB(courseId);
		course.setCourseId(courseId);
		course.setCode(courseData.get(COURSE_CODE));
		course.setTitle(courseData.get(TITLE));
		course.setDescription(courseData.get(DESCRIPTION));
		course.setCreditHours(courseData.get(CREDIT_HOUR));
		course.setEntryDate(convertStringToDate(courseData.get(ENTRY_DATE),DATE_FORMAT));
		course.setUpdateDate(convertStringToDate(courseData.get(UPDATE_DATE),DATE_FORMAT));
		return course;
	}
	

	public StudentCourseScore retrieveStudentCourseScoreInfo(String offerId, String studentId){
		StudentCourseScore studentCourseScore = new StudentCourseScore();
		Map<String, String> studentCourseScoreData = getStudentCourseScoreDataFromDB(offerId, studentId);
		studentCourseScore.setScoreId(studentCourseScoreData.get(SCORE_ID));
		studentCourseScore.setStudentId(studentId);
		studentCourseScore.setOfferId(offerId);
		studentCourseScore.setScoreTypeId(studentCourseScoreData.get(SCORE_TYPE_ID));
		studentCourseScore.setScoreValue(studentCourseScoreData.get(SCORE_VALUE));
		studentCourseScore.setEntryDate(convertStringToDate(studentCourseScoreData.get(ENTRY_DATE),DATE_FORMAT));
		studentCourseScore.setUpdateDate(convertStringToDate(studentCourseScoreData.get(UPDATE_DATE),DATE_FORMAT));
		return studentCourseScore;
	}

	private Map<String, String> getStudentCourseScoreDataFromDB(String offerId, String studentId) {
		// TODO use to retrieve student score data from db, need DAO method
		
		//Mock purpose
		Map<String, String> testMap = new HashMap<>();
		testMap.put(SCORE_ID, "1");
		testMap.put(SCORE_TYPE_ID, "1");
		testMap.put(SCORE_VALUE, "90");
		testMap.put(ENTRY_DATE, "01/01/2016");
		testMap.put(UPDATE_DATE, "02/02/2016");
		
		return testMap;
	}

	private Map<String, String> getCourseDataFromDB(String courseId) {
		// TODO use to retrieve course data from db, need DAO method
		//Mock purpose
				Map<String, String> testMap = new HashMap<>();
				testMap.put(COURSE_CODE, "CIS510");
				testMap.put(TITLE, "software management");
				testMap.put(DESCRIPTION, "n/a");
				testMap.put(CREDIT_HOUR, "3");
				testMap.put(ENTRY_DATE, "01/01/2016");
				testMap.put(UPDATE_DATE, "02/02/2016");
				
				return testMap;
	}
	
	private Map<String, String> getCourseSemesterOfferDataFromDB(String offerId) {
		// TODO use to retrieve course offer data from db, need DAO method
		//Mock purpose
		Map<String, String> testMap = new HashMap<>();
		testMap.put(COURSE_ID, "1");
		testMap.put(SEMESTER_ID, "1");
		testMap.put(FACULTY_ID, "1");
		testMap.put(COURSE_TITLE, "CIS510");
		testMap.put(COURSE_DESCRIPTION, "n/a");
		testMap.put(COURSE_CREDT_HOUR, "3");
		testMap.put(COURSE_TIME, "fall");
		testMap.put(COURSE_LOCATION, "room 2");
		testMap.put(ENTRY_DATE, "01/01/2016");
		testMap.put(UPDATE_DATE, "02/02/2016");
		
		return testMap;
	}

	private Map<String, String> getFacultyDataFromDB(String facultyId) {
		// TODO use to retrieve faculty data from db, need DAO method
		//Mock purpose
				Map<String, String> testMap = new HashMap<>();
				testMap.put(FACULTY_PREFIX, "Dr");
				testMap.put(FACULTY_NAME, "bob");
				testMap.put(FACULTY_LOCATION, "chicago");
				testMap.put(ENTRY_DATE, "01/01/2016");
				testMap.put(UPDATE_DATE, "02/02/2016");
				
				return testMap;
	}

	private Map<String, String> getStudentDataFromDB(String studentId) {
		// TODO use to retrieve student data from db, need DAO method
		//Mock purpose
		Map<String, String> testMap = new HashMap<>();
		testMap.put(PROGRAM_ID, "1");
		testMap.put(FIRST_NAME, "roger");
		testMap.put(LAST_NAME, "thomas");
		testMap.put(GENDER, "male");
		testMap.put(DATE_OF_BIRTH, "12/12/1982");
		testMap.put(SSN, "999999999");
		testMap.put(REGISTER_DATE, "12/12/2011");
		testMap.put(ENTRY_DATE, "01/01/2016");
		testMap.put(UPDATE_DATE, "02/02/2016");
		
		return testMap;
	}

	private Date convertStringToDate(String dateStr, String dateFormat) {
		Date convertedDate = null;
		try {
			DateFormat formatter = new SimpleDateFormat(dateFormat); 
			convertedDate = (Date)formatter.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convertedDate;
	}

	public InsertResponse createStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	public InsertResponse createFaculty(Faculty faculty) {
		// TODO Auto-generated method stub
		return null;
	}

	public InsertResponse createCourseSemesterOffer(CourseSemesterOffer offer) {
		// TODO Auto-generated method stub
		return null;
	}

	public InsertResponse enterStudentCourseScore(StudentCourseScore studentCourseScore) {
		// TODO Auto-generated method stub
		return null;
	}

	public InsertResponse createCourse(Course course) {
		// TODO Auto-generated method stub
		return null;
	}
}

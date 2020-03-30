package com.revature.coursems.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.coursems.domain.Category;
import com.revature.coursems.domain.Course;
import com.revature.coursems.domain.CourseSubscribedVideo;
import com.revature.coursems.domain.Doc;
import com.revature.coursems.domain.Level;
import com.revature.coursems.domain.Video;
import com.revature.coursems.domain.VideoCopy;
import com.revature.coursems.domain.updateDTO;
import com.revature.coursems.service.CourseService;

import exception.BusinessServiceException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/courses")

public class CourseController {
	@Autowired
	private CourseService courseService;

	//@ExceptionHandler(BusinessServiceException.class)
	@PostMapping()
	public ResponseEntity<?> saveCourse(@RequestBody @Valid Course course) throws BusinessServiceException {

		System.out.print(course.getName());
		courseService.saveCourse(course);
		return new ResponseEntity<>("", HttpStatus.OK);

	}

	//@ExceptionHandler(BusinessServiceException.class)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> view() throws BusinessServiceException {
		List<Course> courseObj = courseService.findAllCourses();
		return new ResponseEntity<>(courseObj, HttpStatus.OK);
	}

	//@ExceptionHandler(BusinessServiceException.class)
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) throws BusinessServiceException {
		String deletionStatus = courseService.deleteById(id);
		return new ResponseEntity<>(deletionStatus, HttpStatus.OK);
	}

	//@ExceptionHandler(BusinessServiceException.class)
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody updateDTO updateDTOObj) throws BusinessServiceException {
		// System.out.println(course.getId());
		courseService.update(updateDTOObj);
		return new ResponseEntity<>("updation successful", HttpStatus.OK);
	}
	
	@GetMapping("/viewCourseById/{id}")
	public ResponseEntity<?> getCourseById(@PathVariable int id ){
		Course course=courseService.getCourseById(id);
		return new ResponseEntity<>(course, HttpStatus.OK);
		
	}
	@GetMapping("/viewLevels")
	public ResponseEntity<?> viewLevels(){
		List<Level> listOfLevels=courseService.viewLevels();
		return new ResponseEntity<>(listOfLevels, HttpStatus.OK);
	}
	
	@GetMapping("/viewCategories")
	public ResponseEntity<?> viewCategories(){
		List<Category> listOfCategories=courseService.viewCategories();
		return new ResponseEntity<>(listOfCategories, HttpStatus.OK);
	}
	
	@GetMapping("/viewLevelById/{id}")
	public ResponseEntity<?> viewLevelById(@PathVariable int id){
		Level level=courseService.viewLevelById(id);
		return new ResponseEntity<>(level, HttpStatus.OK);
	}
	
	@GetMapping("/viewCategoryById/{id}")
	public ResponseEntity<?> viewCategoryById(@PathVariable int id){
		Category category=courseService.viewCategoryById(id);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	@GetMapping("/switchStatus/{id}")
	public ResponseEntity<?> switchStatus(@PathVariable int id)  {
		
		courseService.switchStatus(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	@GetMapping("/viewDocByCourseId/{id}")
	public  ResponseEntity<?> viewDocByCourseId(@PathVariable int id){
		List<Doc> listOfDocs=courseService.viewDocByCourseId(id);
		return new ResponseEntity<>(listOfDocs, HttpStatus.OK);
	}
	@GetMapping("/viewVideoByCourseId/{id}")
	public  ResponseEntity<?> viewVideoByCourseId(@PathVariable int id){
		List<CourseSubscribedVideo> listOfCourseSubscribedVideos=courseService.viewVideoByCourseId(id);
		return new ResponseEntity<>(listOfCourseSubscribedVideos, HttpStatus.OK);
	}
	@GetMapping("/login/{userId}/{password}")
	public  ResponseEntity<?> login(@PathVariable String userId,@PathVariable String password){
		String loginStatus=courseService.login(userId,password);
		return new ResponseEntity<>(loginStatus, HttpStatus.OK);
	}

	@DeleteMapping("deleteCourseVideoMappingById/{id}")
	public ResponseEntity<?> deleteCourseVideoMappingById(@PathVariable int id) throws BusinessServiceException {
		String deletionStatus = courseService.deleteCourseVideoMappingById(id);
		return new ResponseEntity<>(deletionStatus, HttpStatus.OK);
	}
	

}

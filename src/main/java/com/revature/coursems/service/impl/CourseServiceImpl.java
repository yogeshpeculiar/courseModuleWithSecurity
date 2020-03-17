package com.revature.coursems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.coursems.domain.Category;
import com.revature.coursems.domain.Course;
import com.revature.coursems.domain.Level;
import com.revature.coursems.repository.CourseRepository;
import com.revature.coursems.service.CourseService;

import exception.BusinessServiceException;
import exception.DatabaseServiceException;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public void saveCourse(Course course) throws BusinessServiceException {
		try {
			courseRepository.saveCourse(course);
		} catch (DatabaseServiceException e) {
			throw new BusinessServiceException(e.getMessage());
		}

	}

	@Override
	public List<Course> findAllCourses() throws BusinessServiceException {
		try {
			return courseRepository.findAllCourses();
		} catch (DatabaseServiceException e) {
			throw new BusinessServiceException("BusinessServiceException -- findAllCourse");
		}

	}

	@Override
	public String deleteById(int id) throws BusinessServiceException {
		try {
			return courseRepository.deleteById(id);
		} catch (DatabaseServiceException e) {
			throw new BusinessServiceException("BusinessServiceException -- deleteByID");
		}

	}

	@Override
	public void update(Course course) throws BusinessServiceException {
		try {
			courseRepository.update(course);
		} catch (DatabaseServiceException e) {
			throw new BusinessServiceException("BusinessServiceException -- update");
		}

	}

	@Override
	public Course getCourseById(int id) {
		return courseRepository.findCourseById(id);
		 
	}

	@Override
	public List<Level> viewLevels() {
		
		return courseRepository.viewLevels();
	}

	@Override
	public List<Category> viewCategories() {
		
		return courseRepository.viewCategories();
	}

	@Override
	public void switchStatus(int id) {
		courseRepository.switchStatus(id);
		
	}

	@Override
	public Level viewLevelById(int id) {
		
		return courseRepository.viewLevelById(id);
	}
	@Override
	public Category viewCategoryById(int id) {
		
		return courseRepository.viewCategoryById(id);
	}
}

package com.revature.coursems.repository;

import java.util.List;

import com.revature.coursems.domain.Category;
import com.revature.coursems.domain.Course;
import com.revature.coursems.domain.Level;

import exception.DatabaseServiceException;

public interface CourseRepository {
    public void saveCourse(Course course) throws DatabaseServiceException;

	public List<Course> findAllCourses() throws DatabaseServiceException;
	public String deleteById(int id) throws DatabaseServiceException;
	public void update(Course course) throws DatabaseServiceException;
	public Course findCourseById(int id);
	public List<Level> viewLevels();
	public List<Category> viewCategories();
	public void switchStatus(int id);
	public Level viewLevelById(int id);
	public Category viewCategoryById(int id);
	
}

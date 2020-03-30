package com.revature.coursems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.coursems.domain.Category;
import com.revature.coursems.domain.Course;
import com.revature.coursems.domain.CourseSubscribedVideo;
import com.revature.coursems.domain.Level;
import com.revature.coursems.domain.Login;
import com.revature.coursems.domain.Video;
import com.revature.coursems.domain.updateDTO;
import com.revature.coursems.repository.CourseRepository;
import com.revature.coursems.service.CourseService;
import com.revature.coursems.domain.Doc;

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
	public void update(updateDTO updateDTOObj) throws BusinessServiceException {
		try {
			Course course = new Course();
			course.setId(updateDTOObj.getId());
			course.setName(updateDTOObj.getName());
			System.out.println("----level id==>" + updateDTOObj.getLevelId());

			Level level = courseRepository.viewLevelById(updateDTOObj.getLevelId());
			System.out.println("-----level obj==>" + level);
			course.setLevelObj(level);
			Category category = courseRepository.viewCategoryById(updateDTOObj.getCategoryId());
			course.setCategoryObj(category);
			course.setDocObj(updateDTOObj.getDocObj());
			course.setCourseSubscribedVideo(updateDTOObj.getCourseSubscribedVideo());
			course.setTag(updateDTOObj.getTag());
			course.setSlug(updateDTOObj.getSlug());
			course.setIsActive(updateDTOObj.getIsActive());
			course.setIsLevelOverride(updateDTOObj.getIsLevelOverride());
			course.setIsPreSignUp(updateDTOObj.getIsPreSignUp());
			course.setIsSlugLogin(updateDTOObj.getIsSlugLogin());
			course.setIsDashboard(updateDTOObj.getIsDashboard());
			course.setDescription(updateDTOObj.getDescription());
			course.setMetaKey(updateDTOObj.getMetaKey());
			course.setMetaDesc(updateDTOObj.getMetaDesc());
			course.setCourse_icon(updateDTOObj.getCourse_icon());
			course.setCreatedBy(updateDTOObj.getCreatedBy());
			course.setModifiedBy(updateDTOObj.getModifiedBy());
			course.setCreatedOn(updateDTOObj.getCreatedOn());
			course.setModifiedOn(updateDTOObj.getModifiedOn());
			course.setVersion(updateDTOObj.getVersion());
			course.setCompletionActivityPoints(updateDTOObj.getCompletionActivityPoints());
			course.setEnrollmentActivityPoints(updateDTOObj.getEnrollmentActivityPoints());
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

	@Override
	public List<Doc> viewDocByCourseId(int id) {

		return courseRepository.viewDocByCourseId(id);
	}

	@Override
	public String login(String userId, String password) {
		Login login = new Login();
		login.setUserId(userId);
		login.setPassword(password);
		return courseRepository.login(login);
	}

	@Override
	public List<CourseSubscribedVideo> viewVideoByCourseId(int id) {
		
		return courseRepository.viewVideoByCourseId(id);
	}

	@Override
	public String deleteCourseVideoMappingById(int id) {
		return courseRepository.deleteCourseVideoMappingById(id);
	}
}

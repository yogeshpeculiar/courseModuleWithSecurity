package com.revature.coursems.repository.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.coursems.domain.Category;
import com.revature.coursems.domain.Course;
import com.revature.coursems.repository.CourseRepository;
import com.revature.coursems.domain.Level;

import exception.DatabaseServiceException;

@Repository
public class CourseRepositoryImpl implements CourseRepository {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveCourse(Course course) throws DatabaseServiceException {
		Session session = this.sessionFactory.getCurrentSession();
		// Here begin transaction
		session.beginTransaction();
		try {

			// Here put course details into doc course
			if (course.getDocObj() != null)
				course.getDocObj().forEach(docObj -> docObj.setCourse(course));
			// session.save(course);
			// here course details are inserted into the courseSubscribedVido
			if (course.getCourseSubscribedVideo() != null)
			course.getCourseSubscribedVideo().forEach(courseSubscribedVideoObj -> courseSubscribedVideoObj.setCourse(course));
			session.save(course);
			// Here changes get permanent
			session.getTransaction().commit();

		}

		catch (HibernateException e) {
			throw new DatabaseServiceException("Database service exception -- exception in saveCourse");
		} finally {
			session.close();
		}

	}

	@Override
	public List<Course> findAllCourses() throws DatabaseServiceException {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			// return session.createQuery("SELECT cou FROM Course cou JOIN FETCH
			// cou.categoryObj cat JOIN FETCH cou.levelObj lvl JOIN FETCH cou.docObj doc
			// JOIN FETCH cou.courseSubscribedVideo csv",Course.class).getResultList();
			List<Course> courses = session.createQuery(
					"SELECT cou FROM Course cou JOIN FETCH cou.categoryObj cat JOIN FETCH cou.levelObj lvl",
					Course.class).getResultList();
			// List<Course> courses =session.createQuery("select cou from Course
			// cou",Course.class).getResultList();
			System.err.println("courses=>" + courses);
			return courses;
			// return session.createQuery("From Course",Course.class).getResultList();
		} catch (HibernateException e) {
			throw new DatabaseServiceException("Database service exception -- exeption in findAllCOurses");
		}

	}

	@Override
	public String deleteById(int id) throws DatabaseServiceException {

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Course obj = session.get(Course.class, id);
			if (obj != null) {
				session.delete(obj);
				System.out.println("before delete" + obj);
				obj = session.get(Course.class, id);
				System.out.println("after delete" + obj);
				session.getTransaction().commit();
				session.close();
				return "deletion successful";
			} else
				return "deletion failed as the requested object doesnt exists";
		} catch (HibernateException e) {
			throw new DatabaseServiceException("Database service exception -- exeption in deleteByID");
		} finally {
			session.close();
		}
	}

	@Override
	public Course findCourseById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Course> courses = session.createQuery(
					"SELECT cou FROM Course cou JOIN FETCH cou.categoryObj cat JOIN FETCH cou.levelObj lvl where cou.id="+id,
					Course.class).getResultList();
					
		return courses.get(0);
	}

	@Override
	public void update(Course course) throws DatabaseServiceException {

		Session session = this.sessionFactory.getCurrentSession();
		try {
			course.getDocObj().forEach(docObj -> docObj.setCourse(course));
			if(course.getCourseSubscribedVideo()!=null)
			course.getCourseSubscribedVideo().forEach(courseSubscribedVideoObj -> courseSubscribedVideoObj.setCourse(course));

			session.beginTransaction();
			// Course updatedCourse = (Course) session.merge(course);
			session.saveOrUpdate(course);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new DatabaseServiceException("Database service exception -- exeption in deleteByID");
		} finally {
			session.close();
		}

	}

	@Override
	public List<Level> viewLevels() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("From Level", Level.class).getResultList();

	}

	@Override
	public List<Category> viewCategories() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("From Category", Category.class).getResultList();
	}

	@Override
	public void switchStatus(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Course course = session.get(Course.class, id);

		if (course.getIsActive())
			course.setIsActive(false);
		else
			course.setIsActive(true);
		session.saveOrUpdate(course);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Level viewLevelById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Level level = session.get(Level.class, id);
		session.getTransaction().commit();
		session.close();

		return level;
	}

	@Override
	public Category viewCategoryById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Category category = session.get(Category.class, id);
		session.getTransaction().commit();
		session.close();
		return category;
	}

}

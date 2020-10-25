package school.services;

import java.util.List;

import school.model.Course;
import school.model.enumvalue.Coursetype;
import school.model.enumvalue.Schclass;

public interface CourseService {

	public List<Course> findAll();
	public Course getCourseByID(Long id);
	public List<Course> getCourseByIDDesc();
	public Course getSingleCourseByID();
	public List<Course> getCourseBySchclass(Schclass schclass);
	public List<Course> getCourseByClassAndType(Schclass schclass,Coursetype coursetype);

}

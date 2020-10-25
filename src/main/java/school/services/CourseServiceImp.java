package school.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.model.Course;
import school.model.enumvalue.Coursetype;
import school.model.enumvalue.Schclass;
import school.repository.CourseRepository;

@Service
public class CourseServiceImp implements CourseService {

	@Autowired
	CourseRepository courseRepository;
	
	@Override
	public List<Course> findAll() {
		List<Course> course=(List<Course>) courseRepository.findAll();		
		return course;
	}

	@Override
	public Course getCourseByID(Long id) {
		Course course=courseRepository.findById(id);
		return course;
	}

	@Override
	public List<Course> getCourseByIDDesc() {
		List<Course> course=courseRepository.findByOrderByIdDesc();
		return course;
	}

	@Override
	public Course getSingleCourseByID() {
		Course course=courseRepository.findTopByOrderByIdDesc();
		return course;
	}
	
	
	@Override
	public List<Course> getCourseBySchclass(Schclass schclass) {
		List<Course> course=courseRepository.findBySchclass(schclass);
		return course;
	}

	@Override
	public List<Course> getCourseByClassAndType(Schclass schclass, Coursetype coursetype) {
		List<Course> course=courseRepository.findBySchclassAndCoursetype(schclass, coursetype);
		return course;
	}
}

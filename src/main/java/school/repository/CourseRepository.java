package school.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import school.model.Course;
import school.model.enumvalue.Coursetype;
import school.model.enumvalue.Schclass;
public interface CourseRepository extends CrudRepository<Course, Long>{

	public Course findById(Long id);
	public List<Course> findByOrderByIdDesc();
	public Course findTopByOrderByIdDesc();
	public List<Course> findBySchclass(Schclass schclass);
	public List<Course> findBySchclassAndCoursetype(Schclass schclass, Coursetype coursetype);

}

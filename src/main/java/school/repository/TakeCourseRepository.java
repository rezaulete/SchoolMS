package school.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import school.model.TakeCourse;
public interface TakeCourseRepository extends CrudRepository<TakeCourse, Long>{

	public TakeCourse findById(Long id);
	public TakeCourse findByStudentId(Long id);
	public List<TakeCourse> findByOrderByIdDesc();
	public TakeCourse findTopByOrderByIdDesc();

}

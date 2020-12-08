package school.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import school.model.Teacher;
public interface TeacherRepository extends CrudRepository<Teacher, Long>{

	public Teacher findById(Long id);
	public List<Teacher> findByOrderByIdDesc();
	public Teacher findTopByOrderByIdDesc();

}

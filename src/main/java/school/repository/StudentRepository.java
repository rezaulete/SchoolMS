package school.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import school.model.Student;
import school.model.enumvalue.Schclass;
public interface StudentRepository extends CrudRepository<Student, Long>{

	public Student findById(Long id);
	public List<Student> findByOrderByIdDesc();
	public Student findTopByOrderByIdDesc();
	public List<Student> findBySchclass(Schclass schclass);

}

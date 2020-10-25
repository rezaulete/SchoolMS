package school.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import school.model.Exam;
import school.model.Marks;
import school.model.Student;
public interface MarksRepository extends CrudRepository<Marks, Long>{

	public Marks findById(Long id);
	public List<Marks> findByOrderByIdDesc();
	public Marks findTopByOrderByIdDesc();
	public List<Marks> findByStudentAndExam(Student student, Exam exam);

}

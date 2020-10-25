package school.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import school.model.Exam;
public interface ExamRepository extends CrudRepository<Exam, Long>{

	public Exam findById(Long id);
	public List<Exam> findByOrderByIdDesc();
	public Exam findTopByOrderByIdDesc();

}

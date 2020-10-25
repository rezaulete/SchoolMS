package school.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import school.model.ExamResult;

public interface ExamResultRepository extends CrudRepository<ExamResult, Long>{

	public ExamResult findById(Long id);

	public List<ExamResult> findByOrderByIdDesc();
	public ExamResult findTopByOrderByIdDesc();

}

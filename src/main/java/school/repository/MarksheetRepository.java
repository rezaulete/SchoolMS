package school.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import school.model.Marksheet;
public interface MarksheetRepository extends CrudRepository<Marksheet, Long>{

	public Marksheet findById(Long id);
	public List<Marksheet> findByOrderByIdDesc();
	public Marksheet findTopByOrderByIdDesc();

}

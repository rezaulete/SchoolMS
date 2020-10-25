package school.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import school.model.Stsession;
public interface StsessionRepository extends CrudRepository<Stsession, Long>{

	public Stsession findById(Long id);
	public List<Stsession> findByOrderByIdDesc();
	public Stsession findTopByOrderByIdDesc();

}

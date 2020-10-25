package school.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import school.model.Section;
import school.model.enumvalue.Schclass;

public interface SectionRepository extends CrudRepository<Section, Long> {

	public Section findById(Long id);

	public List<Section> findBySchclass(Schclass schclass);

	public List<Section> findByOrderByIdDesc();

	public Section findTopByOrderByIdDesc();

}

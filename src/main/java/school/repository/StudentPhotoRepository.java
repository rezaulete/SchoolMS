package school.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import school.model.Student;
import school.model.StudentPhoto;
public interface StudentPhotoRepository extends CrudRepository<StudentPhoto, Long>{

	public StudentPhoto findById(Long id);
	public StudentPhoto findByStudent(Student student);
	public List<StudentPhoto> findByOrderByIdDesc();
	public StudentPhoto findTopByOrderByIdDesc();
	
}

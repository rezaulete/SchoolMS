package school.repository;


import org.springframework.data.repository.CrudRepository;


import school.model.Teacher;
import school.model.TeacherPhoto;
public interface TeacherPhotoRepository extends CrudRepository<TeacherPhoto, Long>{

	public TeacherPhoto findById(Long id);
	public TeacherPhoto findByTeacher(Teacher teacher);

	
}

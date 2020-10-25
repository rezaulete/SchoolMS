package school.repository;


import org.springframework.data.repository.CrudRepository;

import school.model.Student;
import school.model.StudentDetails;

public interface StudentDetailsRepository extends CrudRepository<StudentDetails, Long>{

	public StudentDetails findById(Long id);
	public StudentDetails findByStudent(Student student);
}

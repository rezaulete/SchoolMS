package school.services;

import java.util.List;

import school.model.Student;
import school.model.enumvalue.Schclass;

public interface StudentService {

	public List<Student> findAll();
	public Student getStudentByID(Long id);
	public List<Student> getStudentByIDDesc();
	public Student getSingleStudentByID();
	public List<Student> getStudentByClass(Schclass schclass);
	public Student DeleteStudentByID(Long id);

}

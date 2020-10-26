package school.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.model.Student;
import school.model.enumvalue.Schclass;
import school.repository.StudentRepository;

@Service
public class StudentServiceImp implements StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public List<Student> findAll() {
		List<Student> student=(List<Student>) studentRepository.findAll();		
		return student;
	}

	@Override
	public Student getStudentByID(Long id) {
		Student student=studentRepository.findById(id);
		return student;
	}

	@Override
	public List<Student> getStudentByIDDesc() {
		List<Student> student=studentRepository.findByOrderByIdDesc();
		return student;
	}

	@Override
	public Student getSingleStudentByID() {
		Student student=studentRepository.findTopByOrderByIdDesc();
		return student;
	}
	
	
	@Override
	public List<Student> getStudentByClass(Schclass schclass) {
		List<Student> student=studentRepository.findBySchclass(schclass);
		return student;
	}

	@Override
	public Student DeleteStudentByID(Long id) {

		return null;
	}
}

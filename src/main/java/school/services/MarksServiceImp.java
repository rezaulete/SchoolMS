package school.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.model.Marks;
import school.repository.MarksRepository;

@Service
public class MarksServiceImp implements MarksService {

	@Autowired
	MarksRepository marksRepository;
	
	@Override
	public List<Marks> findAll() {
		List<Marks> marks=(List<Marks>) marksRepository.findAll();		
		return marks;
	}

	@Override
	public Marks getMarksByID(Long id) {
		Marks marks=marksRepository.findById(id);
		return marks;
	}

	@Override
	public List<Marks> getMarksByIDDesc() {
		List<Marks> marks=marksRepository.findByOrderByIdDesc();
		return marks;
	}

	@Override
	public Marks getSingleMarksByID() {
		Marks marks=marksRepository.findTopByOrderByIdDesc();
		return marks;
	}
	

}

package school.services;

import java.util.List;

import school.model.Marks;

public interface MarksService {

	public List<Marks> findAll();
	public Marks getMarksByID(Long id);
	public List<Marks> getMarksByIDDesc();
	public Marks getSingleMarksByID();

}

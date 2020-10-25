package school.services;

import java.util.List;

import school.model.HomeSlider;

public interface HomeSlideService {
	
	public List<HomeSlider> findAll();
	public HomeSlider getHomeSliderByID(Long id);
	public HomeSlider getSingleHomeSlider();

}

package school.controller.AdminController;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import school.model.Course;
import school.model.enumvalue.Schclass;
import school.repository.CourseRepository;
import school.services.CourseService;

@Controller
@RequestMapping("/admincourse")
public class AdminCourseController {
	
	@Autowired
	CourseRepository courseRepository;	
	@Autowired
	CourseService courseService;
	 // Open about us table 
    @RequestMapping(value= {"/","/index"})
    public String home(Model model) {
        model.addAttribute("course",courseRepository.findAll());    
        return "dashboards/courses/index";
    }
    
    @RequestMapping(value="/course/{schclass}")
    public String course(Model model,@PathVariable("schclass") Schclass schclass) {
    	model.addAttribute("course",courseService.getCourseBySchclass(schclass));   
        model.addAttribute("schclass", schclass);    
        return "dashboards/courses/course";
    }
    
    // Open course Insert Form  
    @RequestMapping("/insert/{schclass}")
    public String insert(Model model
    		,@PathVariable("schclass") Schclass schclass) {
        model.addAttribute("course",new Course());
        model.addAttribute("schclass", schclass);   
        return "dashboards/courses/insert";
    }
    
    
    // Save about us Information  
    @PostMapping("/save-course")
    public String save(Model model,@Valid @ModelAttribute("course") Course course
    		,BindingResult bindingResult
    		,RedirectAttributes redirectAttributes
    		) {
   //If there are some error...	
    if (bindingResult.hasErrors()) {			
		       
		        model.addAttribute("errorcourse", "Something wrong...");
		        model.addAttribute("course",course);
		        return "dashboards/courses/insert";
       }	 
		    	
                courseRepository.save(course);
                redirectAttributes.addAttribute("schclass",course.getSchclass());
                return "redirect:/admincourse/course/{schclass}";
    }

    
    // Delete about us file by id 
	@RequestMapping("/delete-course") 
    public String delete(Model model
    		,@RequestParam Long id) {
		 courseRepository.delete(id); 
		     return "redirect:/admincourse/index";
    }
	
	
	@RequestMapping("/edit-course") 
	public String edit(Model model,@RequestParam Long id) {        
        model.addAttribute("course",courseRepository.findById(id));
        model.addAttribute("Grade", Schclass.values());  
        return "dashboards/courses/edit";
	}
	
	
	@PostMapping("/update-course") 
    public String Update(Model model,@ModelAttribute Course course
    		, BindingResult bindingResult
    		,RedirectAttributes redirectAttributes
    		) {	

           //If there are some error...	
         if (bindingResult.hasErrors()) {	
 		        model.addAttribute("errorcourse", "Something wrong...");		 
 		        model.addAttribute("course",course);
 		        return "dashboards/courses/edit";
        }	         		    	
                 courseRepository.save(course);
                 redirectAttributes.addAttribute("schclass",course.getSchclass());
                 return "redirect:/admincourse/course/{schclass}";
    }
    

}

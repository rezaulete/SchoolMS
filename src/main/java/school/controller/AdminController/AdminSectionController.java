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

import school.model.Section;
import school.model.enumvalue.Schclass;
import school.repository.SectionRepository;

@Controller
@RequestMapping("/adminsection")
public class AdminSectionController {
	
	@Autowired
	SectionRepository sectionRepository;	
		
	 // Open about us table 
    @RequestMapping(value= {"/","/index"})
    public String home(Model model) {
    	 return "dashboards/sections/index";
    }
    
    @RequestMapping(value="/section/{schclass}")
    public String section(Model model,@PathVariable("schclass") Schclass schclass) {
    	model.addAttribute("section",sectionRepository.findBySchclass(schclass));   
        model.addAttribute("schclass", schclass);    
        return "dashboards/sections/section";
    }
    
    // Open section Insert Form  
    @RequestMapping("/insert/{schclass}")
    public String insert(Model model
    		,@PathVariable("schclass") Schclass schclass) {
        model.addAttribute("section",new Section());
        model.addAttribute("schclass", schclass);   
        return "dashboards/sections/insert";
    }
    
    
    // Save about us Information  
    @PostMapping("/save-section")
    public String save(Model model,@Valid @ModelAttribute("section") Section section
    		,BindingResult bindingResult
    		,RedirectAttributes redirectAttributes
    		) {
   //If there are some error...	
    if (bindingResult.hasErrors()) {			
		       
		        model.addAttribute("errorsection", "Something wrong...");
		        model.addAttribute("section",section);
		        return "dashboards/sections/insert";
       }	 
		    	
                sectionRepository.save(section);
                redirectAttributes.addAttribute("schclass",section.getSchclass());
                return "redirect:/adminsection/section/{schclass}";
    }

    
    // Delete about us file by id 
	@RequestMapping("/delete-section") 
    public String delete(Model model
    		,@RequestParam Long id
    		,RedirectAttributes redirectAttributes) {
		Section section=sectionRepository.findById(id);
		      sectionRepository.delete(id); 
		      redirectAttributes.addAttribute("schclass",section.getSchclass());
              return "redirect:/adminsection/section/{schclass}";
    }
	
	
	@RequestMapping("/edit-section") 
	public String edit(Model model,@RequestParam Long id) {        
        model.addAttribute("section",sectionRepository.findById(id)); 
        return "dashboards/sections/edit";
	}
	
	
	@PostMapping("/update-section") 
    public String Update(Model model,@ModelAttribute Section section
    		, BindingResult bindingResult
    		,RedirectAttributes redirectAttributes
    		) {	

           //If there are some error...	
         if (bindingResult.hasErrors()) {	
 		        model.addAttribute("errorsection", "Something wrong...");		 
 		        model.addAttribute("section",section);
 		        return "dashboards/sections/edit";
        }	         		    	
                 sectionRepository.save(section);
                 redirectAttributes.addAttribute("schclass",section.getSchclass());
                 return "redirect:/adminsection/section/{schclass}";
    }
    

}

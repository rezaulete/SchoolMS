package school.controller.AdminController;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import school.model.Stsession;
import school.repository.StsessionRepository;

@Controller
@RequestMapping("/adminstsession")
public class AdminStsessionController {
	
	@Autowired
	StsessionRepository stsessionRepository;	
		
	 // Open about us table 
    @RequestMapping(value= {"/","/index"})
    public String home(Model model) {
        model.addAttribute("stsession",stsessionRepository.findAll());    
        return "dashboards/stsessions/index";
    }
   
    // Open stsession Insert Form  
    @RequestMapping("/insert")
    public String insert(Model model) {
        model.addAttribute("stsession",new Stsession());
        return "dashboards/stsessions/insert";
    }
    
    
    // Save about us Information  
    @PostMapping("/save-stsession")
    public String save(Model model,@Valid @ModelAttribute("stsession") Stsession stsession
    		,BindingResult bindingResult
    		,RedirectAttributes redirectAttributes
    		) {
   //If there are some error...	
    if (bindingResult.hasErrors()) {			
		       
		        model.addAttribute("errorstsession", "Something wrong...");
		        model.addAttribute("stsession",stsession);
		        return "dashboards/stsessions/insert";
       }	 
		    	
                stsessionRepository.save(stsession);
                return "redirect:/adminstsession/index";
    }

    
    // Delete about us file by id 
	@RequestMapping("/delete-stsession") 
    public String delete(Model model
    		,@RequestParam Long id) {
		
		try {
			stsessionRepository.delete(id); 
			
		} catch (Exception e) {
			model.addAttribute("stsession",stsessionRepository.findAll());  
			model.addAttribute("errormessage","Can't delete this data."); 
			model.addAttribute("error", e.getMessage()); 
			return "dashboards/stsessions/index";
		}
		     return "redirect:/adminstsession/index";
    }
	
	
	@RequestMapping("/edit-stsession") 
	public String edit(Model model,@RequestParam Long id) {        
        model.addAttribute("stsession",stsessionRepository.findById(id)); 
        return "dashboards/stsessions/edit";
	}
	
	
	@PostMapping("/update-stsession") 
    public String Update(Model model,@ModelAttribute Stsession stsession
    		, BindingResult bindingResult
    		,RedirectAttributes redirectAttributes
    		) {	

           //If there are some error...	
         if (bindingResult.hasErrors()) {	
 		        model.addAttribute("errorstsession", "Something wrong...");		 
 		        model.addAttribute("stsession",stsession);
 		        return "dashboards/stsessions/edit";
        }	         		    	
                 stsessionRepository.save(stsession);
                 return "redirect:/adminstsession/index";
    }
    

}

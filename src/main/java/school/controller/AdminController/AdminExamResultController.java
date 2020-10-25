package school.controller.AdminController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import school.model.ExamResult;
import school.repository.ExamResultRepository;
import school.services.ExamResultService;



@Controller
@RequestMapping("/adminexamResult")
public class AdminExamResultController {
	
	@Autowired
	ExamResultRepository examResultRepository;
	@Autowired
	ExamResultService examResultService;
	
	private static String UPLOAD_FOLDER = "src//main//resources//static//AllFiles//";
	
    @RequestMapping(value= {"/","/index","/show"})
    public String home(Model model) {
        model.addAttribute("examResult",examResultService.findAll());
        return "dashboards/examResult/index";
    }
    
    
    // Open about us Insert Form  
    @RequestMapping("/insert")
    public String insert(Model model) {
    	model.addAttribute("examResult",new ExamResult());
        return "dashboards/examResult/insert";
    }
    
    // Save about us Information  
    @PostMapping("/save-examResult")
    public String save(Model model,@Valid @ModelAttribute("examResult") ExamResult examResult
    		, BindingResult bindingResult
    		,@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes
    		) {
    	int size=(int) file.getSize();     
       if (file.isEmpty()) {
			
			model.addAttribute("message", "Please select a file and try again");
			model.addAttribute("examResult",examResult);
			return "dashboards/examResult/insert";
	          	}
       else  if(size>10000000){
       	model.addAttribute("errormessage", "Input file is more than 10MB");
       	model.addAttribute("message", "File is to large. please thy file below 10MB ");
       	model.addAttribute("examResult",examResult);
			return "dashboards/examResult/insert";
             }
       
       else if (bindingResult.hasErrors()) {			
	       
           	model.addAttribute("examResult",examResult);
  			        return "dashboards/examResult/insert";
             }
       else {
       
		try {
			// read and write the file to the selected location-
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER +examResult.getTitle()+file.getOriginalFilename());
			Files.write(path, bytes);
			examResult.setPicFile(examResult.getTitle()+file.getOriginalFilename());
			System.out.print(examResult.getTitle()+file.getOriginalFilename());

		} catch (IOException e) {
			e.printStackTrace();
		}
	
            examResultRepository.save(examResult);
            return "redirect:/adminexamResult/show";
    }}

    
    // Delete about us file by id 
	@RequestMapping("/delete-examResult") 
    public String delete(Model model
    		,@RequestParam Long id) {
		
		ExamResult examResult=examResultService.getExamResultByID(id); 
		try { 
            Path path=Paths.get(UPLOAD_FOLDER+examResult.getPicFile());
            Files.delete(path);
            System.out.println("Image Deleted !!!");
           
            
		}catch(Exception e)
        {
            System.out.println("Failed to Delete image !!");       
           
        }
		examResultRepository.delete(id); 
		     return "redirect:/adminexamResult/show";
    }
	
	
	@RequestMapping("/edit-examResult") 
	public String edit(Model model,@RequestParam Long id) {        
        model.addAttribute("examResult",examResultService.getExamResultByID(id));
        return "dashboards/examResult/edit";
	}
	
	
	@PostMapping("/update-examResult") 
    public String Update(Model model,@ModelAttribute ExamResult examResult
    		, BindingResult bindingResult
    		,@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes
    		) {
	
		int size=(int) file.getSize(); 
		if (file.isEmpty()) {
			examResult.setPicFile(examResult.getPicFile());			
	      	}
		else  if(size>10000000){
	       	model.addAttribute("errormessage", "Input file is more than 10MB");
	       	model.addAttribute("message", "File is to large. please thy file below 10MB ");
	       	model.addAttribute("examResult",examResult);
			return "dashboards/examResult/edit";
	         }
	    
	       else {
	    	   try { 
	               Path path=Paths.get(UPLOAD_FOLDER+examResult.getPicFile());
	               Files.delete(path);
	               System.out.println("Image Deleted !!!");
	              
	               
	   		}catch(Exception e)
	           {
	               System.out.println("Failed to Delete image !!");       
	              
	           }
	       
			try {
				// read and write the file to the selected location-
				byte[] bytes = file.getBytes();
				Path path = Paths.get(UPLOAD_FOLDER +examResult.getTitle()+file.getOriginalFilename());
				Files.write(path, bytes);
				examResult.setPicFile(examResult.getTitle()+file.getOriginalFilename());
				System.out.print(examResult.getTitle()+file.getOriginalFilename());

			} catch (IOException e) {
				e.printStackTrace();
			}}
			if (bindingResult.hasErrors()) {			
			       
	           	model.addAttribute("examResult",examResult);
	  			        return "dashboards/examResult/insert";
	             }
		examResultRepository.save(examResult);
        return "redirect:/adminexamResult/show";
   
     }
}

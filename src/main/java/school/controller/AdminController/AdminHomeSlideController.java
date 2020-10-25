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

import school.model.HomeSlider;
import school.repository.HomeSliderRepository;
import school.services.HomeSlideService;




@Controller
@RequestMapping("/adminhomeslide")
public class AdminHomeSlideController {
	
	@Autowired
	HomeSliderRepository homeSliderRepository;	
	@Autowired
	HomeSlideService homeSlideService;
	
	
	private static String UPLOAD_FOLDER = "src//main//resources//static//images//HomeSlideImage//";
	
	
	 // Open about us table 
    @RequestMapping(value= {"/","/index"})
    public String home(Model model) {
        model.addAttribute("homeSlider",homeSliderRepository.findAll());     
        return "dashboards/HomeSlide/index";
    }
    
    
    // Open about us Insert Form  
    @RequestMapping("/insert")
    public String insert(Model model) {
        model.addAttribute("homeSlider",new HomeSlider());      
        return "dashboards/HomeSlide/insert";
    }
    
    
    // Save about us Information  
    @PostMapping("/save-homeslider")
    public String save(Model model,@Valid @ModelAttribute("homeSlider") HomeSlider homeSlider
    		,BindingResult bindingResult
    		,@RequestParam("file1") MultipartFile file1 
    		,@RequestParam("file2") MultipartFile file2
    		,@RequestParam("file3") MultipartFile file3
    		,RedirectAttributes redirectAttributes
    		) {
    	 int size1=(int) file1.getSize(); 
    	 int size2=(int) file2.getSize(); 
    	 int size3=(int) file3.getSize(); 
         if (file1.isEmpty()) {
			model.addAttribute("message1", "Please select a file and try again");
			model.addAttribute("coCurricular",homeSlider);      
	        return "dashboards/HomeSlide/insert";
	          	} 
       
        if(size1>1500000){
        	model.addAttribute("errormessage", "Input file is more than 1.5MB");
        	model.addAttribute("message1", "File is to large. please thy file below 1.5MB ");
        	model.addAttribute("homeSlider",homeSlider);   
	        return "dashboards/HomeSlide/insert";
  
                }
        
        if (file2.isEmpty()) {
			model.addAttribute("message1", "Please select a file and try again");
			model.addAttribute("coCurricular",homeSlider);      
	        return "dashboards/HomeSlide/insert";
	          	} 
       
        if(size2>1500000){
        	model.addAttribute("errormessage", "Input file is more than 1.5MB");
        	model.addAttribute("message1", "File is to large. please thy file below 1.5MB ");
        	model.addAttribute("homeSlider",homeSlider);   
	        return "dashboards/HomeSlide/insert";
  
                }
        
        if (file3.isEmpty()) {
			model.addAttribute("message1", "Please select a file and try again");
			model.addAttribute("coCurricular",homeSlider);      
	        return "dashboards/HomeSlide/insert";
	          	} 
       
        if(size3>1500000){
        	model.addAttribute("errormessage", "Input file is more than 1.5MB");
        	model.addAttribute("message1", "File is to large. please thy file below 1.5MB ");
        	model.addAttribute("homeSlider",homeSlider);   
	        return "dashboards/HomeSlide/insert";
  
                }
        
 
   //If there are some error...	
    if (bindingResult.hasErrors()) {			
		       
		        model.addAttribute("errormessage", "Something wrong...");
		        model.addAttribute("homeSlider",homeSlider);       
		        return "dashboards/HomeSlide/insert";
       }	 
       
		try {
			// read and write the file to the selected location-
			byte[] bytes1 = file1.getBytes();
			Path path1 = Paths.get(UPLOAD_FOLDER +"Slide1"+file1.getOriginalFilename());
			Files.write(path1, bytes1);
			homeSlider.setPicFile1("Slide1"+file1.getOriginalFilename());

		}  catch (IOException e) {e.printStackTrace();	}
		try {
			// read and write the file to the selected location-
			byte[] bytes2 = file2.getBytes();
			Path path2 = Paths.get(UPLOAD_FOLDER +"Slide2"+file2.getOriginalFilename());
			Files.write(path2, bytes2);
			homeSlider.setPicFile2("Slide2"+file2.getOriginalFilename());

		} catch (IOException e) {e.printStackTrace();}
		try {
			// read and write the file to the selected location-
			byte[] bytes3 = file3.getBytes();
			Path path3 = Paths.get(UPLOAD_FOLDER +"Slide3"+file3.getOriginalFilename());
			Files.write(path3, bytes3);
			homeSlider.setPicFile3("Slide3"+file3.getOriginalFilename());

		} catch (IOException e) {
			e.printStackTrace();
		}
		    	
		        homeSliderRepository.save(homeSlider);
                return "redirect:/adminhomeslide/index";
    }

    
    // Delete about us file by id 
	@RequestMapping("/delete-homeslider") 
    public String delete(Model model
    		,@RequestParam Long id) {
		HomeSlider homeSlider=homeSlideService.getHomeSliderByID(id); 
		try { 
            Path path1=Paths.get(UPLOAD_FOLDER+homeSlider.getPicFile1());
            Files.delete(path1);
            
		}catch(Exception e){ System.out.println("Failed to Delete image !!"); }	
		try { 
            Path path2=Paths.get(UPLOAD_FOLDER+homeSlider.getPicFile2());
            Files.delete(path2);
            
		}catch(Exception e){ System.out.println("Failed to Delete image !!"); }	
		try { 
            Path path3=Paths.get(UPLOAD_FOLDER+homeSlider.getPicFile3());
            Files.delete(path3);
            
		}catch(Exception e){ System.out.println("Failed to Delete image !!"); }	
		
		
		homeSliderRepository.delete(id); 
		return "redirect:/adminhomeslide/index";
    }
	
	
	@RequestMapping("/edit-homeslider") 
	public String edit(Model model,@RequestParam Long id) {        
        model.addAttribute("homeSlider",homeSlideService.getHomeSliderByID(id));      
        return "dashboards/HomeSlide/edit";
	}
	
	
	@PostMapping("/update-homeslider") 
    public String Update(Model model,@ModelAttribute HomeSlider homeSlider
    		, BindingResult bindingResult
    		,@RequestParam("file1") MultipartFile file1 
    		,@RequestParam("file2") MultipartFile file2
    		,@RequestParam("file3") MultipartFile file3
    		, RedirectAttributes redirectAttributes
    		) {	
		
		 int size1=(int) file1.getSize(); 
    	 int size2=(int) file2.getSize(); 
    	 int size3=(int) file3.getSize();  
		    if (file1.isEmpty()) {
			      homeSlider.setPicFile1(homeSlider.getPicFile1());			
	            	}
		    if(size1>1500000){
	        	model.addAttribute("errormessage", "Input file is more than 1.5MB");
	        	model.addAttribute("message1", "File is to large. please thy file below 1.5MB ");
	        	model.addAttribute("homeSlider",homeSlider);   
		        return "dashboards/HomeSlide/insert";
	            }
		    if (file2.isEmpty()) {
	        	homeSlider.setPicFile2(homeSlider.getPicFile2());
		          	} 
		    if(size2>1500000){
	        	model.addAttribute("errormessage", "Input file is more than 1.5MB");
	        	model.addAttribute("message1", "File is to large. please thy file below 1.5MB ");
	        	model.addAttribute("homeSlider",homeSlider);   
		        return "dashboards/HomeSlide/insert";
	             }
		    if (file3.isEmpty()) {
	        	homeSlider.setPicFile3(homeSlider.getPicFile3());
		          	} 
		    if(size3>1500000){
	        	model.addAttribute("errormessage", "Input file is more than 1.5MB");
	        	model.addAttribute("message1", "File is to large. please thy file below 1.5MB ");
	        	model.addAttribute("homeSlider",homeSlider);   
		        return "dashboards/HomeSlide/insert";
	  
	                }

           //If there are some error...	
         if (bindingResult.hasErrors()) {	
 		        model.addAttribute("errormessage", "Something wrong...");		 
 		        model.addAttribute("homeSlider",homeSlider);       
 		        return "dashboards/HomeSlide/edit";
        }	    
         try { 
             Path path1=Paths.get(UPLOAD_FOLDER+homeSlider.getPicFile1());
             Files.delete(path1);
             
 		}catch(Exception e){ System.out.println("Failed to Delete image !!"); }	
 		try { 
             Path path2=Paths.get(UPLOAD_FOLDER+homeSlider.getPicFile2());
             Files.delete(path2);
             
 		}catch(Exception e){ System.out.println("Failed to Delete image !!"); }	
 		try { 
             Path path3=Paths.get(UPLOAD_FOLDER+homeSlider.getPicFile3());
             Files.delete(path3);
             
 		}catch(Exception e){ System.out.println("Failed to Delete image !!"); }	
 		
 		
     	try {
			// read and write the file to the selected location-
			byte[] bytes1 = file1.getBytes();
			Path path1 = Paths.get(UPLOAD_FOLDER +"Slide1"+file1.getOriginalFilename());
			Files.write(path1, bytes1);
			homeSlider.setPicFile1("Slide1"+file1.getOriginalFilename());

		}  catch (IOException e) {e.printStackTrace();	}
		try {
			// read and write the file to the selected location-
			byte[] bytes2 = file2.getBytes();
			Path path2 = Paths.get(UPLOAD_FOLDER +"Slide2"+file2.getOriginalFilename());
			Files.write(path2, bytes2);
			homeSlider.setPicFile2("Slide2"+file2.getOriginalFilename());

		} catch (IOException e) {e.printStackTrace();}
		try {
			// read and write the file to the selected location-
			byte[] bytes3 = file3.getBytes();
			Path path3 = Paths.get(UPLOAD_FOLDER +"Slide3"+file3.getOriginalFilename());
			Files.write(path3, bytes3);
			homeSlider.setPicFile3("Slide3"+file3.getOriginalFilename());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
         homeSliderRepository.save(homeSlider);
       	return "redirect:/adminhomeslide/index";
    }
    
}

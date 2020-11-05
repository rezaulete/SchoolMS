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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import school.model.Teacher;
import school.model.TeacherPhoto;
import school.model.teacher;
import school.model.teacherDetails;
import school.model.teacherPhoto;
import school.model.enumvalue.Gender;
import school.model.enumvalue.Groups;
import school.model.enumvalue.Isactive;
import school.model.enumvalue.Regularity;
import school.model.enumvalue.Schclass;
import school.model.enumvalue.Version;
import school.repository.SectionRepository;
import school.repository.StsessionRepository;
import school.repository.TeacherPhotoRepository;
import school.repository.teacherDetailsRepository;
import school.repository.teacherPhotoRepository;
import school.repository.teacherRepository;
import school.repository.TeacherRepository;
import school.services.FilePhotoService;
import school.services.teacherService;


/**
*
* @author Md Rezaul karim
*/

@Controller
@RequestMapping("/dashboards/teacher")
public class AdminTeacherController {

	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	TeacherPhotoRepository teacherPhotoRepository; 
	@Autowired
	FilePhotoService filePhotoService;

	// Open Class for Teacher table
	@RequestMapping(value = { "/", "/index" })
	public String home(Model model) {
		model.addAttribute("teacher", teacherRepository.findAll());
		return "dashboards/teachers/index";
	}

	

	// Open a single teacher from the list
	@RequestMapping("/view-teacher/{id}")
	public String view(Model model, @PathVariable("id") Long id) {
		Teacher teacher= teacherRepository.findById(id);
		model.addAttribute("teacher", teacher);
		model.addAttribute("teacherPhoto", teacherPhotoRepository.findByTeacher(teacher));	

		return "dashboards/teachers/view";
	}

	
	
	// Inserting new teacher to the list
	@RequestMapping(value = "/insert/{schclass}")
	public String insert(Model model, @PathVariable("schclass") Schclass schclass) {
		
		model.addAttribute("teacher", new Teacher());
		model.addAttribute("Gender", Gender.values());
		model.addAttribute("IsActive", Isactive.values());
		return "dashboards/teachers/insert";
	}

	
	
	// Save teacher Information to the list 
	@PostMapping("/save-teacher")
	public String save(Model model, @Valid @ModelAttribute("teacher") Teacher teacher, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
		// If there are some error...
		if (bindingResult.hasErrors()) {

			model.addAttribute("errorteacher", "Something wrong...");
			model.addAttribute("teacher", teacher);
			return "dashboards/teachers/insert";
		}

		teacherRepository.save(teacher);
		return "redirect:/dashboards/teachers/";
	}

	
	
	
	//Delete teacher file by id
	@RequestMapping("/delete-teacher")
	public String delete(Model model, @RequestParam Long id, RedirectAttributes redirectAttributes) {
		Teacher teacher = teacherRepository.findById(id);

			  teacherRepository.delete(id); 

		return "redirect:/dashboards/teachers/";
	}
	
	
	
	
	
	// Edit a single teacher from the list
	@RequestMapping("/edit-teacher")
	public String edit(Model model, @RequestParam Long id) {
		Teacher teacher = teacherRepository.findById(id);

		model.addAttribute("teacher", teacherRepository.findById(id));
		model.addAttribute("Version", Version.values());
		model.addAttribute("Gender", Gender.values());
		model.addAttribute("Regularity", Regularity.values());
		model.addAttribute("Groups", Groups.values());
		model.addAttribute("Isactive", Isactive.values());
		return "dashboards/teachers/edit";
	}

	
	
	// Update a single teacher data
	@PostMapping("/update-teacher")
	public String Update(Model model, @ModelAttribute Teacher teacher, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		// If there are some error...
		if (bindingResult.hasErrors()) {
			model.addAttribute("errorteacher", "Something wrong...");
			model.addAttribute("teacher", teacher);
			model.addAttribute("recent", teacherRepository.findByOrderByIdDesc());
			model.addAttribute("Gender", Gender.values());
			model.addAttribute("Isactive", Isactive.values());
			return "dashboards/teachers/edit";
		}
		teacherRepository.save(teacher);
		return "redirect:/dashboards/teachers/";
	}
	
	
	////// teacher PHOTO PART//////
	//Upload teacher Photo page
    @RequestMapping("/photoinsert/{stid}")
    public String teacherphotoinsert(Model model, @PathVariable("stid") Long stid) {
    	
    	model.addAttribute("teacher", teacherRepository.findById(stid));
    	model.addAttribute("teacherPhoto", new TeacherPhoto());
        return "dashboards/teachers/insertPhoto";
    }

    

    // Save teacher Photo 
    @PostMapping("/save-teacherphoto")
    public String teacherphotosave(Model model,@Valid @ModelAttribute("teacherPhoto") TeacherPhoto teacherPhoto
    		,BindingResult bindingResult
    		,@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes
    		) {
    	int size=(int) file.getSize();  	
    	Long stid=teacherPhoto.getTeacher().getId();
    	Teacher teacher= teacherRepository.findById(stid);
    	
       if (file.isEmpty()) {
 			
 			model.addAttribute("message", "Please select a file and try again");
 			model.addAttribute("teacherPhoto",teacherPhoto);
 			model.addAttribute("teacher", teacher);
 	        return "dashboards/teachers/insertPhoto";
 	          	} 
       
        if(size>1000000){
        	model.addAttribute("errormessage", "Input file is more than 1MB");
        	model.addAttribute("message", "File is to large. please thy file below 1MB ");
        	model.addAttribute("teacher", teacher);
        	model.addAttribute("teacherPhoto",teacherPhoto);
        
         return "dashboards/teachers/insertPhoto";
  
                }
        
    if (bindingResult.hasErrors()) {			
 		       
 		        model.addAttribute("errormessage", "Something wrong...");
 		        model.addAttribute("teacherPhoto",teacherPhoto); 
 		        model.addAttribute("teacher", teacher);
 		        return "dashboards/teachers/insertPhoto";
       }	 
       
 		try {
 			filePhotoService.UploadFile(file);

 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 				    	
 		        teacherPhotoRepository.save(teacherPhoto);
 		        redirectAttributes.addAttribute("stid", teacher.getId());
                return "redirect:/dashboards/teachers/view-teacher/{stid}";
    }
    
    
    
    
    // Delete teacher Photo by id 
 	@RequestMapping("/delete-teacherphoto") 
    public String teacherphotodelete(Model model
    		,@RequestParam Long id, RedirectAttributes redirectAttributes) {
 		
 		TeacherPhoto teacherPhoto=teacherPhotoRepository.findById(id); 
 		Long stid=teacherPhoto.getTeacher().getId();
 		Teacher teacher= teacherRepository.findById(stid);
 		redirectAttributes.addAttribute("stid", teacher.getId());
 		
 		try { 
            Path path=Paths.get(teacherPhoto.getPicFile());
            Files.delete(path);
            System.out.println("Image Deleted !!!"); 
 		}catch(Exception e)
        {
            System.out.println("Failed to Delete image !!");            
        }		
 		     teacherPhotoRepository.delete(id);  		
 		    		    
 		    return "redirect:/dashboards/teachers/view-teacher/{stid}";
    }
 	
 	
 	
    // Open edit teacher Photo page
 	@RequestMapping("/edit-teacherphoto/{stid}") 
 	public String edits(Model model, @PathVariable("stid") Long stid) {        
        model.addAttribute("teacherphoto",teacherPhotoRepository.findById(stid));        
        return "dashboards/teachers/editPhoto";
 	}
 	
 	
 	
 	
    // Update teacher new Photo 
 	@PostMapping("/update-teacherphoto") 
    public String teacherphotoUpdate(Model model,@ModelAttribute teacherPhoto teacherPhoto
    		, BindingResult bindingResult
    		,@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes
    		) {	
 		
 		int size=(int) file.getSize(); 
 		Long stid=teacherPhoto.getteacher().getId();
    	teacher teacher= teacherRepository.findById(stid);
 		if (file.isEmpty()) {
 			teacherPhoto.setPicFile(teacherPhoto.getPicFile());			
 	      	}
 		else  if(size>1000000){
         	model.addAttribute("errormessage", "Input file is more than 1MB");
         	model.addAttribute("message", "File is to large. please thy file below 1MB ");
 			model.addAttribute("teacherPhoto",teacherPhoto); 
 		    return "dashboards/teachers/editPhoto";
   
                 }
 		 else {
 				try { 
 		            Path path=Paths.get(UPLOAD_FOLDER+teacherPhoto.getPicFile());
 		            Files.delete(path);
 		            System.out.println("Image Deleted !!!");        
 				}catch(Exception e)
 		        {
 		            System.out.println("Failed to Delete image !!");       
 		           
 		        }				
 				try {
 					// read and write the file to the selected location-
 					byte[] bytes = file.getBytes();
 					Path path = Paths.get(UPLOAD_FOLDER +teacherPhoto.getPhototitle()+file.getOriginalFilename());
 					Files.write(path, bytes);
 					teacherPhoto.setPicFile(teacherPhoto.getPhototitle()+file.getOriginalFilename());					

 				}  catch (IOException e)  {    
 		             e.printStackTrace();
 				         }		
 			    }

           //If there are some error...	
         if (bindingResult.hasErrors()) {	
 		        model.addAttribute("errormessage", "Something wrong...");		 
 		        model.addAttribute("teacherPhoto",teacherPhoto);  
 		        return "dashboards/teachers/editPhoto";
        }	         		    	
                  teacherPhotoRepository.save(teacherPhoto);
                  redirectAttributes.addAttribute("stid", teacher.getId());
                  return "redirect:/dashboards/teachers/view-teacher/{stid}";
    }

 	
     //////teacher DETAILS PART//////
	//Insert teacher Details page
 	
    @RequestMapping("/insertdetails/{stid}")
    public String teacherdetailinsert(Model model, @PathVariable("stid") Long stid) {
    	
    	model.addAttribute("teacher", teacherRepository.findById(stid));
    	model.addAttribute("teacherdetail", new teacherDetails());
        return "dashboards/teachers/insertdetail";
    }
    
    
	// Save teacher Details 
	@PostMapping("/save-teacherdetail")
	public String savedetails(Model model, @Valid @ModelAttribute("teacherDetails") teacherDetails teacherDetails, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
 		Long stid=teacherDetails.getteacher().getId();
 		teacher teacher= teacherRepository.findById(stid);
		// If there are some error...
		if (bindingResult.hasErrors()) {

			model.addAttribute("errorteacher", "Something wrong...");
			model.addAttribute("teacherDetails", teacherDetails);
			return "dashboards/teachers/insert";
		}

		teacherDetailsRepository.save(teacherDetails);
		redirectAttributes.addAttribute("stid", teacher.getId());
        return "redirect:/dashboards/teachers/view-teacher/{stid}";
	}

    // Delete teacher Photo by id 
 	@RequestMapping("/delete-teacherdetails") 
    public String teacherdetailsdelete(Model model
    		,@RequestParam Long id, RedirectAttributes redirectAttributes) {
 		
 		teacherDetails teacherDetails=teacherDetailsRepository.findById(id); 
 		Long stid=teacherDetails.getteacher().getId();
 		teacher teacher= teacherRepository.findById(stid);
 		redirectAttributes.addAttribute("stid", teacher.getId());
 		
 		try {
 			teacherDetailsRepository.delete(id); 

		} catch (Exception e) {
			model.addAttribute("errormessage", "Can't delete this data.");
			model.addAttribute("error", e.getMessage());
			return "redirect:/dashboards/teachers/view-teacher/{stid}";
		}    		    
 		    return "redirect:/dashboards/teachers/view-teacher/{stid}";
    }
	
	
    // Open edit teacher Details page
 	@RequestMapping("/edit-teacherdetails/{stid}") 
 	public String editsdetails(Model model, @PathVariable("stid") Long stid) {        
 		model.addAttribute("teacherdetail", teacherDetailsRepository.findById(stid));       
        return "dashboards/teachers/editdetail";
 	}
 	
 	
    // Update teacher new Details 
  	@PostMapping("/update-teacherdetails") 
     public String teacherDetailsUpdate(Model model,@ModelAttribute teacherDetails teacherDetails
     		, BindingResult bindingResult, RedirectAttributes redirectAttributes
     		) {	
 
  		Long stid=teacherDetails.getteacher().getId();
     	teacher teacher= teacherRepository.findById(stid);
     	
     	if (bindingResult.hasErrors()) {
     		model.addAttribute("errormessage", "Something wrong...");		 
		        model.addAttribute("teacherDetails",teacherDetails);  
		        return "dashboards/teachers/editdetail";
     		
		}
     		
     	           teacherDetailsRepository.save(teacherDetails);
                   redirectAttributes.addAttribute("stid", teacher.getId());
                   return "redirect:/dashboards/teachers/view-teacher/{stid}";
     }

 	
}

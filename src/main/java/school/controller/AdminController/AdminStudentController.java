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

import school.model.Student;
import school.model.StudentPhoto;
import school.model.enumvalue.Gender;
import school.model.enumvalue.Groups;
import school.model.enumvalue.Isactive;
import school.model.enumvalue.Regularity;
import school.model.enumvalue.Schclass;
import school.model.enumvalue.Version;
import school.repository.SectionRepository;
import school.repository.StsessionRepository;
import school.repository.StudentDetailsRepository;
import school.repository.StudentPhotoRepository;
import school.repository.StudentRepository;
import school.services.StudentService;

@Controller
@RequestMapping("/dashboards/students")
public class AdminStudentController {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	StudentService studentService;
	@Autowired
	StsessionRepository stsessionRepository;
	@Autowired
	SectionRepository sectionRepository;
	@Autowired
	StudentPhotoRepository studentPhotoRepository;
	@Autowired
	StudentDetailsRepository studentDetailsRepository;
	
	private static String UPLOAD_FOLDER = "src//main//resources//static//images//StudentPhoto//";
	
	
	
	// Open Class for Student table
	@RequestMapping(value = { "/", "/index" })
	public String home(Model model) {
		model.addAttribute("student", studentRepository.findAll());
		return "dashboards/students/index";
	}

	
	
	// Open Student list table of a single class	
	@RequestMapping(value = "/{schclass}")
	public String student(Model model, @PathVariable("schclass") Schclass schclass) {
		model.addAttribute("student", studentService.getStudentByClass(schclass));
		return "dashboards/students/student";
	}
	
	

	// Open a single Student from the list
	@RequestMapping("/view-student/{id}")
	public String view(Model model, @PathVariable("id") Long id) {
		Student student= studentRepository.findById(id);
		model.addAttribute("student", student);
		model.addAttribute("studentPhoto", studentPhotoRepository.findByStudent(student));	
		model.addAttribute("studentDetails", studentDetailsRepository.findByStudent(student));	
		return "dashboards/students/view";
	}

	
	
	// Inserting new Student to the list
	@RequestMapping(value = "/insert/{schclass}")
	public String insert(Model model, @PathVariable("schclass") Schclass schclass) {
		model.addAttribute("section", sectionRepository.findBySchclass(schclass));
		model.addAttribute("stsession", stsessionRepository.findByOrderByIdDesc());
		model.addAttribute("recent", studentRepository.findByOrderByIdDesc());
		model.addAttribute("student", new Student());
		model.addAttribute("Version", Version.values());
		model.addAttribute("Gender", Gender.values());
		model.addAttribute("Regularity", Regularity.values());
		model.addAttribute("Groups", Groups.values());
		model.addAttribute("IsActive", Isactive.values());
		model.addAttribute("schclass", schclass);
		return "dashboards/students/insert";
	}

	
	
	// Save Student Information to the list 
	@PostMapping("/save-student")
	public String save(Model model, @Valid @ModelAttribute("student") Student student, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
		// If there are some error...
		if (bindingResult.hasErrors()) {

			model.addAttribute("errorstudent", "Something wrong...");
			model.addAttribute("student", student);
			return "dashboards/students/insert";
		}

		studentRepository.save(student);
		redirectAttributes.addAttribute("schclass", student.getSchclass());
		return "redirect:/dashboards/students/{schclass}";
	}

	
	
	
	//Delete Student file by id
	@RequestMapping("/delete-student")
	public String delete(Model model, @RequestParam Long id, RedirectAttributes redirectAttributes) {
		Student student = studentRepository.findById(id);
		Schclass schclass = student.getSchclass();
		StudentPhoto studentPhoto= studentPhotoRepository.findByStudent(student);
		if(studentPhoto!= null)
		{
			try { 
	            Path path=Paths.get(UPLOAD_FOLDER+studentPhoto.getPicFile());
	            Files.delete(path);
	            System.out.println("Image Deleted !!!"); 
	 		}catch(Exception e)
	        {
	            System.out.println("Failed to Delete image !!");            
	        }
			 studentPhotoRepository.delete(studentPhoto.getId()); 
			 
			 try {
					studentRepository.delete(id);

				} catch (Exception e) {
					model.addAttribute("stsession", stsessionRepository.findAll());
					model.addAttribute("errormessage", "Can't delete this data.");
					model.addAttribute("error", e.getMessage());
					model.addAttribute("student", studentService.getStudentByClass(schclass));
					return "dashboards/students/student";
				}
			 
		}
		else { 
		try {
			studentRepository.delete(id);

		} catch (Exception e) {
			model.addAttribute("stsession", stsessionRepository.findAll());
			model.addAttribute("errormessage", "Can't delete this data.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("student", studentService.getStudentByClass(schclass));
			return "dashboards/students/student";
		}
		}
		redirectAttributes.addAttribute("schclass", student.getSchclass());
		return "redirect:/dashboards/students/{schclass}";
	}

	
	
	// Edit a single Student from the list
	@RequestMapping("/edit-student")
	public String edit(Model model, @RequestParam Long id) {
		Student student = studentRepository.findById(id);
		Schclass schclass = student.getSchclass();

		model.addAttribute("student", studentRepository.findById(id));
		model.addAttribute("section", sectionRepository.findBySchclass(schclass));
		model.addAttribute("stsession", stsessionRepository.findByOrderByIdDesc());
		model.addAttribute("recent", studentRepository.findByOrderByIdDesc());
		model.addAttribute("Version", Version.values());
		model.addAttribute("Gender", Gender.values());
		model.addAttribute("Regularity", Regularity.values());
		model.addAttribute("Groups", Groups.values());
		model.addAttribute("Isactive", Isactive.values());
		model.addAttribute("schclass", schclass);
		return "dashboards/students/edit";
	}

	
	
	// Update a single Student data
	@PostMapping("/update-student")
	public String Update(Model model, @ModelAttribute Student student, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		Schclass schclass = student.getSchclass();
		// If there are some error...
		if (bindingResult.hasErrors()) {
			model.addAttribute("errorstudent", "Something wrong...");
			model.addAttribute("student", student);
			model.addAttribute("section", sectionRepository.findBySchclass(schclass));
			model.addAttribute("stsession", stsessionRepository.findByOrderByIdDesc());
			model.addAttribute("recent", studentRepository.findByOrderByIdDesc());
			model.addAttribute("Version", Version.values());
			model.addAttribute("Gender", Gender.values());
			model.addAttribute("Regularity", Regularity.values());
			model.addAttribute("Groups", Groups.values());
			model.addAttribute("Isactive", Isactive.values());
			model.addAttribute("schclass", schclass);
			return "dashboards/students/edit";
		}
		studentRepository.save(student);
		redirectAttributes.addAttribute("schclass", student.getSchclass());
		return "redirect:/dashboards/students/{schclass}";
	}
	
	
	////// STUDENT PHOTO PART//////
	//Upload Student Photo page
    @RequestMapping("/photoinsert/{stid}")
    public String studentphotoinsert(Model model, @PathVariable("stid") Long stid) {
    	
    	model.addAttribute("student", studentRepository.findById(stid));
    	model.addAttribute("studentPhoto", new StudentPhoto());
        return "dashboards/students/insertPhoto";
    }

    

    // Save Student Photo 
    @PostMapping("/save-studentphoto")
    public String studentphotosave(Model model,@Valid @ModelAttribute("studentPhoto") StudentPhoto studentPhoto
    		,BindingResult bindingResult
    		,@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes
    		) {
    	int size=(int) file.getSize();  	
    	Long stid=studentPhoto.getStudent().getId();
    	Student student= studentRepository.findById(stid);
    	
       if (file.isEmpty()) {
 			
 			model.addAttribute("message", "Please select a file and try again");
 			model.addAttribute("studentPhoto",studentPhoto);
 			model.addAttribute("student", student);
 	        return "dashboards/students/insertPhoto";
 	          	} 
       
        if(size>1000000){
        	model.addAttribute("errormessage", "Input file is more than 1MB");
        	model.addAttribute("message", "File is to large. please thy file below 1MB ");
        	model.addAttribute("student", student);
        	model.addAttribute("studentPhoto",studentPhoto);
        
         return "dashboards/students/insertPhoto";
  
                }
        
    if (bindingResult.hasErrors()) {			
 		       
 		        model.addAttribute("errormessage", "Something wrong...");
 		        model.addAttribute("studentPhoto",studentPhoto); 
 		       model.addAttribute("student", student);
 		        return "dashboards/students/insertPhoto";
       }	 
       
 		try {
 			// read and write the file to the selected location-
 			byte[] bytes = file.getBytes();
 			Path path = Paths.get(UPLOAD_FOLDER +studentPhoto.getPhototitle()+file.getOriginalFilename());
 			Files.write(path, bytes);
 			studentPhoto.setPicFile(studentPhoto.getPhototitle()+file.getOriginalFilename());
 			System.out.println("File size:"+file.getSize());

 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 				    	
 		        studentPhotoRepository.save(studentPhoto);
 		       redirectAttributes.addAttribute("stid", student.getId());
                return "redirect:/dashboards/students/view-student/{stid}";
    }
    
    
    
    
    // Delete Student Photo by id 
 	@RequestMapping("/delete-studentphoto") 
    public String studentphotodelete(Model model
    		,@RequestParam Long id, RedirectAttributes redirectAttributes) {
 		
 		StudentPhoto studentPhoto=studentPhotoRepository.findById(id); 
 		Long stid=studentPhoto.getStudent().getId();
 		Student student= studentRepository.findById(stid);
 		redirectAttributes.addAttribute("stid", student.getId());
 		
 		try { 
            Path path=Paths.get(UPLOAD_FOLDER+studentPhoto.getPicFile());
            Files.delete(path);
            System.out.println("Image Deleted !!!"); 
 		}catch(Exception e)
        {
            System.out.println("Failed to Delete image !!");            
        }		
 		     studentPhotoRepository.delete(id);  		
 		    		    
 		    return "redirect:/dashboards/students/view-student/{stid}";
    }
 	
 	
 	
    // Open edit Student Photo page
 	@RequestMapping("/edit-studentphoto/{stid}") 
 	public String edits(Model model, @PathVariable("stid") Long stid) {        
        model.addAttribute("studentphoto",studentPhotoRepository.findById(stid));        
        return "dashboards/students/editPhoto";
 	}
 	
 	
 	
 	
    // Update Student new Photo 
 	@PostMapping("/update-studentphoto") 
    public String studentphotoUpdate(Model model,@ModelAttribute StudentPhoto studentPhoto
    		, BindingResult bindingResult
    		,@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes
    		) {	
 		
 		int size=(int) file.getSize(); 
 		Long stid=studentPhoto.getStudent().getId();
    	Student student= studentRepository.findById(stid);
 		if (file.isEmpty()) {
 			studentPhoto.setPicFile(studentPhoto.getPicFile());			
 	      	}
 		else  if(size>1000000){
         	model.addAttribute("errormessage", "Input file is more than 1MB");
         	model.addAttribute("message", "File is to large. please thy file below 1MB ");
 			model.addAttribute("studentPhoto",studentPhoto); 
 		    return "dashboards/students/editPhoto";
   
                 }
 		 else {
 				try { 
 		            Path path=Paths.get(UPLOAD_FOLDER+studentPhoto.getPicFile());
 		            Files.delete(path);
 		            System.out.println("Image Deleted !!!");        
 				}catch(Exception e)
 		        {
 		            System.out.println("Failed to Delete image !!");       
 		           
 		        }				
 				try {
 					// read and write the file to the selected location-
 					byte[] bytes = file.getBytes();
 					Path path = Paths.get(UPLOAD_FOLDER +studentPhoto.getPhototitle()+file.getOriginalFilename());
 					Files.write(path, bytes);
 					studentPhoto.setPicFile(studentPhoto.getPhototitle()+file.getOriginalFilename());					

 				}  catch (IOException e)  {    
 		             e.printStackTrace();
 				         }		
 			    }

           //If there are some error...	
         if (bindingResult.hasErrors()) {	
 		        model.addAttribute("errormessage", "Something wrong...");		 
 		        model.addAttribute("studentPhoto",studentPhoto);  
 		        return "dashboards/students/editPhoto";
        }	         		    	
                  studentPhotoRepository.save(studentPhoto);
                  redirectAttributes.addAttribute("stid", student.getId());
                  return "redirect:/dashboards/students/view-student/{stid}";
    }

 	
     //////STUDENT DETAILS PART//////
	//Insert Student Details page
 	
 	
}

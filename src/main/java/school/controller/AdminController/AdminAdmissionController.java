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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import school.model.Student;
import school.model.enumvalue.Gender;
import school.model.enumvalue.Groups;
import school.model.enumvalue.Isactive;
import school.model.enumvalue.Regularity;
import school.model.enumvalue.Schclass;
import school.model.enumvalue.Version;
import school.repository.SectionRepository;
import school.repository.StsessionRepository;
import school.repository.StudentRepository;

@Controller
@RequestMapping("/adminadmission")
public class AdminAdmissionController {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	SectionRepository sectionRepository;	
	@Autowired
	StsessionRepository stsessionRepository;		
	// Open about us table
	@RequestMapping(value = { "/", "/index" })
	public String home(Model model) {
        return "dashboards/admission/index";
	}


	// Open admission Insert Form
	@RequestMapping(value = "/insert/{schclass}")
	public String insert(Model model, @PathVariable("schclass") Schclass schclass) {

    	model.addAttribute("section",sectionRepository.findBySchclass(schclass));  
    	model.addAttribute("stsession",stsessionRepository.findByOrderByIdDesc());  
    	model.addAttribute("recent",studentRepository.findByOrderByIdDesc()); 
		model.addAttribute("student", new Student());
		model.addAttribute("Version", Version.values());
		model.addAttribute("Gender", Gender.values());
		model.addAttribute("Regularity", Regularity.values());
		model.addAttribute("Groups", Groups.values());
		model.addAttribute("IsActive", Isactive.values());
		model.addAttribute("schclass", schclass);
		return "dashboards/admission/insert";
	}

	// Save about us Information
	@PostMapping("/save-student")
	public String save(Model model, @Valid @ModelAttribute("student") Student student, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		Schclass schclass=student.getSchclass();
		// If there are some error...
		if (bindingResult.hasErrors()) {

			    model.addAttribute("errorstudent", "Something wrong...");
		        model.addAttribute("student",student);
				model.addAttribute("section",sectionRepository.findBySchclass(schclass));  
		    	model.addAttribute("stsession",stsessionRepository.findByOrderByIdDesc());  
		    	model.addAttribute("recent",studentRepository.findByOrderByIdDesc()); 
		        model.addAttribute("Version", Version.values());
				model.addAttribute("Gender", Gender.values());
				model.addAttribute("Regularity", Regularity.values());
				model.addAttribute("Groups", Groups.values());
				model.addAttribute("IsActive", Isactive.values());
				model.addAttribute("schclass", schclass);
		        return "dashboards/admission/insert";
		}

		studentRepository.save(student);
		
		
		model.addAttribute("Success", "Student successfully admitted");
		model.addAttribute("section",sectionRepository.findBySchclass(schclass));  
    	model.addAttribute("stsession",stsessionRepository.findByOrderByIdDesc());  
    	model.addAttribute("recent",studentRepository.findByOrderByIdDesc()); 
		model.addAttribute("student", new Student());
		model.addAttribute("Version", Version.values());
		model.addAttribute("Gender", Gender.values());
		model.addAttribute("Regularity", Regularity.values());
		model.addAttribute("Groups", Groups.values());
		model.addAttribute("IsActive", Isactive.values());
		model.addAttribute("schclass", schclass);
		return "dashboards/admission/insert";
	}

}

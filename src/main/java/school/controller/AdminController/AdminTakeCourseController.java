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

import school.model.TakeCourse;
import school.model.enumvalue.Coursetype;
import school.model.enumvalue.Schclass;
import school.repository.StudentRepository;
import school.repository.TakeCourseRepository;
import school.services.CourseService;

@Controller
@RequestMapping("/admintakeCourse")
public class AdminTakeCourseController {

	@Autowired
	TakeCourseRepository takeCourseRepository;
	@Autowired
	CourseService courseService;
	@Autowired
	StudentRepository studentRepository;

	// Open about us table
	@RequestMapping(value = "/view-course/{id}")
	public String home(Model model, @PathVariable("id") long id) {
		model.addAttribute("takeCourse", takeCourseRepository.findByStudentId(id));
		model.addAttribute("student", studentRepository.findById(id));
		return "dashboards/takeCourses/index";
	}

	@RequestMapping(value = "/insert-course/{id}/{schclass}")
	public String insert(Model model, @PathVariable("id") long id, @PathVariable("schclass") Schclass schclass,
			TakeCourse takeCourse) {
		model.addAttribute("takeCourse", takeCourseRepository.findByStudentId(id));
		model.addAttribute("student", studentRepository.findById(id));
		model.addAttribute("subject", courseService.getCourseBySchclass(schclass));
		model.addAttribute("mandetorysubject", courseService.getCourseByClassAndType(schclass, Coursetype.Mandatory));
		model.addAttribute("optionalsubject", courseService.getCourseByClassAndType(schclass, Coursetype.Optional));
		model.addAttribute("fourthsubject", courseService.getCourseByClassAndType(schclass, Coursetype.Fourth));
		model.addAttribute("religioussubject", courseService.getCourseByClassAndType(schclass, Coursetype.Religious));
		return "dashboards/takeCourses/insert";
	}

	// Open takeCourse Insert Form
	// @RequestMapping("/insert")
	// public String insert(Model model) {
	// model.addAttribute("takeCourse",new TakeCourse());
	// model.addAttribute("Grade", Schclass.values());
	// model.addAttribute("Version", Version.values());
	// model.addAttribute("Gender", Gender.values());
	// return "dashboards/takeCourses/insert";
	// }
	//

	// Save about us Information
	@PostMapping("/save-takeCourse")
	public String save(Model model, @Valid @ModelAttribute("takeCourse") TakeCourse takeCourse,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		// If there are some error...
		if (bindingResult.hasErrors()) {

			model.addAttribute("errortakeCourse", "Something wrong...");
			model.addAttribute("takeCourse", takeCourse);
			return "dashboards/takeCourses/insert";
		}
		redirectAttributes.addAttribute("takecourseid", ((TakeCourse) takeCourse).getStudent().getId());
		takeCourseRepository.save(takeCourse);
		return "redirect:/admintakeCourse/view-course/{takecourseid}";
	}

	// Delete about us file by id
	@RequestMapping("/delete-takeCourse")
	public String delete(Model model, @RequestParam Long id) {

		takeCourseRepository.delete(id);
		return "redirect:/admintakeCourse/index";
	}

	// Delete about us file by id
	@RequestMapping("/remove-takeCourse/{id}")
	public String remove(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		TakeCourse takeCourse = takeCourseRepository.findById(id);
		takeCourseRepository.delete(id);
		redirectAttributes.addAttribute("id", takeCourse.getStudent().getId());
		return "redirect:/admintakeCourse/view-course/{id}";
	}

	@RequestMapping(value = "/edit-course/{id}/{stid}/{schclass}")
	public String edit(Model model, @PathVariable("id") long id, @PathVariable("stid") long stid,
			@PathVariable("schclass") Schclass schclass, TakeCourse takeCourse) {
		model.addAttribute("takeCourse", takeCourseRepository.findById(id));
		model.addAttribute("student", studentRepository.findById(stid));
		model.addAttribute("course", courseService.getCourseBySchclass(schclass));
		model.addAttribute("mandetorysubject", courseService.getCourseByClassAndType(schclass, Coursetype.Mandatory));
		model.addAttribute("optionalsubject", courseService.getCourseByClassAndType(schclass, Coursetype.Optional));
		model.addAttribute("fourthsubject", courseService.getCourseByClassAndType(schclass, Coursetype.Fourth));
		model.addAttribute("religioussubject", courseService.getCourseByClassAndType(schclass, Coursetype.Religious));
		return "dashboards/takeCourses/edit";
	}

	// @RequestMapping("/edit/{id}")
	// public String edssit(Model model, @PathVariable Long id, Role role) {
	// model.addAttribute("role", roleRepository.findById(id));
	//
	// model.addAttribute("list", roleRepository.findAll());
	//
	// model.addAttribute("privilegelist", privilegeRepository.findAll());
	//
	// return "user/role";
	//
	@PostMapping("/update-takeCourse")
	public String Update(Model model, @ModelAttribute TakeCourse takeCourse, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		// If there are some error...
		if (bindingResult.hasErrors()) {
			model.addAttribute("errortakeCourse", "Something wrong...");
			model.addAttribute("takeCourse", takeCourse);
			return "dashboards/takeCourses/edit";
		}
		redirectAttributes.addAttribute("takecourseid", ((TakeCourse) takeCourse).getStudent().getId());
		takeCourseRepository.save(takeCourse);
		return "redirect:/admintakeCourse/view-course/{takecourseid}";
	}

}

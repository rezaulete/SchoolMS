package school.controller.AdminController;

import java.util.ArrayList;
import java.util.List;
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

import school.model.Exam;
import school.model.Marks;
import school.model.Marksheet;
import school.model.Student;
import school.model.enumvalue.Gender;
import school.model.enumvalue.Schclass;
import school.model.enumvalue.Version;
import school.repository.ExamRepository;
import school.repository.MarksRepository;
import school.repository.MarksheetRepository;
import school.repository.StudentRepository;
import school.repository.TakeCourseRepository;
import school.services.StudentService;

@Controller
@RequestMapping("/adminmarks")
public class AdminMarksController {

	@Autowired
	MarksRepository marksRepository;
	@Autowired
	MarksheetRepository marksheetRepository;
	@Autowired
	StudentService studentService;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	TakeCourseRepository takeCourseRepository;
	@Autowired
	ExamRepository examRepository;

	// Open about us table
	@RequestMapping(value = { "/", "/index" })
	public String home(Model model) {
		return "dashboards/marks/index";
	}

	@RequestMapping(value = "/student/{schclass}")
	public String student(Model model, @PathVariable("schclass") Schclass schclass) {
		model.addAttribute("student", studentService.getStudentByClass(schclass));
		model.addAttribute("schclass", schclass);
		return "dashboards/marks/students";
	}

	@RequestMapping(value = "/add-exam/{id}")
	public String addexam(Model model, @PathVariable("id") long id) {
		model.addAttribute("takeCourse", takeCourseRepository.findByStudentId(id));
		model.addAttribute("student", studentRepository.findById(id));
		model.addAttribute("exam", examRepository.findAll());
		model.addAttribute("marks", new Marks());
		return "dashboards/marks/exam";
	}

	@RequestMapping(value = "/add-marks/{id}")
	public String addmarks(Model model, @PathVariable("id") long id, @Valid @ModelAttribute("exam") Exam exam) {
		model.addAttribute("takeCourse", takeCourseRepository.findByStudentId(id));
		model.addAttribute("student", studentRepository.findById(id));
		model.addAttribute("exam", exam);
		model.addAttribute("marks", new Marks());
		model.addAttribute("marksheet", new Marksheet());
		return "dashboards/marks/addmarks";
	}

	@RequestMapping(value = { "/studentsmarks" })
	public String studentsmarks(Model model) {
		model.addAttribute("student", studentRepository.findAll());
		return "dashboards/marks/index";
	}

	@RequestMapping("/view-marks/{stid}/{examid}")
	public String view(Model model, @PathVariable("stid") long stid, @PathVariable("examid") long examid) {
		Student student = studentRepository.findById(stid);
		Exam exam = examRepository.findById(examid);
		model.addAttribute("marks", marksRepository.findByStudentAndExam(student, exam));
		return "dashboards/marks/view";
	}

	// // Open marks Insert Form
	// @RequestMapping("/insert")
	// public String insert(Model model) {
	// model.addAttribute("marks",new Marks());
	// model.addAttribute("Grade", Schclass.values());
	// model.addAttribute("Version", Version.values());
	// model.addAttribute("Gender", Gender.values());
	// return "dashboards/marks/insert";
	// }

	// Save about us Information
	@PostMapping("/save-marks/{stid}/{examid}/{takeCourseid}")
	public String save(Model model
			, @PathVariable("stid") long stid
			, @PathVariable("examid") long examid
			, @PathVariable("takeCourseid") long takeCourseid
			, @Valid @ModelAttribute("marks") Marks marks,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		// If there are some error...
		if (bindingResult.hasErrors()) {

			model.addAttribute("errormarks", "Something wrong...");
			model.addAttribute("marks", new Marks());
			model.addAttribute("marksheet", new Marksheet());
			return "dashboards/marks/addmarks";
		}
//		TakeCourse takeCourse=takeCourseRepository.findById(takeCourseid);
//		Set<Course> course = takeCourse.getCourse();
	    List<Marks> mark = new ArrayList<Marks>();

	 
	    for (Marks mar : mark) {
	    	marksRepository.save(mar);
	    }
	    
//		for (int i = 0; i < course.size(); i++) {
//          marksRepository.save(marks);
//		}
		
		redirectAttributes.addAttribute("stid", stid);
		redirectAttributes.addAttribute("examid", examid);
		return "redirect:/adminmarks/view-marks/{stid}/{examid}";

	}

	// Delete about us file by id
	@RequestMapping("/delete-marks")
	public String delete(Model model, @RequestParam Long id) {
		marksRepository.delete(id);
		return "redirect:/adminmarks/index";
	}

	@RequestMapping("/edit-marks")
	public String edit(Model model, @RequestParam Long id) {
		model.addAttribute("marks", marksRepository.findById(id));
		model.addAttribute("Grade", Schclass.values());
		model.addAttribute("Version", Version.values());
		model.addAttribute("Gender", Gender.values());
		return "dashboards/marks/edit";
	}

	@PostMapping("/update-marks")
	public String Update(Model model, @ModelAttribute Marks marks, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		// If there are some error...
		if (bindingResult.hasErrors()) {
			model.addAttribute("errormarks", "Something wrong...");
			model.addAttribute("marks", marks);
			return "dashboards/markss/edit";
		}
		marksRepository.save(marks);
		return "redirect:/adminmarks/index";
	}

}

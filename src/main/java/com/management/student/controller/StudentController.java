package com.management.student.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.management.student.entity.Student;
import com.management.student.service.StudentService;

@Controller
public class StudentController {
    @Autowired
	private StudentService service;
	
	@GetMapping("/")
	public String Home(Model model) {
//		List<Student> list=service.getAllStudents();
//		model.addAttribute("list",list);
//		return "index";
		return findPagination(0, model);
	}
	
	@GetMapping("/add_student")
	public String addStudent() {
		return "addStudent";
	}
	
	@PostMapping("/add")
	public String studentAdd(@ModelAttribute Student student,HttpSession session){
		service.addStud(student);
		session.setAttribute("msg","Student inserted successfully !");
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String eidt(@PathVariable int id,Model model) {
		Student student=service.getStudentById(id);
		model.addAttribute("student",student);
		return "edit";
	}
	
	@PostMapping("/update")
	public String updateStudent(@ModelAttribute Student student,HttpSession session) {
		service.addStud(student);
		session.setAttribute("msg","Student Udated Successfully");
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable int id,HttpSession session) {
	 service.deleteStud(id);
	 session.setAttribute("msg","Student deleted Successfully");
	 return "redirect:/";
	}
	
	@GetMapping("/page/{pageno}")
	public String findPagination(@PathVariable int pageno,Model model) {
		Page<Student> stulist=service.getStudentByPagination(pageno,3);
		model.addAttribute("list",stulist);
		model.addAttribute("currentpage",pageno);
		model.addAttribute("totalpages",stulist.getTotalPages());
		model.addAttribute("totalitems",stulist.getTotalElements());
		return "index";
	}
}

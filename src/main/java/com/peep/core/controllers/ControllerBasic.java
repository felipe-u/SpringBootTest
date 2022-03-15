package com.peep.core.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peep.core.configuration.Paginas;
import com.peep.core.model.Post;
import com.peep.core.model.Student;

@Controller
@RequestMapping("/home")
public class ControllerBasic {
	
	public List<Post> getPosts() {
		ArrayList<Post> posts = new ArrayList<>();
		
		posts.add(new Post(1, "Desarrollo web", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque aliquam tortor ut turpis facilisis, pharetra sollicitudin metus pharetra", "http://localhost/img/post.jpg", new Date()));
		posts.add(new Post(2, "Desarrollo móvil", "Descripcion hipotética de desarrollo móvil - Android, Kotlin...", "http://localhost/img/post.jpg", new Date()));
		posts.add(new Post(3, "Bases de datos", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque aliquam tortor ut turpis facilisis, pharetra sollicitudin metus pharetra", "http://localhost/img/post.jpg", new Date()));
		posts.add(new Post(4, "Desarrollo de videojuegos", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque aliquam tortor ut turpis facilisis, pharetra sollicitudin metus pharetra", "http://localhost/img/post.jpg", new Date()));
		
		return posts;
	}
	
	
	@GetMapping(path = {"/posts","/"})
	public String saludar(Model model) {
		model.addAttribute("posts", this.getPosts());
		return "index";
	}
	
	public List<Student> getStudents() {
		ArrayList<Student> students = new ArrayList<>();
		students.add(new Student(1, "Juan"));
		students.add(new Student(2, "Pedro"));
		return students;
	}
	
	@GetMapping(path = {"/listStudents"})
	public String listStudent(Model model) {
		model.addAttribute("students", this.getStudents());
		return "listStudents";
	}
	
	/*@GetMapping(path = "/public")
	public ModelAndView post() {
		ModelAndView modelAndView = new ModelAndView(Paginas.HOME);
		modelAndView.addObject("posts", this.getPosts());
		return modelAndView;
	}*/
	
	@GetMapping(path = {"/post"})
	public ModelAndView getPostIndividual(
			@RequestParam(defaultValue = "1", name = "id", required = false) int id
			) {
		ModelAndView modelAndView =  new ModelAndView(Paginas.POST);
		
		List<Post> postFiltrado = this.getPosts().stream()
									.filter( (p) -> {
										return p.getId() == id;
									} ).collect(Collectors.toList());
		modelAndView.addObject("post", postFiltrado.get(0));
		return modelAndView;
	}
	
}

package mx.edu.unpa.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.edu.unpa.entity.Alumno;
import mx.edu.unpa.repository.AlumnoRepository;
import mx.edu.unpa.service.AlumnoService;

// En el controller se definen las rutas a las que llama ó hace 'petición' el usuario y de aquí se llama a la capa de service
@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private AlumnoRepository alumnoRepository; // Implementa la bd
	@Autowired
	private AlumnoService alumnoService; //Consume la interfaz
	
	@GetMapping("lst")
	public @ResponseBody List<Alumno> list() {
		return alumnoRepository.findAll();
	}
	
	@GetMapping("/app")
	public String list(Model model) {
		Iterable<Alumno> alumnos = alumnoRepository.findAll();

		model.addAttribute("alumnos", alumnos);
		return "alumno/app.html";
	}

	// Read an user
	@GetMapping("/fnd")
	public String read(Alumno alumno, Model model) {
		String url = "";

		if (alumno.getId() != 0) {
			LinkedList<Alumno> alumnos = new LinkedList<Alumno>();
			try {
				Optional<Alumno> oUser = alumnoService.findById(alumno.getId());
				alumnos.add(oUser.get());
				model.addAttribute("alumnos", alumnos);
				url = "alumno/qry.html";
			} catch (Exception e) {
				url = "redirect:/";
			}

		} else {
			url = "redirect:/app";
		}

		return url;
	}
		
	// Create a new user
	@GetMapping("/add")
	public String add(Alumno alumno) {
		return "alumno/add.html";
	}

	@PostMapping("/create")
	public String create(Alumno alumno) {
		alumnoService.save(alumno);
		return "redirect:/app";
	}

	// Update an user
	@GetMapping("/upd/{id}")
	// public String upd(@PathVariable(value = "id") Integer personId, Model model)
	// {
	public String upd(Alumno alumno, Model model) {
		Optional<Alumno> oAlumno = alumnoService.findById(alumno.getId());

		model.addAttribute("alumno", oAlumno);
		return "alumno/upd.html";
	}

	@PostMapping("/save")
	public String save(Alumno alumno) {
		if (alumno != null) {
			alumnoService.save(alumno);
		}

		return "redirect:/app";
	}

	// Delete an User
	@GetMapping("/del/{id}")
	public String delete(@PathVariable(value = "id") Integer alumnoId) {
		alumnoService.deleteById(alumnoId);

		return "redirect:/app";
	}
}

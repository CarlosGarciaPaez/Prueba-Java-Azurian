package mx.edu.unpa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mx.edu.unpa.repository.AlumnoRepository;
import mx.edu.unpa.entity.Alumno;
import mx.edu.unpa.service.AlumnoService;


@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	/*@Autowired
	private AlumnoRepository alumnoRepository; // Implementa la bd*/
	@Autowired
	private AlumnoService alumnoService; //Consume la interfaz

	// Read all Students
	@GetMapping("/app")
	public @ResponseBody Iterable<Alumno> list() {
		Iterable<Alumno> alumnos = alumnoService.findAll();

		return alumnos;
	}

	// Read all Students
	@GetMapping("/all")
	public @ResponseBody Iterable<Alumno> read() {
		Iterable<Alumno> alumnos = alumnoService.findAll();

		return alumnos;
	}

	// Read an Student
	@GetMapping("/{id}")
	public @ResponseBody Optional<Alumno> read(@PathVariable(value = "id") Integer alumnoId) {
		Optional<Alumno> oAlumno = alumnoService.findById(alumnoId);

		return oAlumno;
	}

	// Create a new Student
	@PostMapping("/create")
	public @ResponseBody Alumno create(@RequestBody Alumno alumno) {			
		return alumnoService.save(alumno);
	}

	// Update an Student
	@PutMapping("/{id}")
	public @ResponseBody Alumno update(@RequestBody Alumno alumno, @PathVariable(value = "id") Integer alumnoId) {
		return alumnoService.save(alumno);
	}

	// Delete an Student
	@DeleteMapping("/del/{id}")
	public @ResponseBody void delete(@PathVariable(value = "id") Integer alumnoId) {
		alumnoService.deleteById(alumnoId);

	}


}

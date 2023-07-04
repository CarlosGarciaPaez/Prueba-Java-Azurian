package mx.edu.unpa.service;

import org.springframework.data.domain.Pageable;
import java.util.Optional;
import org.springframework.data.domain.Page;
import mx.edu.unpa.entity.Alumno;

public interface AlumnoService {
	public Iterable<Alumno> findAll();
	public Page<Alumno> findAll(Pageable pageable);
	public Optional<Alumno> findById(Integer id);
	public Alumno save(Alumno alumno);
	public void deleteById(Integer id);
}

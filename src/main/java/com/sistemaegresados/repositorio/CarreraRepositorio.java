package com.sistemaegresados.repositorio;

import com.sistemaegresados.entidades.Carrera;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarreraRepositorio extends JpaRepository<Carrera, Long>{
    
    public Optional<Carrera> findByEspecialidad(String espacialidad);
    public List<Carrera> findAllByNombreCarrera(String nombreCarrera);
}

package com.sistemaegresados.repositorio;

import com.sistemaegresados.entidades.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EstudianteRepositorio extends JpaRepository<Estudiante, Long> {
    
}

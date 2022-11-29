package com.sistemaegresados.repositorio;

import com.sistemaegresados.entidades.Estudiante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface EstudianteRepositorio extends JpaRepository<Estudiante, Long> {
    
}

package com.sistemaegresados.repositorio;

import com.sistemaegresados.entidades.DatosPersonales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatosPersonalesRepositorio extends JpaRepository<DatosPersonales, Long> {
    
}

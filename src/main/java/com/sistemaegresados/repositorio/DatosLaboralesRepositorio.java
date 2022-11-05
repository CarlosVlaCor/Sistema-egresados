package com.sistemaegresados.repositorio;

import com.sistemaegresados.entidades.DatosLaborales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatosLaboralesRepositorio extends JpaRepository<DatosLaborales, Long>{
    
}

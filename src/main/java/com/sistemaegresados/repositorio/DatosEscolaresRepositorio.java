package com.sistemaegresados.repositorio;

import com.sistemaegresados.entidades.DatosEscolares;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatosEscolaresRepositorio extends JpaRepository<DatosEscolares, Long>{
    
}

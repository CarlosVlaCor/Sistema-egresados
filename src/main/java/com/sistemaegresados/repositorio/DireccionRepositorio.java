package com.sistemaegresados.repositorio;

import com.sistemaegresados.entidades.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionRepositorio extends JpaRepository<Direccion, Long>{
    
}

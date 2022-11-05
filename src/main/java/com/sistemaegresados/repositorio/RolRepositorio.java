package com.sistemaegresados.repositorio;

import com.sistemaegresados.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepositorio extends JpaRepository<Rol, Long>{
    
}

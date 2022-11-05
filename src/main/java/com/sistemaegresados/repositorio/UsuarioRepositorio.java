package com.sistemaegresados.repositorio;

import com.sistemaegresados.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
    
    public Usuario findByNroControl(String nroControl);
}

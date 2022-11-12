package com.sistemaegresados.repositorio;

import com.sistemaegresados.entidades.Estudiante;
import com.sistemaegresados.entidades.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
    
    public Usuario findByNroControlOrCurp(String nroControl, String curp);
    
    public Optional<Usuario> findByNroControl(String nroControl);
}

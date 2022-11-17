package com.sistemaegresados.repositorio;

import com.sistemaegresados.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepositorio extends JpaRepository<Comentario, Long>{
    
}

package com.sistemaegresados.repositorio;

import com.sistemaegresados.entidades.DatosEscolares;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DatosEscolaresRepositorio extends JpaRepository<DatosEscolares, Long>{
 
    @Query("FROM DatosEscolares d join d.carrera carrera WHERE carrera.nombreCarrera = :carrera")
    public List<DatosEscolares> findAllByCarrera(@Param("carrera") String carrera);
}

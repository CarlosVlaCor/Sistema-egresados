package com.sistemaegresados.entidades;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "carreras")
@Getter
@Setter
public class Carrera {
    @Id
    private Long id;
    private String nombreCarrera;
    private String especialidad;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "carrera")
    private List<DatosEscolares> datosEscolareses;
    public Carrera(){
        
    }
    
}

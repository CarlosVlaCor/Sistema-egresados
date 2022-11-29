package com.sistemaegresados.entidades;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "datos_escolares")
@Getter
@Setter

public class DatosEscolares {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Este campo no debe estar vacío")
    private Integer anioIngreso;
    @NotNull(message = "Este campo no debe estar vacío")
    private Integer anioEgreso;
    @NotNull(message = "Este campo no debe estar vacío")
    private Double promedioFinal;
    private int titulo;
    private int postGrado;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_carrera", referencedColumnName = "id")
    @Valid
    private Carrera carrera;
    @OneToOne(mappedBy = "datosEscolares", fetch = FetchType.EAGER)
    private Estudiante estudiante;
    public DatosEscolares(){
        
    }

    @Override
    public String toString() {
        return "DatosEscolares{" + "id=" + id + ", anioIngreso=" + anioIngreso + ", anioEgreso=" + anioEgreso + ", promedioFinal=" + promedioFinal +'}';
    }
    
    
}

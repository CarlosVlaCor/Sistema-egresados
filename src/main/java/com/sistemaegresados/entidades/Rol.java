package com.sistemaegresados.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Rol {
    @Id
    private Long id;
    private String nombre;

    public Rol() {
    }
    
}

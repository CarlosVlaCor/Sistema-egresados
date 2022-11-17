package com.sistemaegresados.dto;

import com.sistemaegresados.entidades.Comentario;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RespuestaComentario {

    public static final int TAMANO_PAGINA = 5;
    private int nroPagina;
    private long totalElementos;
    private boolean ultima;
    private int idCarrera;
    private int totalPaginas;
    private boolean primerPagina;
    private boolean vacio;
    private List<Comentario> contenido;
    private int seccionActual;
    private int limiteActual;
    private int inicioActual;
    
    public void moverSeccionActual() {
        int div = totalPaginas / 5;
        System.out.println(totalPaginas);
        int seccion;
        int limite =4;
        int inicio=0;
        boolean encontrado = false;
        System.out.println(nroPagina);
        for (seccion = 1; seccion <= div; seccion++) {
            for (; inicio <= limite; inicio++) {
                if(inicio == nroPagina){
                    seccionActual = seccion;
                    inicioActual = limite-4;
                    limiteActual = limite;
                    encontrado = true;
                    break;
                } 
            }
            if(encontrado == true){
                break;
            }
            limite = limite + 5;
            System.out.println("inicio " + inicio);
            System.out.println("limite " +limite);
        }
        

    }
}

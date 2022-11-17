package com.sistemaegresados.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorPrincipal {
    
    @GetMapping("/")
    public String inicio( ){
        //System.out.println(user.getUsername());
        return "index.html";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
  
    
}

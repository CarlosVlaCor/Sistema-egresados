package com.sistemaegresados.seguridad;

import com.sistemaegresados.entidades.Usuario;
import com.sistemaegresados.repositorio.UsuarioRepositorio;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String nroControl) throws UsernameNotFoundException {
        Usuario usuario =  usuarioRepositorio.findByNroControl(nroControl);
        if(usuario == null){
            throw new UsernameNotFoundException("No se encontró el usuario");
        }
        
        GrantedAuthority ga = new SimpleGrantedAuthority(usuario.getRol().getNombre());
        return new User(usuario.getNroControl(), usuario.getPassword(), Collections.singleton(ga));
    }
    
}

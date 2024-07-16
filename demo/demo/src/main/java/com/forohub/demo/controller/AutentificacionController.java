package com.forohub.demo.controller;

import com.forohub.demo.domain.usuario.DatosAutentificacionUsuario;
import com.forohub.demo.domain.usuario.Usuario;
import com.forohub.demo.infra.security.DatosJWTtoken;
import com.forohub.demo.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutentificacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarusuario(@RequestBody @Valid
                                                DatosAutentificacionUsuario datosAutentificacionUsuario){
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datosAutentificacionUsuario.login()
                ,datosAutentificacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
    }

}
package com.frontend.crud_usuario.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.frontend.crud_usuario.model.Usuario;


@Controller
public class UsuarioController {
    
    @GetMapping("/retornarUsuarios")
    public ModelAndView getAllUsuarios() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/api/usuarios";
        Usuario[] usuarios = restTemplate.getForObject(url, Usuario[].class);

        ModelAndView mv = new ModelAndView("listar");
        mv.addObject("usuarios", Arrays.asList(usuarios));
        return mv;
    }
}

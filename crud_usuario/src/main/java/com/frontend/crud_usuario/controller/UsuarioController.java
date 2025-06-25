package com.frontend.crud_usuario.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.frontend.crud_usuario.model.Usuario;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



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

    @GetMapping("/retornarUsuarios/novo")
    public ModelAndView mostrarFormularioCadastro(){
        ModelAndView mv = new ModelAndView("cadastrar");
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @PostMapping("/novoUsuario")
    public String cadastrarUsuario(@ModelAttribute Usuario usuario) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/api/usuarios";

        restTemplate.postForEntity(url, usuario, Void.class);
        
        return "redirect:/retornarUsuarios";
    }
    
    @GetMapping("/retornarUsuarios/editar/{id}")
    public ModelAndView mostrarFormularioEdicao(@PathVariable Integer id){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/api/usuarios/" + id;

        Usuario usuario = restTemplate.getForObject(url, Usuario.class);

        ModelAndView mv = new ModelAndView("editar");
        mv.addObject("usuario", usuario);
        return mv;
    }

    @PostMapping("/editarUsuario")
    public String editarUsuario(@ModelAttribute Usuario usuario) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/api/usuarios/" + usuario.getId();

        restTemplate.put(url, usuario);

        return "redirect:/retornarUsuarios";
    }
    
    @GetMapping("/retornarUsuarios/deletar/{id}")
    public String deletarUsuario(@PathVariable Integer id){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/api/usuarios/" + id;

        restTemplate.delete(url);

        return "redirect:/retornarUsuarios";
    }

}

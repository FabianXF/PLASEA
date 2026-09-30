package edu.plasea.parcial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/inicio")
    public String mostrarInicio() {
        return "inicio"; // Retorna la vista inicio.html
    }
}

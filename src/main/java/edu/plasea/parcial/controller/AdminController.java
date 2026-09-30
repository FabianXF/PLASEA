package edu.plasea.parcial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/panel")
    public String showAdminPanel() {
        return "formulario-administrador"; 
    }
}

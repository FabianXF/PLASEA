package edu.plasea.parcial.controller;

import edu.plasea.parcial.model.Carrera;
import edu.plasea.parcial.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FormularioControlador {

    @Autowired
    private CarreraRepository carreraRepository;

    @GetMapping("/formulario-reportes")
    public String mostrarFormularioReportes(Model model) {
        // Pre-cargar las carreras (opcional, si no quieres depender solo de AJAX)
        List<Carrera> carreras = carreraRepository.findAll();
        model.addAttribute("carreras", carreras);
        return "formulario-reportes";
    }

    @GetMapping("/formulario-auditor")
    public String mostrarFormularioAuditor() {
        return "formulario-auditor";
    }
}

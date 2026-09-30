package edu.plasea.parcial.controller;

import edu.plasea.parcial.dto.*;
import edu.plasea.parcial.model.*;
import edu.plasea.parcial.repository.UsuarioRepository;
import edu.plasea.parcial.service.ReporteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/reporte")
public class ReporteController {

    private static final Logger usuariologger = LoggerFactory.getLogger(ReporteController.class);

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("reporte", new Reporte());
        return "formulario-reportes";
    }

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarReporte(
            @RequestParam String cedula,
            @RequestParam Long carrera,
            @RequestParam Long asignatura,
            @RequestParam Long curso,
            @RequestParam Long idEstudiante,
            @RequestParam String motivo,
            @RequestParam String fecha,
            @RequestParam BigDecimal calificacion,
            @RequestParam(required = false) String comentario) {
        try {
            Long idUsuario;
            try {
                idUsuario = Long.valueOf(cedula);
            } catch (NumberFormatException e) {
                usuariologger.error("Cédula inválida: {}", cedula, e);
                return ResponseEntity.badRequest().body("La cédula debe ser un número válido.");
            }

            Usuario usuario = usuarioRepository.findByIdUsuario(idUsuario);
            if (usuario == null) {
                usuariologger.warn("No se encontró un usuario con la cédula: {}", cedula);
                return ResponseEntity.badRequest().body("No se encontró un usuario con la cédula proporcionada.");
            }
            if (usuario.getRol().getIdRol() != 1) {
                usuariologger.warn("El usuario con cédula {} no es un profesor", cedula);
                return ResponseEntity.badRequest().body("El usuario no es un profesor.");
            }

            LocalDate fechaParsed = LocalDate.parse(fecha);
            Reporte reporte = reporteService.guardarReporte(
                    cedula, carrera, asignatura, curso, idEstudiante, motivo, fechaParsed, calificacion, comentario);
            return ResponseEntity.ok("Reporte guardado exitosamente con ID: " + reporte.getIdReporte());
        } catch (Exception e) {
            usuariologger.error("Error al guardar el reporte: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("Error al guardar el reporte: " + e.getMessage());
        }
    }

    @GetMapping("/buscar-carreras")
    @Transactional
    public ResponseEntity<?> buscarCarreras(@RequestParam String cedula) {
        try {
            usuariologger.info("Buscando carreras para la cédula: {}", cedula);
            Long idUsuario = Long.valueOf(cedula);
            usuariologger.debug("ID de usuario convertido: {}", idUsuario);
            Usuario usuario = usuarioRepository.findByIdUsuario(idUsuario);
            if (usuario == null) {
                usuariologger.warn("No se encontró un usuario con la cédula: {}", cedula);
                return ResponseEntity.badRequest().body("No se encontró un usuario con la cédula proporcionada.");
            }
            if (usuario.getRol().getIdRol() != 1) {
                usuariologger.warn("El usuario con cédula {} no es un profesor", cedula);
                return ResponseEntity.badRequest().body("El usuario no es un profesor.");
            }
            List<CarreraDTO> carreras = reporteService.buscarCarrerasPorProfesor(cedula);
            usuariologger.debug("Carreras encontradas: {}", carreras);
            if (carreras.isEmpty()) {
                usuariologger.info("No se encontraron carreras para la cédula: {}", cedula);
                return ResponseEntity.status(404).body("No se encontraron carreras asociadas a este profesor.");
            }
            return ResponseEntity.ok(carreras);
        } catch (NumberFormatException e) {
            usuariologger.error("Error al convertir la cédula a número: {}", cedula, e);
            return ResponseEntity.badRequest().body("La cédula debe ser un número válido.");
        } catch (Exception e) {
            usuariologger.error("Error interno al buscar carreras para la cédula: {}", cedula, e);
            return ResponseEntity.status(500).body("Error interno del servidor: " + e.getMessage());
        }
    }

    @GetMapping("/buscar-asignaturas")
    @Transactional
    public ResponseEntity<?> buscarAsignaturas(
            @RequestParam String cedula,
            @RequestParam Long idCarrera) {
        try {
            List<AsignaturaDTO> asignaturas = reporteService.buscarAsignaturasPorProfesorYCarrera(cedula, idCarrera);
            if (asignaturas.isEmpty()) {
                return ResponseEntity.status(404).body("No se encontraron asignaturas para esta carrera y profesor.");
            }
            return ResponseEntity.ok(asignaturas);
        } catch (Exception e) {
            usuariologger.error("Error al buscar asignaturas para cédula {} y carrera {}: {}", cedula, idCarrera, e.getMessage(), e);
            return ResponseEntity.status(500).body("Error interno del servidor: " + e.getMessage());
        }
    }

    @GetMapping("/buscar-cursos")
    @Transactional
    public ResponseEntity<?> buscarCursos(
            @RequestParam String cedula,
            @RequestParam Long idCarrera,
            @RequestParam Long idAsignatura) {
        try {
            usuariologger.info("Buscando cursos para cédula: {}, carrera: {}, asignatura: {}", cedula, idCarrera, idAsignatura);
            List<CursoDTO> cursos = reporteService.buscarCursosPorProfesorCarreraYAsignatura(cedula, idCarrera, idAsignatura);
            if (cursos.isEmpty()) {
                usuariologger.info("No se encontraron cursos para cédula: {}, carrera: {}, asignatura: {}", cedula, idCarrera, idAsignatura);
                return ResponseEntity.status(404).body("No se encontraron cursos para esta asignatura, carrera y profesor.");
            }
            usuariologger.debug("Cursos encontrados: {}", cursos);
            return ResponseEntity.ok(cursos);
        } catch (Exception e) {
            usuariologger.error("Error al buscar cursos para cédula: {}, carrera: {}, asignatura: {}: {}", 
                               cedula, idCarrera, idAsignatura, e.getMessage(), e);
            return ResponseEntity.status(500).body("Error interno del servidor: " + e.getMessage());
        }
    }

    @GetMapping("/buscar-alumnos")
    @Transactional
    public ResponseEntity<?> buscarAlumnos(@RequestParam Long idCurso) {
        try {
            usuariologger.info("Buscando estudiantes para el curso con ID: {}", idCurso);
            List<EstudianteDTO> estudiantes = reporteService.buscarEstudiantesPorCurso(idCurso);
            if (estudiantes.isEmpty()) {
                usuariologger.info("No se encontraron estudiantes para el curso con ID: {}", idCurso);
                return ResponseEntity.status(404).body("No se encontraron estudiantes para este curso.");
            }
            usuariologger.debug("Estudiantes encontrados: {}", estudiantes);
            return ResponseEntity.ok(estudiantes);
        } catch (Exception e) {
            usuariologger.error("Error al buscar estudiantes para el curso con ID {}: {}", idCurso, e.getMessage(), e);
            return ResponseEntity.status(500).body("Error interno del servidor: " + e.getMessage());
        }
    }

    @GetMapping("/general")
    @Transactional
    public ResponseEntity<List<ReporteGeneralDTO>> obtenerReporteGeneral() {
        try {
            List<ReporteGeneralDTO> reportes = reporteService.obtenerReporteGeneral();
            if (reportes.isEmpty()) {
                return ResponseEntity.status(404).body(reportes);
            }
            return ResponseEntity.ok(reportes);
        } catch (Exception e) {
            usuariologger.error("Error al obtener reporte general: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/carreras")
    @Transactional
    public ResponseEntity<List<CarreraReporteDTO>> obtenerReporteCarreras() {
        try {
            List<CarreraReporteDTO> reportes = reporteService.obtenerReporteCarreras();
            if (reportes.isEmpty()) {
                return ResponseEntity.status(404).body(reportes);
            }
            return ResponseEntity.ok(reportes);
        } catch (Exception e) {
            usuariologger.error("Error al obtener reporte de carreras: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/asignaturas")
    @Transactional
    public ResponseEntity<List<AsignaturaReporteDTO>> obtenerReporteAsignaturas() {
        try {
            List<AsignaturaReporteDTO> reportes = reporteService.obtenerReporteAsignaturas();
            if (reportes.isEmpty()) {
                return ResponseEntity.status(404).body(reportes);
            }
            return ResponseEntity.ok(reportes);
        } catch (Exception e) {
            usuariologger.error("Error al obtener reporte de asignaturas: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/cursos")
    @Transactional
    public ResponseEntity<List<CursoReporteDTO>> obtenerReporteCursos() {
        try {
            List<CursoReporteDTO> reportes = reporteService.obtenerReporteCursos();
            if (reportes.isEmpty()) {
                return ResponseEntity.status(404).body(reportes);
            }
            return ResponseEntity.ok(reportes);
        } catch (Exception e) {
            usuariologger.error("Error al obtener reporte de cursos: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/usuario/{id}")
    @Transactional
    public ResponseEntity<List<ReporteGeneralDTO>> obtenerReportesPorUsuario(@PathVariable Long id) {
        try {
            List<ReporteGeneralDTO> reportes = reporteService.obtenerReportesPorUsuario(id);
            if (reportes.isEmpty()) {
                return ResponseEntity.status(404).body(reportes);
            }
            return ResponseEntity.ok(reportes);
        } catch (Exception e) {
            usuariologger.error("Error al obtener reportes por usuario con ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    @Transactional
    public ResponseEntity<String> eliminarReporte(@PathVariable Long id) {
        try {
            reporteService.eliminarReporte(id);
            return ResponseEntity.ok("Reporte eliminado con éxito");
        } catch (Exception e) {
            usuariologger.error("Error al eliminar el reporte con ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(500).body("Error al eliminar el reporte: " + e.getMessage());
        }
    }

    @GetMapping("/editar/{id}")
    @Transactional
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        try {
            Reporte reporte = reporteService.obtenerReportePorId(id);
            model.addAttribute("reporte", reporte);
            return "editar-reporte";
        } catch (Exception e) {
            usuariologger.error("Error al cargar el reporte con ID {} para edición: {}", id, e.getMessage(), e);
            return "redirect:/admin/panel";
        }
    }

    @PostMapping("/actualizar/{id}")
    @Transactional
    public String actualizarReporte(
            @PathVariable Long id,
            @RequestParam String cedula,
            @RequestParam Long idCarrera,
            @RequestParam Long idAsignatura,
            @RequestParam Long idCurso,
            @RequestParam Long idEstudiante,
            @RequestParam String motivo,
            @RequestParam String fecha,
            @RequestParam BigDecimal calificacion,
            @RequestParam(required = false) String comentario) {
        try {
            LocalDate fechaParsed = LocalDate.parse(fecha);
            reporteService.actualizarReporte(id, cedula, idCarrera, idAsignatura, idCurso, idEstudiante, motivo, fechaParsed, calificacion, comentario);
            return "redirect:/admin/panel";
        } catch (Exception e) {
            usuariologger.error("Error al actualizar el reporte con ID {}: {}", id, e.getMessage(), e);
            return "redirect:/admin/panel";
        }
    }
}

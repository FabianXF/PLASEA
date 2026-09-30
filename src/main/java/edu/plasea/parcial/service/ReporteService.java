package edu.plasea.parcial.service;

import edu.plasea.parcial.dto.*;
import edu.plasea.parcial.model.*;
import edu.plasea.parcial.repository.*;
import org.hibernate.Hibernate; // Importación agregada para usar Hibernate.initialize()
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public List<EstudianteDTO> buscarEstudiantesPorCurso(Long idCurso) {
        List<Estudiante> estudiantes = estudianteRepository.findEstudiantesByCurso(idCurso);
        return estudiantes.stream()
                .map(estudiante -> new EstudianteDTO(estudiante.getIdEstudiante(), estudiante.getNombre()))
                .collect(Collectors.toList());
    }

    public Reporte guardarReporte(
            String cedula, Long carrera, Long asignatura, Long curso, Long idEstudiante,
            String motivo, LocalDate fecha, BigDecimal calificacion, String comentario) {
        Reporte reporte = new Reporte();

        // Buscar las entidades relacionadas
        Usuario usuario = usuarioRepository.findByIdUsuario(Long.valueOf(cedula));
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario con cédula " + cedula + " no encontrado.");
        }
        Carrera carreraEntity = carreraRepository.findById(carrera)
                .orElseThrow(() -> new IllegalArgumentException("Carrera con ID " + carrera + " no encontrada."));
        Asignatura asignaturaEntity = asignaturaRepository.findById(asignatura)
                .orElseThrow(() -> new IllegalArgumentException("Asignatura con ID " + asignatura + " no encontrada."));
        Curso cursoEntity = cursoRepository.findById(curso)
                .orElseThrow(() -> new IllegalArgumentException("Curso con ID " + curso + " no encontrado."));
        Estudiante estudianteEntity = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante con ID " + idEstudiante + " no encontrado."));

        // Asignar las entidades al reporte
        reporte.setUsuario(usuario);
        reporte.setCarrera(carreraEntity);
        reporte.setAsignatura(asignaturaEntity);
        reporte.setCurso(cursoEntity);
        reporte.setEstudiante(estudianteEntity);
        reporte.setMotivo(motivo);
        reporte.setFecha(fecha);
        reporte.setCalificacion(calificacion);
        reporte.setComentario(comentario);

        return reporteRepository.save(reporte);
    }

    public List<CarreraDTO> buscarCarrerasPorProfesor(String cedula) {
        Long idDocente = Long.valueOf(cedula);
        List<Carrera> carreras = carreraRepository.findCarrerasByProfesor(idDocente);
        return carreras.stream()
                .map(carrera -> new CarreraDTO(carrera.getIdCarrera(), carrera.getNombre()))
                .collect(Collectors.toList());
    }

    public List<AsignaturaDTO> buscarAsignaturasPorProfesorYCarrera(String cedula, Long idCarrera) {
        Long idUsuario = Long.valueOf(cedula);
        List<Asignatura> asignaturas = asignaturaRepository.findAsignaturasByProfesorAndCarrera(idUsuario, idCarrera);
        return asignaturas.stream()
                .map(asignatura -> new AsignaturaDTO(asignatura.getIdAsignatura(), asignatura.getNombre()))
                .collect(Collectors.toList());
    }

    public List<CursoDTO> buscarCursosPorProfesorCarreraYAsignatura(String cedula, Long idCarrera, Long idAsignatura) {
        Long idDocente = Long.valueOf(cedula);
        List<Curso> cursos = cursoRepository.findCursosByDocenteAndCarreraAndAsignatura(idDocente, idCarrera, idAsignatura);
        return cursos.stream()
                .map(curso -> new CursoDTO(curso.getIdCurso(), curso.getSemestreAcademico() + " - Grupo " + curso.getGrupo()))
                .collect(Collectors.toList());
    }

    public List<ReporteGeneralDTO> obtenerReporteGeneral() {
        List<Reporte> reportes = reporteRepository.findAllWithRelations();
        return reportes.stream()
                .map(reporte -> new ReporteGeneralDTO(
                        reporte.getIdReporte(),
                        reporte.getUsuario().getNombre(),
                        reporte.getCarrera().getNombre(),
                        reporte.getAsignatura().getNombre(),
                        reporte.getCurso().getIdCurso(),
                        reporte.getCurso().getSemestreAcademico() + " - Grupo " + reporte.getCurso().getGrupo(),
                        reporte.getEstudiante().getNombre(),
                        reporte.getMotivo(),
                        reporte.getFecha(),
                        reporte.getCalificacion(),
                        reporte.getComentario()
                ))
                .collect(Collectors.toList());
    }

    public List<CarreraReporteDTO> obtenerReporteCarreras() {
        List<Object[]> resultados = reporteRepository.findReportePorCarrera();
        return resultados.stream()
                .map(resultado -> new CarreraReporteDTO(
                        (String) resultado[0], // Nombre de la carrera
                        ((Number) resultado[1]).longValue() // Cantidad de reportes
                ))
                .collect(Collectors.toList());
    }

    public List<AsignaturaReporteDTO> obtenerReporteAsignaturas() {
        List<Object[]> resultados = reporteRepository.findReportePorAsignatura();
        return resultados.stream()
                .map(resultado -> new AsignaturaReporteDTO(
                        (String) resultado[0], // Nombre de la asignatura
                        ((Number) resultado[1]).longValue() // Cantidad de reportes
                ))
                .collect(Collectors.toList());
    }

    public List<CursoReporteDTO> obtenerReporteCursos() {
        List<Object[]> resultados = reporteRepository.findReportePorCurso();
        return resultados.stream()
                .map(resultado -> new CursoReporteDTO(
                        ((Number) resultado[0]).longValue(), // ID del curso
                        (String) resultado[1], // Semestre académico
                        (String) resultado[2], // Grupo
                        ((Number) resultado[3]).longValue() // Cantidad de reportes
                ))
                .collect(Collectors.toList());
    }

    public List<ReporteGeneralDTO> obtenerReportesPorUsuario(Long id) {
        List<Reporte> reportes = reporteRepository.findByUsuarioIdUsuarioWithRelations(id);
        return reportes.stream()
                .map(reporte -> new ReporteGeneralDTO(
                        reporte.getIdReporte(),
                        reporte.getUsuario().getNombre(),
                        reporte.getCarrera().getNombre(),
                        reporte.getAsignatura().getNombre(),
                        reporte.getCurso().getIdCurso(),
                        reporte.getCurso().getSemestreAcademico() + " - Grupo " + reporte.getCurso().getGrupo(),
                        reporte.getEstudiante().getNombre(),
                        reporte.getMotivo(),
                        reporte.getFecha(),
                        reporte.getCalificacion(),
                        reporte.getComentario()
                ))
                .collect(Collectors.toList());
    }

    public void eliminarReporte(Long id) {
        if (!reporteRepository.existsById(id)) {
            throw new IllegalArgumentException("Reporte con ID " + id + " no encontrado.");
        }
        reporteRepository.deleteById(id);
    }

    public Reporte obtenerReportePorId(Long id) {
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reporte con ID " + id + " no encontrado."));
        Hibernate.initialize(reporte.getUsuario());
        Hibernate.initialize(reporte.getCarrera());
        Hibernate.initialize(reporte.getAsignatura());
        Hibernate.initialize(reporte.getCurso());
        Hibernate.initialize(reporte.getEstudiante());
        return reporte;
    }

    public void actualizarReporte(
            Long id, String cedula, Long idCarrera, Long idAsignatura, Long idCurso, Long idEstudiante,
            String motivo, LocalDate fecha, BigDecimal calificacion, String comentario) {
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reporte con ID " + id + " no encontrado."));

        // Validar y asignar las entidades relacionadas
        Usuario usuario = usuarioRepository.findByIdUsuario(Long.valueOf(cedula));
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario con cédula " + cedula + " no encontrado.");
        }
        Carrera carrera = carreraRepository.findById(idCarrera)
                .orElseThrow(() -> new IllegalArgumentException("Carrera con ID " + idCarrera + " no encontrada."));
        Asignatura asignatura = asignaturaRepository.findById(idAsignatura)
                .orElseThrow(() -> new IllegalArgumentException("Asignatura con ID " + idAsignatura + " no encontrada."));
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new IllegalArgumentException("Curso con ID " + idCurso + " no encontrado."));
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante con ID " + idEstudiante + " no encontrado."));

        // Actualizar los campos del reporte
        reporte.setUsuario(usuario);
        reporte.setCarrera(carrera);
        reporte.setAsignatura(asignatura);
        reporte.setCurso(curso);
        reporte.setEstudiante(estudiante);
        reporte.setMotivo(motivo);
        reporte.setFecha(fecha);
        reporte.setCalificacion(calificacion);
        reporte.setComentario(comentario);

        reporteRepository.save(reporte);
    }
}
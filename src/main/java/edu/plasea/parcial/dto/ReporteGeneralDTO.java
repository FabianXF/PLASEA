package edu.plasea.parcial.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReporteGeneralDTO {
    private Long idReporte;
    private String nombreProfesor;
    private String nombreCarrera;
    private String nombreAsignatura;
    private Long idCurso;
    private String curso;
    private String nombreEstudiante;
    private String motivo;
    private LocalDate fecha;
    private BigDecimal calificacion;
    private String comentario;

    public ReporteGeneralDTO() {}

    public ReporteGeneralDTO(Long idReporte, String nombreProfesor, String nombreCarrera, String nombreAsignatura,
                             Long idCurso, String curso, String nombreEstudiante, String motivo, LocalDate fecha,
                             BigDecimal calificacion, String comentario) {
        this.idReporte = idReporte;
        this.nombreProfesor = nombreProfesor;
        this.nombreCarrera = nombreCarrera;
        this.nombreAsignatura = nombreAsignatura;
        this.idCurso = idCurso;
        this.curso = curso;
        this.nombreEstudiante = nombreEstudiante;
        this.motivo = motivo;
        this.fecha = fecha;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    // Getters y Setters
    public Long getIdReporte() { return idReporte; }
    public void setIdReporte(Long idReporte) { this.idReporte = idReporte; }

    public String getNombreProfesor() { return nombreProfesor; }
    public void setNombreProfesor(String nombreProfesor) { this.nombreProfesor = nombreProfesor; }

    public String getNombreCarrera() { return nombreCarrera; }
    public void setNombreCarrera(String nombreCarrera) { this.nombreCarrera = nombreCarrera; }

    public String getNombreAsignatura() { return nombreAsignatura; }
    public void setNombreAsignatura(String nombreAsignatura) { this.nombreAsignatura = nombreAsignatura; }

    public Long getIdCurso() { return idCurso; }
    public void setIdCurso(Long idCurso) { this.idCurso = idCurso; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public String getNombreEstudiante() { return nombreEstudiante; }
    public void setNombreEstudiante(String nombreEstudiante) { this.nombreEstudiante = nombreEstudiante; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public BigDecimal getCalificacion() { return calificacion; }
    public void setCalificacion(BigDecimal calificacion) { this.calificacion = calificacion; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
}
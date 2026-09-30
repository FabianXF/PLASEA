package edu.plasea.parcial.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @Column(name = "id_curso")
    private Long idCurso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_docente", nullable = false)
    private Usuario docente; // Cambiado de Usuarios a Usuario

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_asig_carr", nullable = false)
    private AsignaturaCarrera asignaturaCarrera;

    @Column(name = "semestre_academico", nullable = false)
    private String semestreAcademico;

    @Column(name = "grupo", nullable = false)
    private String grupo;

    @ManyToMany(mappedBy = "cursos", fetch = FetchType.LAZY)
    private List<Estudiante> estudiantes = new ArrayList<>();

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reporte> reportes = new ArrayList<>();

    // Getters y Setters
    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public Usuario getDocente() {
        return docente;
    }

    public void setDocente(Usuario docente) {
        this.docente = docente;
    }

    public AsignaturaCarrera getAsignaturaCarrera() {
        return asignaturaCarrera;
    }

    public void setAsignaturaCarrera(AsignaturaCarrera asignaturaCarrera) {
        this.asignaturaCarrera = asignaturaCarrera;
    }

    public String getSemestreAcademico() {
        return semestreAcademico;
    }

    public void setSemestreAcademico(String semestreAcademico) {
        this.semestreAcademico = semestreAcademico;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }
}
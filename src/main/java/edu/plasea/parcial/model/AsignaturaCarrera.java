package edu.plasea.parcial.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "asignatura_carrera")
public class AsignaturaCarrera {

    @Id
    @Column(name = "id_asig_carr")
    private Long idAsigCarr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_asignatura", nullable = false)
    private Asignatura asignatura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrera", nullable = false)
    private Carrera carrera;

    @OneToMany(mappedBy = "asignaturaCarrera", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DocenteAsignaturaCarrera> docenteAsignaturaCarreras = new ArrayList<>();

    @OneToMany(mappedBy = "asignaturaCarrera", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Curso> cursos = new ArrayList<>();

    // Getters y Setters
    public Long getIdAsigCarr() {
        return idAsigCarr;
    }

    public void setIdAsigCarr(Long idAsigCarr) {
        this.idAsigCarr = idAsigCarr;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public List<DocenteAsignaturaCarrera> getDocenteAsignaturaCarreras() {
        return docenteAsignaturaCarreras;
    }

    public void setDocenteAsignaturaCarreras(List<DocenteAsignaturaCarrera> docenteAsignaturaCarreras) {
        this.docenteAsignaturaCarreras = docenteAsignaturaCarreras;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
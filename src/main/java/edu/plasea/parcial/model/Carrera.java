package edu.plasea.parcial.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carreras") // Nombre en minúsculas
public class Carrera {

    @Id
    @Column(name = "id_carrera")
    private Long idCarrera;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AsignaturaCarrera> asignaturaCarreras = new ArrayList<>();

    @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reporte> reportes = new ArrayList<>();

    // Getters y Setters
    public Long getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Long idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<AsignaturaCarrera> getAsignaturaCarreras() {
        return asignaturaCarreras;
    }

    public void setAsignaturaCarreras(List<AsignaturaCarrera> asignaturaCarreras) {
        this.asignaturaCarreras = asignaturaCarreras;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }
}
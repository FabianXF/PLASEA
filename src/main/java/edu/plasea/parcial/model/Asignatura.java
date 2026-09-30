package edu.plasea.parcial.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "asignaturas") // Nombre en minúsculas
public class Asignatura {

    @Id
    @Column(name = "id_asignatura")
    private Long idAsignatura;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "asignatura", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AsignaturaCarrera> asignaturaCarreras = new ArrayList<>();

    @OneToMany(mappedBy = "asignatura", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reporte> reportes = new ArrayList<>();

    // Constructors
    public Asignatura() {
    }

    public Asignatura(String nombre) {
        this.nombre = nombre;
    }

    // Getters and Setters
    public Long getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Long idAsignatura) {
        this.idAsignatura = idAsignatura;
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

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asignatura that = (Asignatura) o;
        return Objects.equals(idAsignatura, that.idAsignatura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAsignatura);
    }
}
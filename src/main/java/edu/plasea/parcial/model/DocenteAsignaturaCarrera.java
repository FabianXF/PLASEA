package edu.plasea.parcial.model;

import jakarta.persistence.*;

@Entity
@Table(name = "docente_asignatura_carrera") // Nombre en minúsculas
public class DocenteAsignaturaCarrera {

    @Id
    @Column(name = "id_doc_asig_carr")
    private Long idDocAsigCarr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario; // Cambiado de Usuarios a Usuario

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_asig_carr", nullable = false)
    private AsignaturaCarrera asignaturaCarrera;

    // Getters y Setters
    public Long getIdDocAsigCarr() {
        return idDocAsigCarr;
    }

    public void setIdDocAsigCarr(Long idDocAsigCarr) {
        this.idDocAsigCarr = idDocAsigCarr;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public AsignaturaCarrera getAsignaturaCarrera() {
        return asignaturaCarrera;
    }

    public void setAsignaturaCarrera(AsignaturaCarrera asignaturaCarrera) {
        this.asignaturaCarrera = asignaturaCarrera;
    }
}
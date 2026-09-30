package edu.plasea.parcial.dto;

public class CursoDTO {

    private Long idCurso;
    private String nombre;

    public CursoDTO(Long idCurso, String nombre) {
        this.idCurso = idCurso;
        this.nombre = nombre;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
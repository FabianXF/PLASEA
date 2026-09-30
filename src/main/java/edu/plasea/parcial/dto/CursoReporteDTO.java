package edu.plasea.parcial.dto;

public class CursoReporteDTO {
    private Long idCurso;
    private String semestreAcademico;
    private String grupo;
    private Long cantidadReportes;

    public CursoReporteDTO(Long idCurso, String semestreAcademico, String grupo, Long cantidadReportes) {
        this.idCurso = idCurso;
        this.semestreAcademico = semestreAcademico;
        this.grupo = grupo;
        this.cantidadReportes = cantidadReportes;
    }

    // Getters y Setters
    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
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

    public Long getCantidadReportes() {
        return cantidadReportes;
    }

    public void setCantidadReportes(Long cantidadReportes) {
        this.cantidadReportes = cantidadReportes;
    }
}
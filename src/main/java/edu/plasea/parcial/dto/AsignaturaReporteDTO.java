package edu.plasea.parcial.dto;

public class AsignaturaReporteDTO {
    private String asignaturaNombre;
    private Long cantidadReportes;

    public AsignaturaReporteDTO(String asignaturaNombre, Long cantidadReportes) {
        this.asignaturaNombre = asignaturaNombre;
        this.cantidadReportes = cantidadReportes;
    }

    // Getters y Setters
    public String getAsignaturaNombre() {
        return asignaturaNombre;
    }

    public void setAsignaturaNombre(String asignaturaNombre) {
        this.asignaturaNombre = asignaturaNombre;
    }

    public Long getCantidadReportes() {
        return cantidadReportes;
    }

    public void setCantidadReportes(Long cantidadReportes) {
        this.cantidadReportes = cantidadReportes;
    }
}
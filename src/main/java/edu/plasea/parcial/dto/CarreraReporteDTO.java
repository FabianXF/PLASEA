package edu.plasea.parcial.dto;

public class CarreraReporteDTO {

    private String nombre;
    private Long cantidadReportes;

    public CarreraReporteDTO(String nombre, Long cantidadReportes) {
        this.nombre = nombre;
        this.cantidadReportes = cantidadReportes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCantidadReportes() {
        return cantidadReportes;
    }

    public void setCantidadReportes(Long cantidadReportes) {
        this.cantidadReportes = cantidadReportes;
    }
}

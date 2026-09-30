package edu.plasea.parcial.repository;

import edu.plasea.parcial.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {
    @Query("SELECT DISTINCT c FROM Carrera c JOIN c.asignaturaCarreras ac JOIN ac.docenteAsignaturaCarreras dac WHERE dac.usuario.idUsuario = :idDocente")
    List<Carrera> findCarrerasByProfesor(@Param("idDocente") Long idDocente);
}
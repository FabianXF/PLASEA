package edu.plasea.parcial.repository;

import edu.plasea.parcial.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {
    @Query("SELECT DISTINCT a FROM Asignatura a JOIN a.asignaturaCarreras ac JOIN ac.docenteAsignaturaCarreras dac WHERE dac.usuario.idUsuario = :idUsuario AND ac.carrera.idCarrera = :idCarrera")
    List<Asignatura> findAsignaturasByProfesorAndCarrera(@Param("idUsuario") Long idUsuario, @Param("idCarrera") Long idCarrera);
}
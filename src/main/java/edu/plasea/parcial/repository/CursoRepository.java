package edu.plasea.parcial.repository;

import edu.plasea.parcial.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    @Query("SELECT c FROM Curso c JOIN c.asignaturaCarrera ac WHERE c.docente.idUsuario = :idDocente AND ac.carrera.idCarrera = :idCarrera AND ac.asignatura.idAsignatura = :idAsignatura")
    List<Curso> findCursosByDocenteAndCarreraAndAsignatura(@Param("idDocente") Long idDocente, @Param("idCarrera") Long idCarrera, @Param("idAsignatura") Long idAsignatura);
}
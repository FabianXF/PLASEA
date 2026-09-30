package edu.plasea.parcial.repository;

import edu.plasea.parcial.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    @Query("SELECT e FROM Estudiante e JOIN e.cursos c WHERE c.idCurso = :idCurso")
    List<Estudiante> findEstudiantesByCurso(@Param("idCurso") Long idCurso);
}
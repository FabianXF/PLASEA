package edu.plasea.parcial.repository;

import edu.plasea.parcial.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ReporteRepository extends JpaRepository<Reporte, Long> {

    @Query("SELECT r FROM Reporte r " +
           "JOIN FETCH r.usuario " +
           "JOIN FETCH r.carrera " +
           "JOIN FETCH r.asignatura " +
           "JOIN FETCH r.curso " +
           "JOIN FETCH r.estudiante")
    List<Reporte> findAllWithRelations();

    @Query("SELECT r FROM Reporte r " +
           "JOIN FETCH r.usuario " +
           "JOIN FETCH r.carrera " +
           "JOIN FETCH r.asignatura " +
           "JOIN FETCH r.curso " +
           "JOIN FETCH r.estudiante " +
           "WHERE r.usuario.idUsuario = :id")
    List<Reporte> findByUsuarioIdUsuarioWithRelations(@Param("id") Long id);

    @Query("SELECT c.nombre, COUNT(r) FROM Reporte r JOIN r.carrera c GROUP BY c.nombre")
    List<Object[]> findReportePorCarrera();

    @Query("SELECT a.nombre, COUNT(r) FROM Reporte r JOIN r.asignatura a GROUP BY a.nombre")
    List<Object[]> findReportePorAsignatura();

    @Query("SELECT c.idCurso, c.semestreAcademico, c.grupo, COUNT(r) FROM Reporte r JOIN r.curso c GROUP BY c.idCurso, c.semestreAcademico, c.grupo")
    List<Object[]> findReportePorCurso();

    List<Reporte> findByUsuarioIdUsuario(Long idUsuario);
}
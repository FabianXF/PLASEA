package edu.plasea.parcial.repository;

import edu.plasea.parcial.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByIdUsuario(Long idUsuario);
}
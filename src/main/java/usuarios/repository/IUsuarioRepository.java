package usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usuarios.models.entidades.Usuario;

import javax.inject.Singleton;

@Singleton
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}

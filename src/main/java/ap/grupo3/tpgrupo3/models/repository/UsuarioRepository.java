package ap.grupo3.tpgrupo3.models.repository;

import ap.grupo3.tpgrupo3.models.entity.Cliente;
import ap.grupo3.tpgrupo3.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}

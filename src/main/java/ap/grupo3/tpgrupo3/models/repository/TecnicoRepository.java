package ap.grupo3.tpgrupo3.models.repository;

import ap.grupo3.tpgrupo3.models.entity.Cliente;
import ap.grupo3.tpgrupo3.models.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
}

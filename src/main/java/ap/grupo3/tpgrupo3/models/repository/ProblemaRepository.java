package ap.grupo3.tpgrupo3.models.repository;

import ap.grupo3.tpgrupo3.models.entity.TipoProblema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemaRepository extends JpaRepository<TipoProblema, Long> {
}

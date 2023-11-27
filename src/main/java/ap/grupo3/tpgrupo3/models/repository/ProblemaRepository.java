package ap.grupo3.tpgrupo3.models.repository;

import ap.grupo3.tpgrupo3.models.entity.TipoProblema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemaRepository extends JpaRepository<TipoProblema, Long> {

    @Query(value="SELECT * FROM tipo_problema e WHERE e.nombre like %?1%", nativeQuery=true)
    List<TipoProblema> findByNombre(String nombre, Class<TipoProblema> type);

}

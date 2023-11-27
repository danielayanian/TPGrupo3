package ap.grupo3.tpgrupo3.models.repository;

import ap.grupo3.tpgrupo3.models.entity.Cliente;
import ap.grupo3.tpgrupo3.models.entity.Servicio;
import ap.grupo3.tpgrupo3.models.entity.TipoProblema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    @Query(value="SELECT * FROM servicio s WHERE s.nombre like %?1%", nativeQuery=true)
    List<Servicio> findByNombre(String nombre, Class<Servicio> type);

}

package ap.grupo3.tpgrupo3.models.repository;

import ap.grupo3.tpgrupo3.models.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>  {

}

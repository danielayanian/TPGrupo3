package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Rol;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RolService {

    public void altaRol(Rol rol);

    public List<Rol> obtenerListaDeRoles();

    public void altaRolesIniciales();

}

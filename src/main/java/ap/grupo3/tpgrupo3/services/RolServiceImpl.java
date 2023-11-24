package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Rol;
import ap.grupo3.tpgrupo3.models.entity.Usuario;
import ap.grupo3.tpgrupo3.models.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public void altaRol(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    public List<Rol> obtenerListaDeRoles(){
        return rolRepository.findAll();
    }

    @Override
    public void altaRolesIniciales(){

        //Creo rol Area Comercial
        Rol rol = new Rol();
        rol.setRol("Area Comercial");
        rolRepository.save(rol);

        //Creo rol Area RRHH
        Rol rol2 = new Rol();
        rol2.setRol("Area RRHH");
        rolRepository.save(rol2);

        //Creo rol Mesa Ayuda
        Rol rol3 = new Rol();
        rol3.setRol("Mesa de Ayuda");
        rolRepository.save(rol3);

        //Creo rol Tecnico
        Rol rol4 = new Rol();
        rol4.setRol("Tecnico");
        rolRepository.save(rol4);

    }

}

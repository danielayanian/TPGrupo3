package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Rol;
import ap.grupo3.tpgrupo3.models.entity.Usuario;
import ap.grupo3.tpgrupo3.models.repository.RolRepository;
import ap.grupo3.tpgrupo3.models.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void altaUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void altaUsuariosIniciales(){

        //Creo usuario Area Comercial
        Rol rol = new Rol();
        //rol.setId(Long.valueOf(1));
        rol.setRol("Area Comercial");
        Usuario usuario = new Usuario();
        usuario.setUsername("juan_comercial");
        usuario.setPassword("1234");
        usuario.setRol(rol);
        altaUsuario(usuario);

        //Creo usuario Area RRHH
        Rol rol2 = new Rol();
        //rol2.setId(Long.valueOf(2));
        rol2.setRol("Area RRHH");
        Usuario usuario2 = new Usuario();
        usuario2.setUsername("laura_rrhh");
        usuario2.setPassword("1234");
        usuario2.setRol(rol2);
        altaUsuario(usuario2);

        //Creo usuario Mesa Ayuda
        Rol rol3 = new Rol();
        //rol3.setId(Long.valueOf(3));
        rol3.setRol("Mesa de Ayuda");
        Usuario usuario3 = new Usuario();
        usuario3.setUsername("luis_ayuda");
        usuario3.setPassword("1234");
        usuario3.setRol(rol3);
        altaUsuario(usuario3);

        //Creo usuario Tecnico
        Rol rol4 = new Rol();
        rol4.setRol("Tecnico");
        //rol4.setId(Long.valueOf(4));
        Usuario usuario4 = new Usuario();
        usuario4.setUsername("pedro_tecnico");
        usuario4.setPassword("1234");
        usuario4.setRol(rol4);
        altaUsuario(usuario4);

    }

    @Override
    public Usuario buscarUsuario(Long id) {

        Optional<Usuario> o = usuarioRepository.findById(id);
        return o.orElse(null);

    }

    @Override
    public List<Usuario> obtenerListaDeUsuarios() {
        return usuarioRepository.findAll();
    }

}

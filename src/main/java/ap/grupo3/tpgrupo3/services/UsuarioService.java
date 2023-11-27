package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Rol;
import ap.grupo3.tpgrupo3.models.entity.Usuario;
import ap.grupo3.tpgrupo3.models.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {

    public Usuario altaUsuario(Usuario usuario);

    public void altaUsuariosIniciales();

    public Usuario buscarUsuario(Long id);

    public Usuario buscarUsuarioPorUsername(String username);

    public List<Usuario> obtenerListaDeUsuarios();

}

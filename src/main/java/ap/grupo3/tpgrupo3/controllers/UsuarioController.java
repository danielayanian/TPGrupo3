package ap.grupo3.tpgrupo3.controllers;

import ap.grupo3.tpgrupo3.models.entity.Rol;
import ap.grupo3.tpgrupo3.models.entity.Usuario;
import ap.grupo3.tpgrupo3.services.RolService;
import ap.grupo3.tpgrupo3.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @GetMapping(value = "/login")
    public String login(Model model){

        List<Rol> roles = rolService.obtenerListaDeRoles();

        //Si no hay roles cargados en la DB, cargamos roles y usuarios
        if(roles.isEmpty()){
            usuarioService.altaUsuariosIniciales();
        }

        List<Usuario> usuarios = usuarioService.obtenerListaDeUsuarios();

        model.addAttribute("usuarios", usuarios);

        return "varias/login";

    }

    @PostMapping(value = "/verificarLogin")
    public String verificarLogin(@RequestParam("id") String id,
                                 @RequestParam("username") String username,
                                 @RequestParam("password") String password, Model model){

        Long idLong;
        try {
            idLong = Long.parseLong(id);
        } catch(NumberFormatException excepcion) {
            return "varias/loginIncorrecto";
        }

        Usuario usuario = usuarioService.buscarUsuario(idLong);
        if(usuario == null) return "varias/loginIncorrecto";
        //verificar login y si es valido devolver pagina correspondiente segun rol
        if(usuario.getUsername().equals(username) && usuario.getPassword().equals(password)){
            model.addAttribute("mensaje", "Login realizado exitosamente");
            model.addAttribute("username", usuario.getUsername());
            model.addAttribute("rol", usuario.getRol().getRol());
            if(usuario.getRol().getRol().equals("Area Comercial")) return "areaComercial/areaComercial";
            if(usuario.getRol().getRol().equals("Area RRHH")) return "rrhh/rrhh";
            if(usuario.getRol().getRol().equals("Mesa de Ayuda")) return "mesaAyuda/mesaAyuda";
            if(usuario.getRol().getRol().equals("Tecnico")) return "tecnicos/tecnicos";
        }

        return "varias/loginIncorrecto";

    }

}

package ap.grupo3.tpgrupo3.controllers;

import ap.grupo3.tpgrupo3.models.entity.Rol;
import ap.grupo3.tpgrupo3.models.entity.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class InicioController extends BaseController {

    @GetMapping()
    public String index(Model model) {
        return "varias/index";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {

        List<Rol> roles = rolService.obtenerListaDeRoles();

        //Si no hay roles cargados en la DB, cargamos roles y usuarios
        if (roles.isEmpty()) {
            usuarioService.altaUsuariosIniciales();
            //Doy de alta los 5 servicios que brinda la empresa
            servicioService.altaDeTodosLosServicios();
            //Doy de alta las especialidades
            especialidadService.altaDeTodasLasEspecialidades();
            //Doy de alta los problemas
            tipoProblemaService.altaDeTodosLosProblemas();
            //Doy de alta algunos clientes
            clienteService.altaDevariosClientes();
            //Doy de alta algunos tecnicos
            tecnicoService.altaDeVariosTecnicos();

        }

        List<Usuario> usuarios = usuarioService.obtenerListaDeUsuarios();

        model.addAttribute("usuarios", usuarios);

        return "varias/login";

    }

    @PostMapping(value = "/verificarLogin")
    public String verificarLogin(@RequestParam("id") String id,
                                 @RequestParam("username") String username,
                                 @RequestParam("password") String password, Model model) {

        Long idLong;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException excepcion) {
            return "varias/loginIncorrecto";
        }

        Usuario usuario = usuarioService.buscarUsuario(idLong);
        if (usuario == null) return "varias/loginIncorrecto";
        //verificar login y si es valido devolver pagina correspondiente segun rol
        if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
            model.addAttribute("mensaje", "Login realizado exitosamente");
            model.addAttribute("username", usuario.getUsername());
            model.addAttribute("rol", usuario.getRol().getRol());
            if (usuario.getRol().getRol().equals("Area Comercial")) return "areaComercial/areaComercial";
            if (usuario.getRol().getRol().equals("Area RRHH")) return "rrhh/rrhh";
            if (usuario.getRol().getRol().equals("Mesa de Ayuda")) return "mesaAyuda/mesaAyuda";
            model.addAttribute("id", id);
            if (usuario.getRol().getRol().equals("Tecnico")) return "tecnicos/tecnicos";
        }

        return "varias/loginIncorrecto";

    }

}

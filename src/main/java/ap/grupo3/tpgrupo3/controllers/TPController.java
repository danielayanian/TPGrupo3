package ap.grupo3.tpgrupo3.controllers;

import ap.grupo3.tpgrupo3.models.entity.*;
import ap.grupo3.tpgrupo3.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TPController {

    @Autowired
    private RolService rolService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private TipoProblemaService tipoProblemaService;

    @Autowired
    private EspecialidadService especialidadService;

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
            if (usuario.getRol().getRol().equals("Tecnico")) return "tecnicos/tecnicos";
        }

        return "varias/loginIncorrecto";

    }

    @GetMapping(value = "/areaComercialPage")
    public String areaComercialPage(Model model) {
        return "areaComercial/areaComercial";
    }

    @GetMapping(value = "/altaClientePage")
    public String altaClientePage(Model model) {

        List<Servicio> servicios = servicioService.buscarTodosLosServicios();

        model.addAttribute("servicios", servicios);

        return "areaComercial/altaCliente";

    }

    @GetMapping(value = "/bajaClientePage")
    public String bajaClientePage(Model model) {
        return "areaComercial/bajaCliente";
    }

    @GetMapping(value = "/updateClientePage")
    public String updateClientePage(Model model) {

        List<Servicio> servicios = servicioService.buscarTodosLosServicios();

        model.addAttribute("servicios", servicios);

        return "areaComercial/updateCliente";

    }

    @PostMapping(value = "/altaCliente")
    public String altaCliente(@RequestParam(name = "nombre") String nombre,
                              @RequestParam(name = "razonSocial") String razonSocial,
                              @RequestParam(name = "cuit") String cuit,
                              @RequestParam(name = "email") String email,
                              @RequestParam(name = "telefono") String telefono,
                              @RequestParam(name = "idChecked", required = false) List<String> serviciosMarcados,
                              Model model) {

        List<Servicio> serviciosContratados = new ArrayList<>();

        if (serviciosMarcados != null) {

            Long idLong = Long.parseLong("0");
            for (String nombreServicio : serviciosMarcados) {
                Servicio s = new Servicio();
                if (nombreServicio.equals("SAP")) {
                    idLong = Long.parseLong("1");
                }
                if (nombreServicio.equals("TANGO")) {
                    idLong = Long.parseLong("2");
                }
                if (nombreServicio.equals("WINDOWS")) {
                    idLong = Long.parseLong("3");
                }
                if (nombreServicio.equals("MAC")) {
                    idLong = Long.parseLong("4");
                }
                if (nombreServicio.equals("LINUX")) {
                    idLong = Long.parseLong("5");
                }
                s.setId(idLong);
                serviciosContratados.add(s);
            }

        }

        Cliente cliente = new Cliente(nombre, razonSocial, cuit, email, telefono, serviciosContratados, null);

        clienteService.altaCliente(cliente);

        model.addAttribute("mensaje", "Cliente ingresado con éxito");

        return "areaComercial/mensajeCliente";

    }

    @PostMapping(value = "/bajaCliente")
    public String bajaCliente(@RequestParam("id") String id, Model model) {

        Long idLong = Long.parseLong(id);

        Cliente cliente = clienteService.buscarCliente(idLong);
        if (cliente == null){

            model.addAttribute("mensaje", "ERROR: Cliente no existe");

            return "areaComercial/mensajeCliente";

        }else{

            clienteService.bajaCliente(cliente);

            model.addAttribute("mensaje", "Cliente eliminado con éxito");

            return "areaComercial/mensajeCliente";
        }



    }

    @PostMapping(value = "/updateCliente")
    public String updateCliente(@RequestParam(name = "id") String id,
                                @RequestParam(name = "nombre") String nombre,
                                @RequestParam(name = "razonSocial") String razonSocial,
                                @RequestParam(name = "cuit") String cuit,
                                @RequestParam(name = "email") String email,
                                @RequestParam(name = "telefono") String telefono,
                                @RequestParam(name = "idChecked", required = false) List<String> serviciosMarcados,
                                Model model) {

        List<Servicio> serviciosContratados = new ArrayList<>();

        if (serviciosMarcados != null) {

            Long idLong = Long.parseLong("0");
            for (String nombreServicio : serviciosMarcados) {
                Servicio s = new Servicio();
                if (nombreServicio.equals("SAP")) {
                    idLong = Long.parseLong("1");
                }
                if (nombreServicio.equals("TANGO")) {
                    idLong = Long.parseLong("2");
                }
                if (nombreServicio.equals("WINDOWS")) {
                    idLong = Long.parseLong("3");
                }
                if (nombreServicio.equals("MAC")) {
                    idLong = Long.parseLong("4");
                }
                if (nombreServicio.equals("LINUX")) {
                    idLong = Long.parseLong("5");
                }
                s.setId(idLong);
                serviciosContratados.add(s);
            }

        }

        Long idLong = Long.parseLong(id);

        Cliente cliente = clienteService.buscarCliente(idLong);
        if (cliente != null) {
            cliente.setNombre(nombre);
            cliente.setRazonSocial(razonSocial);
            cliente.setCUIT(cuit);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            cliente.setServiciosContratados(serviciosContratados);

            clienteService.updateCliente(cliente);

            model.addAttribute("mensaje", "Cliente actualizado con éxito");

            return "areaComercial/mensajeCliente";
        }

        model.addAttribute("mensaje", "ERROR: Cliente no existe");

        return "areaComercial/mensajeCliente";

    }

    @GetMapping(value = "/RRHHPage")
    public String RRHHPage(Model model) {
        return "rrhh/rrhh";
    }


    @GetMapping(value = "/altaTecnicoPage")
    public String altaTecnicoPage(Model model){

        List<Especialidad> especialidades = especialidadService.buscarTodasLasEspecialidades();

        model.addAttribute("especialidades", especialidades);

        return "rrhh/altaTecnico";

    }

    @PostMapping(value = "/altaTecnico")
    public String altaTecnico(@RequestParam(name = "nombre") String nombre,
                              @RequestParam(name = "email") String email,
                              @RequestParam(name = "telefono") String telefono,
                              @RequestParam(name = "idChecked", required = false) List<String> especialidadesMarcadas,
                              Model model) {

        List<Especialidad> especialidades = new ArrayList<>();

        if (especialidadesMarcadas != null) {

            Long idLong = Long.parseLong("0");
            for (String nombreEpecialidad : especialidadesMarcadas) {
                Especialidad e = new Especialidad();
                if (nombreEpecialidad.equals("SAP")) {
                    idLong = Long.parseLong("1");
                }
                if (nombreEpecialidad.equals("TANGO")) {
                    idLong = Long.parseLong("2");
                }
                if (nombreEpecialidad.equals("WINDOWS")) {
                    idLong = Long.parseLong("3");
                }
                if (nombreEpecialidad.equals("MAC")) {
                    idLong = Long.parseLong("4");
                }
                if (nombreEpecialidad.equals("LINUX")) {
                    idLong = Long.parseLong("5");
                }
                e.setId(idLong);
                especialidades.add(e);
            }

        }

        Tecnico tecnico = new Tecnico(nombre, email, telefono, especialidades, null);

        tecnicoService.altaTecnico(tecnico);

        model.addAttribute("mensaje", "Técnico ingresado con éxito");

        return "rrhh/mensaje";

    }



    //Seguir con la baja de un tecnico


    @GetMapping(value = "/bajaTecnicoPage")
    public String bajaTecnicoPage(Model model){
        return "rrhh/bajaTecnico";
    }
    @GetMapping(value = "/updateTecnicoPage")
    public String updateTecnicoPage(Model model){
        return "rrhh/updateTecnico";
    }

    @GetMapping(value = "/emitirReportes")
    public String emitirReportes(Model model){
        return "rrhh/reportes";
    }

    @GetMapping(value = "/obtenerTecnicoMasIncidentesResueltosNDias")
    public String obtenerTecnicoMasIncidentesResueltosNDias(Model model){
        return "rrhh/tecnicoMasIncidentesResNDias";
    }

    @GetMapping(value = "/obtenerTecnicoMasIncidentesResueltosPorEspecialidadNDias")
    public String obtenerTecnicoMasIncidentesResueltosPorEspecialidadNDias(Model model){
        return "rrhh/tecnicoMasIncidentesResPorEspNDias";
    }

    @GetMapping(value = "/obtenerTecnicoMasRapido")
    public String obtenerTecnicoMasRapido(Model model){
        return "rrhh/tecnicoMasRapido";
    }

    @GetMapping(value = "/mesaAyudaPage")
    public String mesaAyudaPage(Model model) {
        return "mesaAyuda/mesaAyuda";
    }

    @GetMapping(value = "/atenderLlamadoCliente")
    public String atenderLlamadoCliente(Model model){
        return "mesaAyuda/atenderLlamadoCliente";
    }

    @GetMapping(value = "/tecnicosPage")
    public String tecnicosPage(Model model) {
        return "tecnicos/tecnicos";
    }

    @GetMapping(value = "/resolverUnIncidente")
    public String resolverUnIncidente(Model model){
        return "tecnicos/resolverUnIncidente";
    }

}
package ap.grupo3.tpgrupo3.controllers;

import ap.grupo3.tpgrupo3.models.entity.*;
import ap.grupo3.tpgrupo3.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

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

    @Autowired
    private IncidenteService incidenteService;

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
            //clienteService.altaDevariosClientes();
            //Doy de alta algunos tecnicos
            //tecnicoService.altaDeVariosTecnicos();

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

        Tecnico tecnico = new Tecnico(nombre, email, telefono, "SI", especialidades, null);

        tecnicoService.altaTecnico(tecnico);

        model.addAttribute("mensaje", "Técnico ingresado con éxito");

        return "rrhh/mensaje";

    }

    @GetMapping(value = "/bajaTecnicoPage")
    public String bajaTecnicoPage(Model model){
        return "rrhh/bajaTecnico";
    }

    @PostMapping(value = "/bajaTecnico")
    public String bajaTecnico(@RequestParam("id") String id, Model model){

        Long idLong = Long.parseLong(id);

        Tecnico tecnico =tecnicoService.buscarTecnico(idLong);
        if (tecnico == null){

            model.addAttribute("mensaje", "ERROR: Técnico no existe");

            return "rrhh/mensaje";

        }else{

            tecnicoService.bajaTecnico(tecnico);

            model.addAttribute("mensaje", "Técnico eliminado con éxito");

            return "rrhh/mensaje";
        }

    }

    @GetMapping(value = "/updateTecnicoPage")
    public String updateTecnicoPage(Model model){

        List<Especialidad> especialidades = especialidadService.buscarTodasLasEspecialidades();

        model.addAttribute("especialidades", especialidades);

        return "rrhh/updateTecnico";
    }

    @PostMapping(value = "/updateTecnico")
    public String updateCliente(@RequestParam(name = "id") String id,
                                @RequestParam(name = "nombre") String nombre,
                                @RequestParam(name = "email") String email,
                                @RequestParam(name = "telefono") String telefono,
                                @RequestParam(name = "idChecked", required = false) List<String> especialidadesMarcadas,
                                Model model) {

        List<Especialidad> especialidades = new ArrayList<>();

        if (especialidadesMarcadas != null) {

            Long idLong = Long.parseLong("0");
            for (String nombreEspecialidad : especialidadesMarcadas) {
                Especialidad e = new Especialidad();
                if (nombreEspecialidad.equals("SAP")) {
                    idLong = Long.parseLong("1");
                }
                if (nombreEspecialidad.equals("TANGO")) {
                    idLong = Long.parseLong("2");
                }
                if (nombreEspecialidad.equals("WINDOWS")) {
                    idLong = Long.parseLong("3");
                }
                if (nombreEspecialidad.equals("MAC")) {
                    idLong = Long.parseLong("4");
                }
                if (nombreEspecialidad.equals("LINUX")) {
                    idLong = Long.parseLong("5");
                }
                e.setId(idLong);
                especialidades.add(e);
            }

        }

        Long idLong = Long.parseLong(id);

        Tecnico tecnico = tecnicoService.buscarTecnico(idLong);

        if (tecnico != null) {
            tecnico.setNombre(nombre);
            tecnico.setEmail(email);
            tecnico.setTelefono(telefono);
            tecnico.setEspecialidades(especialidades);

            tecnicoService.updateTecnico(tecnico);

            model.addAttribute("mensaje", "Técnico actualizado con éxito");

            return "rrhh/mensaje";
        }

        model.addAttribute("mensaje", "ERROR: Técnico no existe");

        return "rrhh/mensaje";

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

    @PostMapping(value = "/obtenerServiciosContratados")
    public String obtenerServiciosContratados(@RequestParam(name = "id") String id, Model model) {

        Long idLong = Long.parseLong(id);

        Cliente cliente = clienteService.buscarCliente(idLong);
        if (cliente != null) {

            List<Servicio> serviciosContratados = cliente.getServiciosContratados();

            model.addAttribute("serviciosContratados", serviciosContratados);

            List<TipoProblema> tiposDeProblemas = tipoProblemaService.buscarTodosLosProblemas();

            model.addAttribute("tiposDeProblemas", tiposDeProblemas);

            model.addAttribute("idCliente", id);

            return "mesaAyuda/indicarServicioYProblema";

        } else {

            model.addAttribute("mensaje", "ERROR: Cliente no existe");

            return "mesaAyuda/mensaje";

        }

    }

    @PostMapping(value = "/altaIncidente")
    public String altaIncidente(@RequestParam(name = "idChecked1", required = false) List<String> servicios,
                                @RequestParam(name = "descripcionProblema") String descripcionProblema,
                                @RequestParam(name = "idChecked2", required = false) List<String> problemas,
                                @RequestParam(name = "idCliente") String idCliente,
                                Model model, final RedirectAttributes redirectAttrs) {

        //Creo Incidente
        Incidente incidente = new Incidente();
        incidente.setAlias("Incidente");

        Date fechaActual = new Date(System.currentTimeMillis());
        incidente.setFecheDesde(fechaActual);

        incidente.setResuelto(0);

        Cliente cliente = new Cliente();
        Long idLong = Long.parseLong(idCliente);
        cliente.setId(idLong);
        incidente.setCliente(cliente);

        //Creo IncidenteDetalle
        IncidenteDetalle incidenteDetalle = new IncidenteDetalle();
        incidenteDetalle.setDescripcion(descripcionProblema);

        List<Servicio> serviciosConDatosCompletos = servicioService.buscarServiciosPorNombre(servicios.get(0));
        Servicio servicio = (Servicio) (serviciosConDatosCompletos.get(0));

        incidenteDetalle.setServicio(servicio);
        incidenteDetalle.setIncidente(incidente);

        List<TipoProblema> problemasConDatosCompletos = tipoProblemaService.buscarProblemasPorNombre(problemas.get(0));
        TipoProblema tipoProblema = (TipoProblema) (problemasConDatosCompletos.get(0));

        incidenteDetalle.setTipoProblema(tipoProblema);

        List<IncidenteDetalle> detallesDelIncidente = new ArrayList<>();
        detallesDelIncidente.add(incidenteDetalle);

        incidente.setDetallesDelIncidente(detallesDelIncidente);

        Incidente incidenteInsertado = incidenteService.altaIncidente(incidente);

        redirectAttrs.addFlashAttribute("idIncidente", incidenteInsertado.getId());
        redirectAttrs.addFlashAttribute("idTipoProblema", tipoProblema.getId());

        return "redirect:/tecnicosDisponibles";

    }

    @GetMapping(value = "/tecnicosDisponibles")
    public String tecnicosDisponibles(@ModelAttribute("idIncidente") final String idIncidente,
                                      @ModelAttribute("idTipoProblema") final String idTipoProblema,
                                      Model model) {

        Long idLongTipoProblema = Long.parseLong(idTipoProblema);
        TipoProblema tipoProblema = tipoProblemaService.buscarTipoProblema(idLongTipoProblema);
        List<Especialidad> especialidadesProblema = tipoProblema.getEspecialidadesQueResuelvenElProblema();
        List<Tecnico> tecnicos = tecnicoService.obtenerTodosLosTecnicos();
        Set<Tecnico> tecnicosDisponibles = new HashSet<>();

        for(Tecnico t : tecnicos){
            for(Especialidad e : t.getEspecialidades()){
                for(Especialidad ep : especialidadesProblema){
                    if(e.getId().equals(ep.getId())){
                        if((t.getDisponible()).equals("SI")){
                            tecnicosDisponibles.add(t);
                        }
                    }
                }
            }
        }

        model.addAttribute("tecnicosDisponibles", tecnicosDisponibles);
        model.addAttribute("idIncidente", idIncidente);
        model.addAttribute("idTipoProblema", idTipoProblema);

        return "mesaAyuda/mostrarTecnicosDisponibles";
    }

    @PostMapping(value = "/tiempoDeResolucion")
    public String tiempoDeResolucion(@RequestParam(name = "idIncidente") String idIncidente,
                                     @RequestParam(name = "idTipoProblema") String idTipoProblema,
                                     @RequestParam(name = "idChecked", required = false) List<String> tecnicos,
                                     Model model) {

        if(tecnicos == null){
            model.addAttribute("mensaje", "ERROR: Debe seleccionar un técnico");
            return "mesaAyuda/mensaje";
        }

        Long idLongInc = Long.parseLong(idIncidente);
        Incidente incidente = incidenteService.buscarIncidente(idLongInc);

        Long idLongTec = Long.parseLong(tecnicos.get(0));
        Tecnico tecnico = tecnicoService.buscarTecnico(idLongTec);
        tecnico.setDisponible("NO");
        incidente.setTecnico(tecnico);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(incidente.getFecheDesde());

        Long idLongTP = Long.parseLong(idTipoProblema);
        TipoProblema tipoProblema = tipoProblemaService.buscarTipoProblema(idLongTP);

        calendar.add(Calendar.DAY_OF_YEAR, tipoProblema.getTiempoEstimado());
        Date fechaEstimada = calendar.getTime();

        incidente.setFechaEstimada(fechaEstimada);

        incidenteService.updateIncidente(incidente);

        model.addAttribute("tiempoEstimado", tipoProblema.getTiempoEstimado());
        model.addAttribute("fechaEstimada", fechaEstimada);

        return "mesaAyuda/informesParaElCliente";

    }

    @PostMapping(value = "/tecnicoNotificado")
    public String tecnicoNotificado(Model model) {
        return "mesaAyuda/tecnicoNotificado";
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
package ap.grupo3.tpgrupo3.controllers;

import ap.grupo3.tpgrupo3.models.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class MesaAyudaController extends BaseController {

    @GetMapping(value = "/mesaAyudaPage")
    public String mesaAyudaPage(Model model) {
        return "mesaAyuda/mesaAyuda";
    }

    @GetMapping(value = "/atenderLlamadoCliente")
    public String atenderLlamadoCliente(Model model) {
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

        for (Tecnico t : tecnicos) {
            for (Especialidad e : t.getEspecialidades()) {
                for (Especialidad ep : especialidadesProblema) {
                    if (e.getId().equals(ep.getId())) {
                        if ((t.getDisponible()).equals("SI")) {
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

        if (tecnicos == null) {
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

}

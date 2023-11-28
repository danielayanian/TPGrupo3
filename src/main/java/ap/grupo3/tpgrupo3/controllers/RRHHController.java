package ap.grupo3.tpgrupo3.controllers;

import ap.grupo3.tpgrupo3.models.entity.Especialidad;
import ap.grupo3.tpgrupo3.models.entity.Incidente;
import ap.grupo3.tpgrupo3.models.entity.Tecnico;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RRHHController extends BaseController {

    @GetMapping(value = "/RRHHPage")
    public String RRHHPage(Model model) {
        return "rrhh/rrhh";
    }


    @GetMapping(value = "/altaTecnicoPage")
    public String altaTecnicoPage(Model model) {

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

        //Doy de alta al Tecnico
        tecnicoService.altaTecnico(tecnico);

        model.addAttribute("mensaje", "Técnico creado con éxito");

        return "rrhh/mensajeNuevoTecnico";

    }

    @GetMapping(value = "/bajaTecnicoPage")
    public String bajaTecnicoPage(Model model) {
        return "rrhh/bajaTecnico";
    }

    @PostMapping(value = "/bajaTecnico")
    public String bajaTecnico(@RequestParam("id") String id, Model model) {

        Long idLong = Long.parseLong(id);

        Tecnico tecnico = tecnicoService.buscarTecnico(idLong);
        if (tecnico == null) {

            model.addAttribute("mensaje", "ERROR: Técnico no existe");

            return "rrhh/mensaje";

        } else {

            tecnicoService.bajaTecnico(tecnico);

            model.addAttribute("mensaje", "Técnico eliminado con éxito");

            return "rrhh/mensaje";
        }

    }

    @GetMapping(value = "/updateTecnicoPage")
    public String updateTecnicoPage(Model model) {

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
    public String emitirReportes(Model model) {

        List<Incidente> incidentes = incidenteService.buscarTodosLosIncidentes();

        Date fechaActual = new Date(System.currentTimeMillis());

        int milisecondsByDay = 86400000;

        List<Incidente> incidentesDeHoy = incidentes.
                stream().filter(incidente ->
                        ((fechaActual.getTime()-incidente.getFechaDesde().getTime()) / milisecondsByDay) <= 1 )
                .collect(Collectors.toList());

        model.addAttribute("incidentes", incidentesDeHoy);

        return "rrhh/reportes";

    }

    @GetMapping(value = "pedirN")
    public String pedirN(){


        return "rrhh/pedirN";
    }


    @PostMapping(value = "/obtenerTecnicoMasIncidentesResueltosNDias")
    public String obtenerTecnicoMasIncidentesResueltosNDias(@RequestParam(name = "N") String N,
                                                            Model model) {

        List<Incidente> incidentes = incidenteService.buscarTodosLosIncidentes();

        Date fechaActual = new Date(System.currentTimeMillis());

        int milisecondsByDay = 86400000;

        List<Incidente> incidentesDeNDiasResueltos = incidentes.
                stream().filter(incidente ->
                        (((fechaActual.getTime()-incidente.getFechaDesde().getTime()) / milisecondsByDay) <= Integer.parseInt(N))
                && (incidente.getResuelto() == 1))
                .collect(Collectors.toList());

        //Seguir calculando

        model.addAttribute("incidentes", incidentesDeNDiasResueltos);

        return "rrhh/tecnicoMasIncidentesResNDias";
    }

    @GetMapping(value = "pedirNYEspecialidad")
    public String pedirNYEspecialidad(){


        return "rrhh/pedirNyEspecialidad";
    }

    @PostMapping(value = "/obtenerTecnicoMasIncidentesResueltosPorEspecialidadNDias")
    public String obtenerTecnicoMasIncidentesResueltosPorEspecialidadNDias(@RequestParam(name = "N") String N,
                                                                           @RequestParam(name = "especialidad") String especialidad,
                                                                           Model model) {

        List<Incidente> incidentes = incidenteService.buscarTodosLosIncidentes();

        Date fechaActual = new Date(System.currentTimeMillis());

        int milisecondsByDay = 86400000;

        List<Incidente> incidentesDeNDiasResueltos = incidentes.
                stream().filter(incidente ->
                        (((fechaActual.getTime()-incidente.getFechaDesde().getTime()) / milisecondsByDay) <= Integer.parseInt(N))
                                && (incidente.getResuelto() == 1))
                .collect(Collectors.toList());

        //Seguir calculando

        model.addAttribute("incidentes", incidentesDeNDiasResueltos);


        return "rrhh/tecnicoMasIncidentesResPorEspNDias";
    }

    @GetMapping(value = "/obtenerTecnicoMasRapido")
    public String obtenerTecnicoMasRapido(Model model) {

        //Obtener lista de tecnicos y sacar un promedio

        return "rrhh/tecnicoMasRapido";
    }

}

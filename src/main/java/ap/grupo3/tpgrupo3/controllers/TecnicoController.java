package ap.grupo3.tpgrupo3.controllers;

import ap.grupo3.tpgrupo3.models.entity.Incidente;
import ap.grupo3.tpgrupo3.models.entity.IncidenteDetalle;
import ap.grupo3.tpgrupo3.models.entity.Tecnico;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TecnicoController extends BaseController {

    @GetMapping(value = "/tecnicosPage")
    public String tecnicosPage(Model model) {
        return "tecnicos/tecnicos";
    }

    @GetMapping(value = "/ingresarIdTecnico")
    public String ingresarIdTecnico(Model model) {
        return "tecnicos/ingresarIdTecnico";
    }

    @PostMapping(value = "/resolverUnIncidente")
    public String resolverUnIncidente(@RequestParam(name = "id") String id, Model model) {

        //id es del usuario no del tecnico
        Tecnico tecnico = tecnicoService.buscarTecnico(Long.parseLong(id));


        if (tecnico.getIncidentes().size() != 0) {
            Incidente incidente = tecnico.getIncidentes().get(0);
            if(incidente.getResuelto() == 1){
                model.addAttribute("mensaje", "No tiene incidentes pendientes");
                return "tecnicos/mensajeSinPendientes";
            }
            List<IncidenteDetalle> detallesDelIncidente = incidente.getDetallesDelIncidente();
            model.addAttribute("id", id);
            model.addAttribute("detalleDescripcion", detallesDelIncidente.get(0).getDescripcion());
            return "tecnicos/resolverUnIncidente";
        } else {
            model.addAttribute("mensaje", "No tiene incidentes pendientes");
            return "tecnicos/mensajeSinPendientes";
        }

    }

    @PostMapping(value = "/incidenteResuelto")
    public String incidenteResuelto(@RequestParam(name = "id") String id,
                                    @RequestParam(name = "consideraciones") String consideraciones,
                                    Model model) {

        Tecnico tecnico = tecnicoService.buscarTecnico(Long.parseLong(id));

        Incidente incidente = tecnico.getIncidentes().get(0);

        incidente.setConsideraciones(consideraciones);
        incidente.setResuelto(1);

        tecnico.setDisponible("SI");
        tecnicoService.updateTecnico(tecnico);

        incidenteService.updateIncidente(incidente);

        return "tecnicos/incidenteResueltoYMailACliente";

    }

}

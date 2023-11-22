package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Especialidad;
import ap.grupo3.tpgrupo3.models.entity.Tecnico;
import org.springframework.stereotype.Service;

@Service
public class TecnicoService {

    public String marcarIncidenteComoResuelto(){
        return "consideraciones";
    }

    public Tecnico obtenerTecnicoConMasIncidentesResueltos(int dias){
        return null;
    }

    public Tecnico obtenerTecnicoConMasIncidentesResueltosDeUnaEspecialidad(int dias, Especialidad especialidad){
        return null;
    }

    public Tecnico obtenerTecnicoMasRapido(){
        return null;
    }

}

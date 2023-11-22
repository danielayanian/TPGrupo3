package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Incidente;
import ap.grupo3.tpgrupo3.models.entity.Tecnico;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MesaAyudaService {

    public List<Tecnico> ingresarIncidente(){
        return null;
    }

    public int asignarIncidenteATecnico(){
        return 1;
    }

    public int obtenerServiciosContratadosDeUnCliente(){
        return 1;
    }

    public Tecnico seleccionarTecnicoDisponible(List<Tecnico> tecnicosDisponibles){
        return null;
    }

    public int obtenerTiempoEstimadoDeResolucionDelIncidente(Incidente incidente, Tecnico tecnico){
        return 1;
    }

    public Date calcularFechaPosibleDeResolucionDelIncidente(Incidente incidente){
        return null;
    }

    public int enviarNotificacionAlTecnico(Incidente incidente, Tecnico tecnico){
        return 1;
    }

    public int agregarColchonExtraDeHorasPorIncidenteComplejo(){
        return 1;
    }

    public int ingresarIncidenteConProblemasDeUnMismoServicio(){
        return 1;
    }

}

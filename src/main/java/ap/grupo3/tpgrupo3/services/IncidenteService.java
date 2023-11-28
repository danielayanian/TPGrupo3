package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Cliente;
import ap.grupo3.tpgrupo3.models.entity.Incidente;
import ap.grupo3.tpgrupo3.models.entity.IncidenteDetalle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IncidenteService {

    public Incidente altaIncidente(Incidente incidente);

    public Incidente updateIncidente(Incidente incidente);

    public Incidente buscarIncidente(Long id);

    public List<IncidenteDetalle> buscarDetalles(Long id);

}

package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Cliente;
import ap.grupo3.tpgrupo3.models.entity.Incidente;
import org.springframework.stereotype.Service;

@Service
public interface IncidenteService {

    public Incidente altaIncidente(Incidente incidente);

    public Incidente updateIncidente(Incidente incidente);

    public Incidente buscarIncidente(Long id);

}

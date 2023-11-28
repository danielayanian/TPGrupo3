package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Incidente;
import ap.grupo3.tpgrupo3.models.entity.IncidenteDetalle;
import ap.grupo3.tpgrupo3.models.repository.IncidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidenteServiceImpl implements IncidenteService {

    @Autowired
    private IncidenteRepository incidenteRepository;

    @Override
    public Incidente altaIncidente(Incidente incidente) {

        return incidenteRepository.save(incidente);

    }

    @Override
    public Incidente updateIncidente(Incidente incidente) {
        return incidenteRepository.save(incidente);
    }

    @Override
    public Incidente buscarIncidente(Long id) {
        Optional<Incidente> o = incidenteRepository.findById(id);
        return o.orElse(null);
    }

    @Override
    public List<IncidenteDetalle> buscarDetalles(Long id) {
        Incidente incidente = this.buscarIncidente(id);
        return incidente.getDetallesDelIncidente();
    }

}

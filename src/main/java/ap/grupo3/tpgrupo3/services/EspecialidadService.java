package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Especialidad;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EspecialidadService {

    public void altaDeTodasLasEspecialidades();

    public List<Especialidad> buscarTodasLasEspecialidades();

}

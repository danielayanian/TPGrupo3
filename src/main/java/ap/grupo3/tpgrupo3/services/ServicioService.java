package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Servicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicioService {

    public void altaServicio(Servicio servicio);

    public void altaDeTodosLosServicios();

    public List<Servicio> buscarTodosLosServicios();

}

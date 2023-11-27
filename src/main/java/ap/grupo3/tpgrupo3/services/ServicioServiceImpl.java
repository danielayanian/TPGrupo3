package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Servicio;
import ap.grupo3.tpgrupo3.models.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public void altaServicio(Servicio servicio) {
        servicioRepository.save(servicio);
    }

    @Override
    public void altaDeTodosLosServicios() {

        Servicio servicioSAP = new Servicio("SAP", null, null);
        Servicio sservicioTANGO = new Servicio("TANGO", null, null);
        Servicio sservicioWINDOWS = new Servicio("WINDOWS", null, null);
        Servicio sservicioMAC = new Servicio("MAC", null, null);
        Servicio sservicioLINUX = new Servicio("LINUX", null, null);
        altaServicio(servicioSAP);
        altaServicio(sservicioTANGO);
        altaServicio(sservicioWINDOWS);
        altaServicio(sservicioMAC);
        altaServicio(sservicioLINUX);

    }

    @Override
    public List<Servicio> buscarTodosLosServicios() {
        return servicioRepository.findAll();
    }

    @Override
    public List<Servicio> buscarServiciosPorNombre(String nombre) {
        return servicioRepository.findByNombre(nombre, Servicio.class);
    }

}

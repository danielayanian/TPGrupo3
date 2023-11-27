package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Cliente;
import ap.grupo3.tpgrupo3.models.entity.Servicio;
import ap.grupo3.tpgrupo3.models.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void altaCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public void bajaCliente(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

    @Override
    public void updateCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente buscarCliente(Long id) {
        Optional<Cliente> o = clienteRepository.findById(id);
        return o.orElse(null);
    }

    @Override
    public void altaDevariosClientes() {

        Servicio servicioSAP = new Servicio("SAP", null, null);
        servicioSAP.setId(Long.parseLong("1"));
        Servicio sservicioTANGO = new Servicio("TANGO", null, null);
        sservicioTANGO.setId(Long.parseLong("2"));
        Servicio sservicioWINDOWS = new Servicio("WINDOWS", null, null);
        sservicioWINDOWS.setId(Long.parseLong("3"));
        Servicio sservicioMAC = new Servicio("MAC", null, null);
        sservicioMAC.setId(Long.parseLong("4"));
        Servicio sservicioLINUX = new Servicio("LINUX", null, null);
        sservicioLINUX.setId(Long.parseLong("5"));

        List<Servicio> servicioContratados1 = new ArrayList<>();
        servicioContratados1.add(sservicioTANGO);
        servicioContratados1.add(sservicioWINDOWS);
        Cliente c1 = new Cliente("Juan Lopez", "AVM", "123456", "juan@gmail.com", "654321", servicioContratados1, null);

        clienteRepository.save(c1);

        List<Servicio> servicioContratados2 = new ArrayList<>();
        servicioContratados2.add(servicioSAP);
        servicioContratados2.add(sservicioLINUX);
        Cliente c2 = new Cliente("Laura Gomez", "GDL", "141441", "laura@gmail.com", "453435", servicioContratados2, null);

        clienteRepository.save(c2);

        List<Servicio> servicioContratados3 = new ArrayList<>();
        servicioContratados3.add(sservicioTANGO);
        servicioContratados3.add(sservicioWINDOWS);
        servicioContratados3.add(sservicioLINUX);
        Cliente c3 = new Cliente("Luis Alvarez", "IBM", "234126", "luis@gmail.com", "334423", servicioContratados3, null);

        clienteRepository.save(c3);

    }
}

package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Especialidad;
import ap.grupo3.tpgrupo3.models.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Override
    public void altaDeTodasLasEspecialidades() {

        Especialidad especialidad1 = new Especialidad();
        especialidad1.setNombre("SAP");
        especialidadRepository.save(especialidad1);

        Especialidad especialidad2 = new Especialidad();
        especialidad2.setNombre("TANGO");
        especialidadRepository.save(especialidad2);

        Especialidad especialidad3 = new Especialidad();
        especialidad3.setNombre("WINDOWS");
        especialidadRepository.save(especialidad3);

        Especialidad especialidad4 = new Especialidad();
        especialidad4.setNombre("MAC");
        especialidadRepository.save(especialidad4);

        Especialidad especialidad5 = new Especialidad();
        especialidad5.setNombre("LINUX");
        especialidadRepository.save(especialidad5);

    }

    @Override
    public List<Especialidad> buscarTodasLasEspecialidades() {

        return especialidadRepository.findAll();

    }

}

package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Especialidad;
import ap.grupo3.tpgrupo3.models.entity.Incidente;
import ap.grupo3.tpgrupo3.models.entity.Tecnico;
import ap.grupo3.tpgrupo3.models.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TecnicoServiceImpl implements TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Override
    public void altaTecnico(Tecnico tecnico) {
        tecnicoRepository.save(tecnico);
    }

    @Override
    public void bajaTecnico(Tecnico tecnico) {
        tecnicoRepository.delete(tecnico);
    }

    @Override
    public void updateTecnico(Tecnico tecnico) {
        tecnicoRepository.save(tecnico);
    }

    @Override
    public Tecnico buscarTecnico(Long id) {
        Optional<Tecnico> o = tecnicoRepository.findById(id);
        return o.orElse(null);
    }

    @Override
    public List<Tecnico> obtenerTodosLosTecnicos() {
        return tecnicoRepository.findAll();
    }

    @Override
    public void altaDeVariosTecnicos() {

        Especialidad especialidad1 = new Especialidad();
        especialidad1.setId(Long.parseLong("1"));

        Especialidad especialidad2 = new Especialidad();
        especialidad2.setId(Long.parseLong("2"));

        Especialidad especialidad3 = new Especialidad();
        especialidad3.setId(Long.parseLong("3"));

        Especialidad especialidad4 = new Especialidad();
        especialidad4.setId(Long.parseLong("4"));

        Especialidad especialidad5 = new Especialidad();
        especialidad5.setId(Long.parseLong("5"));

        List<Especialidad> especialidades1 = new ArrayList<>();
        especialidades1.add(especialidad3);
        especialidades1.add(especialidad4);
        especialidades1.add(especialidad5);
        Tecnico t1 = new Tecnico("Rodolfo Perez", "rodo@gmail.com", "132694", "SI", especialidades1, null);


        List<Especialidad> especialidades2 = new ArrayList<>();
        especialidades2.add(especialidad1);
        especialidades2.add(especialidad2);
        Tecnico t2 = new Tecnico("Lucía García", "lucy@gmail.com", "906769", "SI", especialidades2, null);

        List<Especialidad> especialidades3 = new ArrayList<>();
        especialidades3.add(especialidad1);
        especialidades3.add(especialidad2);
        especialidades3.add(especialidad3);
        especialidades3.add(especialidad4);
        especialidades3.add(especialidad5);
        Tecnico t3 = new Tecnico("Jorge Freyre", "jorge@gmail.com", "345176", "SI", especialidades3, null);

        tecnicoRepository.save(t1);
        tecnicoRepository.save(t2);
        tecnicoRepository.save(t3);

    }

    @Override
    public List<Incidente> obtenerIncidentesPendientes(Long id) {
        Tecnico tecnico;
        Optional<Tecnico> o = tecnicoRepository.findById(id);
        if(o.isPresent()){
            tecnico = (Tecnico)o.get();
        }else{
            return null;
        }
        List<Incidente> incidentes = tecnico.getIncidentes();
        return incidentes.stream().filter(i -> i.getResuelto() == 0).collect(Collectors.toList());

    }
}

package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Especialidad;
import ap.grupo3.tpgrupo3.models.entity.TipoProblema;
import ap.grupo3.tpgrupo3.models.repository.ProblemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TipoProblemaServiceImpl implements TipoProblemaService {

    @Autowired
    private ProblemaRepository problemaRepository;

    @Override
    public void altaDeTodosLosProblemas() {

        Especialidad e1 = new Especialidad();
        e1.setId(Long.parseLong("1"));
        Especialidad e2 = new Especialidad();
        e2.setId(Long.parseLong("2"));
        Especialidad e3 = new Especialidad();
        e3.setId(Long.parseLong("3"));
        Especialidad e4 = new Especialidad();
        e4.setId(Long.parseLong("4"));
        Especialidad e5 = new Especialidad();
        e5.setId(Long.parseLong("5"));

        TipoProblema problema1 = new TipoProblema();
        problema1.setNombre("UN_VIRUS");
        problema1.setTiempoEstimado(2);
        List<Especialidad> especialidadesQueResuelvenElProblema1 = new ArrayList<>();
        especialidadesQueResuelvenElProblema1.add(e3);
        especialidadesQueResuelvenElProblema1.add(e4);
        especialidadesQueResuelvenElProblema1.add(e5);
        problema1.setEspecialidadesQueResuelvenElProblema(especialidadesQueResuelvenElProblema1);
        problemaRepository.save(problema1);


        TipoProblema problema2 = new TipoProblema();
        problema2.setNombre("DRIVER_INCORRECTO");
        problema2.setTiempoEstimado(5);
        List<Especialidad> especialidadesQueResuelvenElProblema2 = new ArrayList<>();
        especialidadesQueResuelvenElProblema2.add(e1);
        especialidadesQueResuelvenElProblema2.add(e2);
        problema2.setEspecialidadesQueResuelvenElProblema(especialidadesQueResuelvenElProblema2);
        problemaRepository.save(problema2);

        TipoProblema problema3 = new TipoProblema();
        problema3.setNombre("FALTA_RAM");
        problema3.setTiempoEstimado(3);
        List<Especialidad> especialidadesQueResuelvenElProblema3 = new ArrayList<>();
        especialidadesQueResuelvenElProblema3.add(e3);
        especialidadesQueResuelvenElProblema3.add(e4);
        especialidadesQueResuelvenElProblema3.add(e5);
        problema3.setEspecialidadesQueResuelvenElProblema(especialidadesQueResuelvenElProblema3);
        problemaRepository.save(problema3);

        TipoProblema problema4 = new TipoProblema();
        problema4.setNombre("VENCIO_LICENCIA");
        problema4.setTiempoEstimado(7);
        List<Especialidad> especialidadesQueResuelvenElProblema4 = new ArrayList<>();
        especialidadesQueResuelvenElProblema4.add(e1);
        especialidadesQueResuelvenElProblema4.add(e2);
        problema4.setEspecialidadesQueResuelvenElProblema(especialidadesQueResuelvenElProblema4);
        problemaRepository.save(problema4);

    }

    @Override
    public List<TipoProblema> buscarTodosLosProblemas() {
        return problemaRepository.findAll();
    }

    @Override
    public List<TipoProblema> buscarProblemasPorNombre(String nombre) {
        return problemaRepository.findByNombre(nombre, TipoProblema.class);
    }

    @Override
    public TipoProblema buscarTipoProblema(Long id) {
        Optional<TipoProblema> o = problemaRepository.findById(id);
        return o.orElse(null);
    }

}

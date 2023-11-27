package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Cliente;
import ap.grupo3.tpgrupo3.models.entity.Tecnico;
import ap.grupo3.tpgrupo3.models.repository.ClienteRepository;
import ap.grupo3.tpgrupo3.models.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}

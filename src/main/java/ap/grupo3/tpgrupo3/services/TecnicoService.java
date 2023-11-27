package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Cliente;
import ap.grupo3.tpgrupo3.models.entity.Tecnico;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TecnicoService {

    public void altaTecnico(Tecnico tecnico);

    public void bajaTecnico(Tecnico tecnico);

    public void updateTecnico(Tecnico tecnico);

    public Tecnico buscarTecnico(Long id);

    public List<Tecnico> obtenerTodosLosTecnicos();

    public void altaDeVariosTecnicos();

}

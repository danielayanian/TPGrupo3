package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.TipoProblema;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TipoProblemaService {

    public void altaDeTodosLosProblemas();

    public List<TipoProblema> buscarTodosLosProblemas();

    public List<TipoProblema> buscarProblemasPorNombre(String nombre);

    public TipoProblema buscarTipoProblema(Long id);

}

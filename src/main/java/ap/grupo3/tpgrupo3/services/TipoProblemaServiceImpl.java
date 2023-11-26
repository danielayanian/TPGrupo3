package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.NombreProblema;
import ap.grupo3.tpgrupo3.models.entity.TipoProblema;
import ap.grupo3.tpgrupo3.models.repository.ProblemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoProblemaServiceImpl implements TipoProblemaService {

    @Autowired
    private ProblemaRepository problemaRepository;

    @Override
    public void altaDeTodosLosProblemas() {

        TipoProblema problema1 = new TipoProblema();
        problema1.setNombre(NombreProblema.UN_VIRUS);
        problema1.setTiempoEstimado(2);
        problemaRepository.save(problema1);

        TipoProblema problema2 = new TipoProblema();
        problema2.setNombre(NombreProblema.DRIVER_INCORRECTO);
        problema2.setTiempoEstimado(5);
        problemaRepository.save(problema2);

        TipoProblema problema3 = new TipoProblema();
        problema3.setNombre(NombreProblema.FALTA_RAM);
        problema3.setTiempoEstimado(3);
        problemaRepository.save(problema3);

        TipoProblema problema4 = new TipoProblema();
        problema4.setNombre(NombreProblema.VENCIO_LICENCIA);
        problema4.setTiempoEstimado(7);
        problemaRepository.save(problema4);

    }

}

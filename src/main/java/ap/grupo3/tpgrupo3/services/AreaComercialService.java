package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Cliente;
import ap.grupo3.tpgrupo3.models.entity.Tecnico;
import org.springframework.stereotype.Service;

@Service
public class AreaComercialService {

    public int altaCliente(Cliente cliente){
        return 1;
    }

    public int bajaCliente(Long cliente_id){
        return 1;
    }

    public int actualizacionCliente(Cliente cliente){
        return 1;
    }

    public int enviarEmailAlClientePorIncidenteSolucionado(){
        return 1;
    }

}

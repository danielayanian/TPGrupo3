package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Cliente;
import org.springframework.stereotype.Service;

@Service
public interface ClienteService {

    public void altaCliente(Cliente cliente);

    public void bajaCliente(Cliente cliente);

    public void updateCliente(Cliente cliente);

    public Cliente buscarCliente(Long id);

    public void altaDevariosClientes();

}

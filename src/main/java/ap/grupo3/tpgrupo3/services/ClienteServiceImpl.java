package ap.grupo3.tpgrupo3.services;

import ap.grupo3.tpgrupo3.models.entity.Cliente;
import ap.grupo3.tpgrupo3.models.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

package Pasteleria.PuntoNieve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Pasteleria.PuntoNieve.model.Cliente;
import Pasteleria.PuntoNieve.repository.ClienteRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(String run) {
        return clienteRepository.findById(run).orElse(null);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void delete(String run) {
        clienteRepository.deleteById(run);
    }
}

package Pasteleria.PuntoNieve.service;

import Pasteleria.PuntoNieve.model.Pedido;
import Pasteleria.PuntoNieve.repository.PedidoRepository;
 
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }
}

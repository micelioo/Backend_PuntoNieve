package Pasteleria.PuntoNieve.service;

import Pasteleria.PuntoNieve.model.DetallePedido;
import Pasteleria.PuntoNieve.repository.DetallePedidoRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public List<DetallePedido> findAll() {
        return detallePedidoRepository.findAll();
    }

    public DetallePedido findById(Long id) {
        return detallePedidoRepository.findById(id).orElse(null);
    }

    public DetallePedido save(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    public void delete(Long id) {
        detallePedidoRepository.deleteById(id);
    }
} 

package Pasteleria.PuntoNieve.controller;

import Pasteleria.PuntoNieve.model.DetallePedido;
import Pasteleria.PuntoNieve.service.DetallePedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalle-pedido")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping
    public ResponseEntity<List<DetallePedido>> listar() {
        List<DetallePedido> lista = detallePedidoService.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<DetallePedido> guardar(@RequestBody DetallePedido detalle) {
        DetallePedido nuevo = detallePedidoService.save(detalle);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> buscar(@PathVariable Long id) {
        try {
            DetallePedido encontrado = detallePedidoService.findById(id);
            return ResponseEntity.ok(encontrado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedido> actualizar(@PathVariable Long id, @RequestBody DetallePedido detalle) {
        try {
            DetallePedido existente = detallePedidoService.findById(id);

            existente.setIdDetallePedido(id);
            existente.setCantidad(detalle.getCantidad());
            existente.setPrecioUnitario(detalle.getPrecioUnitario());

            detallePedidoService.save(existente);
            return ResponseEntity.ok(existente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            detallePedidoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

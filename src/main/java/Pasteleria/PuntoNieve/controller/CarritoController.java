package Pasteleria.PuntoNieve.controller;

import Pasteleria.PuntoNieve.model.Carrito;
import Pasteleria.PuntoNieve.service.CarritoService;

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
@RequestMapping("/api/v1/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public ResponseEntity<List<Carrito>> listar() {
        List<Carrito> lista = carritoService.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Carrito> guardar(@RequestBody Carrito carrito) {
        Carrito nuevo = carritoService.save(carrito);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> buscar(@PathVariable Long id) {
        try {
            Carrito encontrado = carritoService.findById(id);
            return ResponseEntity.ok(encontrado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrito> actualizar(@PathVariable Long id, @RequestBody Carrito carrito) {
        try {
            Carrito existente = carritoService.findById(id);

            existente.setIdCarrito(id);
            existente.setCantidad(carrito.getCantidad());
            existente.setPrecioUnitario(carrito.getPrecioUnitario());

            carritoService.save(existente);
            return ResponseEntity.ok(existente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            carritoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

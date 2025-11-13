package Pasteleria.PuntoNieve.controller;

import Pasteleria.PuntoNieve.model.Pedido;
import Pasteleria.PuntoNieve.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
@RequestMapping("/api/v1/pedido")
@Tag(name = "Pedido", description = "Operaciones relacionadas con pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @Operation(
            summary = "Listar todos los pedidos",
            description = "Devuelve una lista con todos los pedidos registrados"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pedidos encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Pedido.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No hay pedidos registrados"
            )
    })
    public ResponseEntity<List<Pedido>> listar() {
        List<Pedido> lista = pedidoService.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Operation(
            summary = "Crear un nuevo pedido",
            description = "Crea y guarda un nuevo pedido"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Pedido creado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Pedido.class)
                    )
            )
    })
    public ResponseEntity<Pedido> guardar(@RequestBody Pedido pedido) {
        Pedido nuevo = pedidoService.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar un pedido por id",
            description = "Obtiene un pedido según su id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pedido encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Pedido.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pedido no encontrado"
            )
    })
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
        try {
            Pedido encontrado = pedidoService.findById(id);
            return ResponseEntity.ok(encontrado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un pedido",
            description = "Actualiza un pedido existente por su id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pedido actualizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Pedido.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pedido no encontrado"
            )
    })
    public ResponseEntity<Pedido> actualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        try {
            Pedido existente = pedidoService.findById(id);

            // Aquí asumo que tu entidad Pedido tiene setIdPedido.
            // Si el nombre del campo es distinto, solo cambia este setter.
            existente.setIdPedido(id); 
            // Si quieres actualizar campos específicos, puedes agregarlos acá,
            // por ejemplo:
            // existente.setFechaPedido(pedido.getFechaPedido());
            // existente.setEstado(pedido.getEstado());
            // existente.setTotal(pedido.getTotal());
            // etc.

            Pedido actualizado = pedidoService.save(existente);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un pedido",
            description = "Elimina un pedido por id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Pedido eliminado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pedido no encontrado"
            )
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            pedidoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

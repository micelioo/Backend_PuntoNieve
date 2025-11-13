package Pasteleria.PuntoNieve.controller;

import Pasteleria.PuntoNieve.model.Carrito;
import Pasteleria.PuntoNieve.service.CarritoService;

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
@RequestMapping("/api/v1/carrito")
@Tag(name = "Carrito", description = "Operaciones relacionadas con el carrito de compras")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    @Operation(
            summary = "Listar todos los elementos del carrito",
            description = "Devuelve una lista con todos los registros del carrito"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Productos del carrito encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Carrito.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "El carrito está vacío"
            )
    })
    public ResponseEntity<List<Carrito>> listar() {
        List<Carrito> lista = carritoService.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Operation(
            summary = "Agregar un nuevo producto al carrito",
            description = "Crea y guarda un nuevo registro en el carrito"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Producto agregado al carrito",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Carrito.class)
                    )
            )
    })
    public ResponseEntity<Carrito> guardar(@RequestBody Carrito carrito) {
        Carrito nuevo = carritoService.save(carrito);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar un producto del carrito por id",
            description = "Obtiene un producto del carrito según su id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Producto del carrito encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Carrito.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto del carrito no encontrado"
            )
    })
    public ResponseEntity<Carrito> buscar(@PathVariable Long id) {
        try {
            Carrito encontrado = carritoService.findById(id);
            return ResponseEntity.ok(encontrado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un producto del carrito",
            description = "Actualiza un producto del carrito existente por su id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Producto del carrito actualizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Carrito.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto del carrito no encontrado"
            )
    })
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
    @Operation(
            summary = "Eliminar un elemento del carrito",
            description = "Elimina un elemento del carrito por id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Producto del carrito eliminado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto del carrito no encontrado"
            )
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            carritoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

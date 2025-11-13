package Pasteleria.PuntoNieve.controller;

import Pasteleria.PuntoNieve.model.Cliente;
import Pasteleria.PuntoNieve.service.ClienteService;

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
@RequestMapping("/api/v1/clientes")
@Tag(name = "Cliente", description = "Operaciones relacionadas con clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(
            summary = "Listar todos los clientes",
            description = "Devuelve una lista con todos los clientes registrados"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Clientes encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No hay clientes registrados"
            )
    })
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> lista = clienteService.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Operation(
            summary = "Crear un nuevo cliente",
            description = "Crea y guarda un nuevo cliente"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Cliente creado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)
                    )
            )
    })
    public ResponseEntity<Cliente> guardar(@RequestBody Cliente cliente) {
        Cliente nuevo = clienteService.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @GetMapping("/{run}")
    @Operation(
            summary = "Buscar un cliente por run",
            description = "Obtiene un cliente según su run"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cliente encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente no encontrado"
            )
    })
    public ResponseEntity<Cliente> buscar(@PathVariable String run) {
        try {
            Cliente encontrado = clienteService.findById(run);
            return ResponseEntity.ok(encontrado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{run}")
    @Operation(
            summary = "Actualizar un cliente",
            description = "Actualiza un cliente existente por su run"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cliente actualizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente no encontrado"
            )
    })
    public ResponseEntity<Cliente> actualizar(@PathVariable String run, @RequestBody Cliente datos) {
        try {
            Cliente existente = clienteService.findById(run);

            existente.setRun(run);
            existente.setDv(datos.getDv());
            existente.setNombres(datos.getNombres());
            existente.setApe1(datos.getApe1());
            existente.setApe2(datos.getApe2());
            existente.setFecnac(datos.getFecnac());
            existente.setCorreo(datos.getCorreo());

            Cliente actualizado = clienteService.save(existente);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{run}")
    @Operation(
            summary = "Eliminar un cliente",
            description = "Elimina un cliente por su run"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Cliente eliminado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente no encontrado"
            )
    })
    public ResponseEntity<?> eliminar(@PathVariable String run) {
        try {
            clienteService.delete(run);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

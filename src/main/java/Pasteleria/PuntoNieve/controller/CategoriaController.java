package Pasteleria.PuntoNieve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Pasteleria.PuntoNieve.model.Categoria;
import Pasteleria.PuntoNieve.service.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/categorias")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Categoría", description = "Operaciones relacionadas con categorías de productos")

public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @Operation(
            summary = "Listar todas las categorías",
            description = "Devuelve una lista de las categorías disponibles"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Categorías encontradas",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No hay categorías"
            )
    })
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> categorias = categoriaService.findAll();
        if (categorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar una categoría por id",
            description = "Obtiene una categoría según su id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Categoría encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Categoría no encontrada"
            )
    })
    public ResponseEntity<Categoria> buscar(@PathVariable Long id) {
        try {
            Categoria categoria = categoriaService.findById(id);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(
            summary = "Crear una nueva categoría",
            description = "Crea y guarda una nueva categoría"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Categoría creada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class)
                    )
            )
    })
    public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria) {
        Categoria nuevo = categoriaService.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar una categoría",
            description = "Actualiza una categoría existente por su id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Categoría actualizada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Categoría no encontrada"
            )
    })
    public ResponseEntity<Categoria> actualizar(@PathVariable Long id, @RequestBody Categoria datos) {
        try {
            Categoria existente = categoriaService.findById(id);
            existente.setDescripcion(datos.getDescripcion());

            Categoria actualizado = categoriaService.save(existente);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar una categoría",
            description = "Elimina una categoría por id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Categoría eliminada"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Categoría no encontrada"
            )
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            categoriaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

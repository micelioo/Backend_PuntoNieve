package Pasteleria.PuntoNieve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Pasteleria.PuntoNieve.model.Categoria;
import Pasteleria.PuntoNieve.model.Producto;
import Pasteleria.PuntoNieve.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @Operation(
            summary = "Listar todas las categorias",
            description = "Devuelve una lista de las categorias disponibles"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Categorias encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No hay categorias"
            )
    })
    public ResponseEntity<List<Categoria>> listar(){
        List<Categoria> categorias = categoriaService.findAll();
        if(categorias.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar un categoria por id",
            description = "Obtiene una categoria según su id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Categoria encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class)
                    )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Categoria no encontrado"
            )
    })
    public ResponseEntity<Categoria> buscar(@PathVariable Long id){
        try{
            Categoria categoria = categoriaService.findById(id);
            return ResponseEntity.ok(categoria);

        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(
            summary = "Crear una nueva categoria",
            description = "Crea y guarda una nueva categoria"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Categoria creada",
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
            summary = "Actualizar una categoria",
            description = "Actualiza una categoria existente por su id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Categoria actualizada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto no encontrado"
            )
    })
    public ResponseEntity<Categoria> actualizar(@PathVariable Long id, @RequestBody Categoria datos) {
        Categoria existente = categoriaService.findById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setDescripcion(null);
        

        Categoria actualizado = categoriaService.save(existente);
        return ResponseEntity.ok(actualizado);
    }

}

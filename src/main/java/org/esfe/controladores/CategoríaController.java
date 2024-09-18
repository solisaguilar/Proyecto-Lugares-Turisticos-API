package org.esfe.controladores;

import org.esfe.dtos.categoria.CategoríaGuardar;
import org.esfe.dtos.categoria.CategoríaModificar;
import org.esfe.dtos.categoria.CategoríaSalida;
import org.esfe.servicios.implementaciones.CategoríaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoríaController {
    @Autowired
    private CategoríaService categoriaService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<Page<CategoríaSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<CategoríaSalida> categorias = categoriaService.obtenerTodosPaginados(pageable);
        if(categorias.hasContent()){
            return ResponseEntity.ok(categorias);
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/lista")
    public ResponseEntity<List<CategoríaSalida>> mostrarTodos(){
        List<CategoríaSalida> categorias = categoriaService.obtenerTodos();
        if(!categorias.isEmpty()){
            return ResponseEntity.ok(categorias);
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<CategoríaSalida> buscarPorId(@PathVariable Integer id){
        CategoríaSalida categoria = categoriaService.obtenerPorId(id);

        if(categoria != null){
            return ResponseEntity.ok(categoria);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoríaSalida> crear(@RequestBody CategoríaGuardar categoriaGuardar){
        CategoríaSalida categoria = categoriaService.crear(categoriaGuardar);
        return ResponseEntity.ok(categoria);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoríaSalida> editar(@PathVariable Integer id, @RequestBody CategoríaModificar categoriaModificar){
        CategoríaSalida categoria = categoriaService.editar(categoriaModificar);
        return ResponseEntity.ok(categoria);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        categoriaService.eliminarPorId(id);
        return ResponseEntity.ok("Categoría eliminada correctamente");
    }
}

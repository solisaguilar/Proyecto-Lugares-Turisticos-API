package org.esfe.controladores;

import org.esfe.dtos.resena.ResenaGuardar;
import org.esfe.dtos.resena.ResenaModificar;
import org.esfe.dtos.resena.ResenaSalida;
import org.esfe.servicios.interfaces.IResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reseñas")
public class ResenaController {
    @Autowired
    private IResenaService resenaService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<Page<ResenaSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<ResenaSalida> resenas = resenaService.obtenerTodosPaginados(pageable);
        if(resenas.hasContent()){
            return ResponseEntity.ok(resenas);
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/lista")
    public ResponseEntity<List<ResenaSalida>> mostrarTodos(){
        List<ResenaSalida> resenas = resenaService.obtenerTodos();
        if(!resenas.isEmpty()){
            return ResponseEntity.ok(resenas);
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ResenaSalida> buscarPorId(@PathVariable Integer id){
        ResenaSalida resena = resenaService.obtenerPorId(id);

        if(resena != null){
            return ResponseEntity.ok(resena);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ResenaSalida> crear(@RequestBody ResenaGuardar resenaGuardar){
        ResenaSalida resena = resenaService.crear(resenaGuardar);
        return ResponseEntity.ok(resena);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ResenaSalida> editar(@PathVariable Integer id, @RequestBody ResenaModificar resenaModificar){
        ResenaSalida resena = resenaService.editar(resenaModificar);
        return ResponseEntity.ok(resena);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        resenaService.eliminarPorId(id);
        return ResponseEntity.ok("Reseña eliminada correctamente");
    }
}


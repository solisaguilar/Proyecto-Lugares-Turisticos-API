package org.esfe.controladores;

import org.esfe.dtos.lugar.LugarGuardar;
import org.esfe.dtos.lugar.LugarModificar;
import org.esfe.dtos.lugar.LugarSalida;
import org.esfe.servicios.interfaces.ILugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lugares")
public class LugarController {
    @Autowired
    private ILugarService lugarService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<Page<LugarSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<LugarSalida> lugares = lugarService.obtenerTodosPaginados(pageable);
        if(lugares.hasContent()){
            return ResponseEntity.ok(lugares);
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/lista")
    public ResponseEntity<List<LugarSalida>> mostrarTodos(){
        List<LugarSalida> lugares = lugarService.obtenerTodos();
        if(!lugares.isEmpty()){
            return ResponseEntity.ok(lugares);
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<LugarSalida> buscarPorId(@PathVariable Integer id){
        LugarSalida lugar = lugarService.obtenerPorId(id);

        if(lugar != null){
            return ResponseEntity.ok(lugar);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<LugarSalida> crear(@RequestBody LugarGuardar lugarGuardar){
        LugarSalida lugar = lugarService.crear(lugarGuardar);
        return ResponseEntity.ok(lugar);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<LugarSalida> editar(@PathVariable Integer id, @RequestBody LugarModificar lugarModificar){
        LugarSalida lugar = lugarService.editar(lugarModificar);
        return ResponseEntity.ok(lugar);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        lugarService.eliminarPorId(id);
        return ResponseEntity.ok("Lugar eliminado correctamente");
    }
}

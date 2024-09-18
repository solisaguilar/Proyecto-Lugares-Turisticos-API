package org.esfe.controladores;

import org.esfe.dtos.resena.ResenaGuardar;
import org.esfe.dtos.resena.ResenaModificar;
import org.esfe.dtos.resena.ResenaSalida;
import org.esfe.dtos.reserva.ReservaGuardar;
import org.esfe.dtos.reserva.ReservaModificar;
import org.esfe.dtos.reserva.ReservaSalida;
import org.esfe.servicios.interfaces.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    @Autowired
    private IReservaService reservaService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<Page<ReservaSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<ReservaSalida> reservas = reservaService.obtenerTodosPaginados(pageable);
        if(reservas.hasContent()){
            return ResponseEntity.ok(reservas);
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/lista")
    public ResponseEntity<List<ReservaSalida>> mostrarTodos(){
        List<ReservaSalida> reservas = reservaService.obtenerTodos();
        if(!reservas.isEmpty()){
            return ResponseEntity.ok(reservas);
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ReservaSalida> buscarPorId(@PathVariable Integer id){
        ReservaSalida reserva = reservaService.obtenerPorId(id);

        if(reserva != null){
            return ResponseEntity.ok(reserva);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ReservaSalida> crear(@RequestBody ReservaGuardar reservaGuardar){
        ReservaSalida reserva = reservaService.crear(reservaGuardar);
        return ResponseEntity.ok(reserva);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ReservaSalida> editar(@PathVariable Integer id, @RequestBody ReservaModificar reservaModificar){
        ReservaSalida reserva = reservaService.editar(reservaModificar);
        return ResponseEntity.ok(reserva);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        reservaService.eliminarPorId(id);
        return ResponseEntity.ok("Reserva eliminada correctamente");
    }
}


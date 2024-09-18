package org.esfe.servicios.interfaces;

import org.esfe.dtos.reserva.ReservaGuardar;
import org.esfe.dtos.reserva.ReservaModificar;
import org.esfe.dtos.reserva.ReservaSalida;
import org.esfe.modelos.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IReservaService {
    List<ReservaSalida> obtenerTodos();

    Page<ReservaSalida> obtenerTodosPaginados(Pageable pageable);

    ReservaSalida obtenerPorId(Integer id);

    ReservaSalida crear(ReservaGuardar reservaGuardar);

    ReservaSalida editar(ReservaModificar reservaModificar);

    void eliminarPorId(Integer id);
}

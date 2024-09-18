package org.esfe.servicios.interfaces;

import org.esfe.dtos.resena.ResenaGuardar;
import org.esfe.dtos.resena.ResenaModificar;
import org.esfe.dtos.resena.ResenaSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IResenaService {
    List<ResenaSalida> obtenerTodos();

    Page<ResenaSalida> obtenerTodosPaginados(Pageable pageable);

    ResenaSalida obtenerPorId(Integer id);

    ResenaSalida crear(ResenaGuardar resenaGuardar);

    ResenaSalida editar(ResenaModificar resenaModificar);

    void eliminarPorId(Integer id);
}

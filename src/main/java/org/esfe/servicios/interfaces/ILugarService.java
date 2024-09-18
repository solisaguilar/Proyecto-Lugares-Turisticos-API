package org.esfe.servicios.interfaces;

import org.esfe.dtos.lugar.LugarGuardar;
import org.esfe.dtos.lugar.LugarModificar;
import org.esfe.dtos.lugar.LugarSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILugarService {
    List<LugarSalida> obtenerTodos();

    Page<LugarSalida> obtenerTodosPaginados(Pageable pageable);

    LugarSalida obtenerPorId(Integer id);

    LugarSalida crear(LugarGuardar lugarGuardar);

    LugarSalida editar(LugarModificar lugarModificar);

    void eliminarPorId(Integer id);
}


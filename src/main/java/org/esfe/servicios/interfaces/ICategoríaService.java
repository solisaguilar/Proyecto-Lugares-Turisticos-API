package org.esfe.servicios.interfaces;

import org.esfe.dtos.categoria.CategoríaGuardar;
import org.esfe.dtos.categoria.CategoríaModificar;
import org.esfe.dtos.categoria.CategoríaSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ICategoríaService {
    List<CategoríaSalida> obtenerTodos();

    Page<CategoríaSalida> obtenerTodosPaginados(Pageable pageable);

    CategoríaSalida obtenerPorId(Integer id);

    CategoríaSalida crear(CategoríaGuardar categoriaGuardar);

    CategoríaSalida editar(CategoríaModificar categoriaModificar);

    void eliminarPorId(Integer id);
}


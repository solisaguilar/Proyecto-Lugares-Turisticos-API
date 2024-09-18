package org.esfe.dtos.lugar;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LugarSalida implements Serializable {
    private Integer id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private Integer categoriaId;
}

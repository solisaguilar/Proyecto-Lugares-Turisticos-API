package org.esfe.dtos.resena;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ResenaSalida implements Serializable {
    private Integer id;
    private String comentario;
    private LocalDate fechaResena;
    private Integer lugarId;
}

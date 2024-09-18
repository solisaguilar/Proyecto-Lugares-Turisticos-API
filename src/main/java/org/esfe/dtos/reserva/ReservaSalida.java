package org.esfe.dtos.reserva;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ReservaSalida implements Serializable {
    private Integer id;
    private LocalDate fechaReserva;
    private String nombreCliente;
}

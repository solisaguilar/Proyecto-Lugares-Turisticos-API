package org.esfe.dtos.reserva;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ReservaGuardar implements Serializable {
    private LocalDate fechaReserva;
    private String nombreCliente;
}

package org.esfe.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "reseñas")
public class Resena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String comentario;

    private LocalDate fechaResena;

    @ManyToOne
    @JoinColumn(name = "lugar_id")
    private Lugar lugar;
}


package org.esfe.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "lugares")
public class Lugar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String descripcion;

    private String ubicacion;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoría categoria;

    @OneToMany(mappedBy = "lugar")
    private List<Resena> resenas;

    @OneToMany(mappedBy = "lugar")
    private List<Reserva> reservas;
}

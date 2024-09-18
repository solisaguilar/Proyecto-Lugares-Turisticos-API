package org.esfe.repositorios;

import org.esfe.modelos.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservaRepository extends JpaRepository<Reserva, Integer> {
}

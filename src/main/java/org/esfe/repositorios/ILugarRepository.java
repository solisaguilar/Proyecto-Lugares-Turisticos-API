package org.esfe.repositorios;

import org.esfe.modelos.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILugarRepository extends JpaRepository<Lugar, Integer> {
}

package org.esfe.repositorios;

import org.esfe.modelos.Categoría;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoríaRepository extends JpaRepository<Categoría, Integer> {
}

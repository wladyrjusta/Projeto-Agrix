package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a tabela farms no banco de dados.
 */
@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

}

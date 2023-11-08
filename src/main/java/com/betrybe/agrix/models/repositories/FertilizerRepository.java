package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.controllers.dtos.CropResponseDto;
import com.betrybe.agrix.controllers.dtos.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repositório para a tabela fertilizers no banco de dados.
 */
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {

  String jpql = "SELECT new com.betrybe.agrix.controllers.dtos.FertilizerDto("
      + "f.id, "
      + "f.name, "
      + "f.brand, "
      + "f.composition) FROM Fertilizer f";

  @Query(jpql)
  List<FertilizerDto> findAllFertilizersAsDto();
}

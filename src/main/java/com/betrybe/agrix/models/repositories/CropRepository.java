package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.controllers.dtos.CropResponseDto;
import com.betrybe.agrix.models.entities.Crop;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a tabela crops no banco de dados.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
  String jpql = "SELECT new com.betrybe.agrix.controllers.dtos.CropResponseDto("
      + "c.id, "
      + "c.farm.id, "
      + "c.name, "
      + "c.plantedArea, "
      + "c.plantedDate, "
      + "c.harvestDate) FROM Crop c";

  @Query(jpql)
  List<CropResponseDto> findAllCropsAsDto();

}

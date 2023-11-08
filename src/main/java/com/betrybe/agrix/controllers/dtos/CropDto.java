package com.betrybe.agrix.controllers.dtos;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;

/**
 * Representa a entidade Crop em requisições /POST.
 */
public record CropDto(Long id, String name, Double plantedArea,
                      LocalDate plantedDate, LocalDate harvestDate
) {

  /**
   * Método retorna um Crop.
   *
   * @return retorna uma instância de Crop com os atributos do dto.
   */
  public Crop toCrop() {
    return new Crop(
        id, null, name, plantedArea, plantedDate, harvestDate,
        null
    );
  }
}

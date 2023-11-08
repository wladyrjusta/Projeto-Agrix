package com.betrybe.agrix.controllers.dtos;

import com.betrybe.agrix.models.entities.Farm;

/**
 * Representa a entidade Farm em requisições /POST.
 */
public record FarmDto(
    Long id, String name, Double size
) {

  /**
   * Método retorna um Farm.
   *
   * @return retorna uma instância de farm com os atributos do dto.
   */
  public Farm toFarm() {
    return new Farm(
        id, name, size, null
    );
  }
}

package com.betrybe.agrix.controllers.dtos;

import java.time.LocalDate;

/**
 * Representa a entidade Crop em resposta a requisições /POST.
 */
public record CropResponseDto(
    Long id, Long farmId, String name,
    Double plantedArea, LocalDate plantedDate, LocalDate harvestDate
) {

}

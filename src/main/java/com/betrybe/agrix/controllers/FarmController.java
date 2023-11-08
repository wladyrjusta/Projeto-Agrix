package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dtos.CropDto;
import com.betrybe.agrix.controllers.dtos.CropResponseDto;
import com.betrybe.agrix.controllers.dtos.FarmDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller rota /farms.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  /**
   * Constructor da entidade Crop.
   *
   * @param farmService representa o service da entidade farm e rota /farms.
   */
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  @PostMapping()
  public ResponseEntity<Farm> createFarm(@RequestBody FarmDto farmDto) {
    Farm newFarm = farmService.insertFarm(farmDto.toFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  /**
   * Método busca todas as farms listadas no banco de dados.
   *
   * @return retorna uma lista de farms.
   */
  @GetMapping()
  public ResponseEntity<List<Farm>> getAllFarms() {
    List<Farm> farmList = farmService.getAllFarms();

    return ResponseEntity.ok(farmList);
  }

  /**
   * Método busca uma farm por id.
   *
   * @param farmId representa o id da entidade farm.
   */
  @GetMapping("/{farmId}")
  public ResponseEntity<Farm> getFarmById(@PathVariable Long farmId) {
    Farm farmById = farmService.getFarmById(farmId);
    return ResponseEntity.ok(farmById);
  }

  /**
   * Método busca uma farm por id.
   * Se farm existente, salva uma plantação e associa a essa farm.
   *
   * @param farmId representa o id da entidade farm.
   * @param cropDto representa a plantação a ser criada.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropResponseDto> createCrop(
      @PathVariable Long farmId, @RequestBody CropDto cropDto
  ) {
    Long cropId = farmService.insertCrop(farmId, cropDto);
    CropResponseDto newCrop = new CropResponseDto(
        cropId,
        farmId,
        cropDto.name(),
        cropDto.plantedArea(),
        cropDto.plantedDate(),
        cropDto.harvestDate()
    );
    return ResponseEntity.status(HttpStatus.CREATED).body(newCrop);
  }

  /**
   * Recupera a lista de cultivos de uma fazenda específica com base no ID da fazenda.
   *
   * @param farmId O ID da fazenda para a qual se deseja obter os cultivos.
   * @return Uma lista de CropResponseDto representando os cultivos da fazenda.
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropResponseDto>> getFarmsCrops(@PathVariable Long farmId) {
    List<Crop> cropList = farmService.getFarmsCrops(farmId);
    List<CropResponseDto> cropResponseList = cropList.stream()
        .map((crop) ->
            new CropResponseDto(
                crop.getId(),
                crop.getFarm().getId(),
                crop.getName(),
                crop.getPlantedArea(),
                crop.getPlantedDate(),
                crop.getHarvestDate()
            )).toList();
    return ResponseEntity.ok(cropResponseList);
  }
}


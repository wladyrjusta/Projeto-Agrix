package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dtos.CropResponseDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.CropService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Esta classe atua como um controlador para manipular.
 * Solicitações relacionadas a culturas (crops) no sistema Agrix.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  /**
   * Construtor da classe CropController.
   *
   * @param cropService O serviço de culturas a ser injetado.
   */
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Manipula uma solicitação GET para recuperar todas as culturas cadastradas no sistema.
   *
   * @return Uma lista de objetos do tipo CropResponseDto representando as culturas.
   */
  @GetMapping()
  public ResponseEntity<List<CropResponseDto>> getAllCrops() {
    List<CropResponseDto> cropResponseDtoList = cropService.getAllCrops();

    return ResponseEntity.ok(cropResponseDtoList);
  }

  /**
   * Recupera informações sobre uma cultura (crop) específica pelo seu ID.
   *
   * @param cropId O ID da cultura a ser recuperada.
   * @return Uma CropResponseDto que representa a cultura solicitada.
   */
  @GetMapping("/{cropId}")
  public ResponseEntity<CropResponseDto> getCropById(@PathVariable Long cropId) {
    Crop cropById = cropService.getCropById(cropId);
    CropResponseDto cropResponseDto = new CropResponseDto(
        cropById.getId(),
        cropById.getFarm().getId(),
        cropById.getName(),
        cropById.getPlantedArea(),
        cropById.getPlantedDate(),
        cropById.getHarvestDate()
    );
    return ResponseEntity.ok(cropResponseDto);
  }

  /**
   * Retorna uma lista de safras com base em um intervalo de datas de colheita.
   *
   * @param start A data de início do intervalo no formato "yyyy-MM-dd".
   * @param end A data de término do intervalo no formato "yyyy-MM-dd".
   * @return Uma lista de objetos CropResponseDto.
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropResponseDto>> getCropsByHarvestDateInterval(
      @RequestParam String start, @RequestParam String end
  ) {
    List<CropResponseDto> cropList = cropService.getAllCrops();

    List<CropResponseDto> filteredCropList = cropList.stream()
        .filter((crop ->
            cropService.verifyHarvestInterval(start, end, crop.harvestDate())))
        .toList();

    return ResponseEntity.ok(filteredCropList);
  }

  /**
   * Associa um fertilizante a uma plantação existente.
   *
   * @param cropId      ID da plantação.
   * @param fertilizerId ID do fertilizante.
   * @return Resposta com mensagem de sucesso.
   */
  @PostMapping("{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateFertilizerAndCrop(
      @PathVariable Long cropId,
      @PathVariable Long fertilizerId
  ) {
    cropService.associateFertilizerAndCrop(
        cropId, fertilizerId
    );

    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * Obtém a lista de fertilizantes associados a uma plantação pelo ID da plantação.
   *
   * @param cropId ID da plantação para a qual deseja-se obter os fertilizantes.
   * @return Uma resposta contendo a lista de fertilizantes associados.
   */
  @GetMapping("{cropId}/fertilizers")
  public ResponseEntity<List<Fertilizer>> getCropsFertilizers(
      @PathVariable Long cropId
  ) {
    List<Fertilizer> fertilizerList = cropService
        .getCropById(cropId).getFertilizers();

    return ResponseEntity.ok(fertilizerList);
  }
}


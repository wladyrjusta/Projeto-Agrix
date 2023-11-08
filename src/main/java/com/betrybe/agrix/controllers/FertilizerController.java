package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dtos.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller rota /fertilizers.
 */

@RestController
@RequestMapping("fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  /**
   * Constructor da entidade Fertilizer.
   *
   * @param fertilizerService representa o service da entidade farm e rota /fertilizers.
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Cria um novo registro de fertilizante com base nos dados fornecidos.
   *
   * @param fertilizerDto Os dados do fertilizante a ser criado.
   * @return ResponseEntity contendo o fertilizante recém-criado.
   */
  @PostMapping()
  public ResponseEntity<FertilizerDto> createFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    Long fertilizerId = fertilizerService.insertFertilizer(fertilizerDto);
    FertilizerDto fertilizerDtoResponse = new FertilizerDto(
        fertilizerId,
        fertilizerDto.name(),
        fertilizerDto.brand(),
        fertilizerDto.composition()
    );

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(fertilizerDtoResponse);
  }

  /**
   * Manipula uma solicitação GET para recuperar todos os fertilizantes.
   *
   * @return Uma lista de objetos do tipo FertilizerDto representando fertilizantes.
   */
  @GetMapping()
  public ResponseEntity<List<FertilizerDto>> getAllFertilizers() {
    List<FertilizerDto> fertilizerDtoList = fertilizerService.getAllFertilizers();

    return ResponseEntity.ok(fertilizerDtoList);
  }

  /**
   * Recupera informações sobre um fertilizante específico pelo seu ID.
   *
   * @param fertilizerId O ID do fertilizante a ser recuperado.
   * @return Uma fertilizerDto que representa o fertilizante solicitado.
   */
  @GetMapping("/{fertilizerId}")
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable Long fertilizerId) {
    Fertilizer fertilizerById = fertilizerService.getFertilizerById(fertilizerId);

    FertilizerDto fertilizerDto = new FertilizerDto(
        fertilizerById.getId(),
        fertilizerById.getName(),
        fertilizerById.getBrand(),
        fertilizerById.getComposition()
    );

    return ResponseEntity.ok(fertilizerDto);
  }
}

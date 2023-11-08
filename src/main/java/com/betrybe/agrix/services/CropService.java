package com.betrybe.agrix.services;

import com.betrybe.agrix.controllers.dtos.CropResponseDto;
import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Esta classe fornece serviços relacionados a culturas (crops) no sistema Agrix.
 * Ela permite recuperar informações sobre todas as culturas cadastradas.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;

  private final FertilizerRepository fertilizerRepository;

  /**
   * Construtor da classe CropService.
   *
   * @param cropRepository O repositório de dados de culturas a ser injetado.
   */
  @Autowired
  public CropService(
      CropRepository cropRepository,
      FertilizerRepository fertilizerRepository
  ) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Recupera uma lista de todas as culturas cadastradas no sistema.
   *
   * @return Uma lista de objetos Crop que representam as culturas cadastradas.
   */
  public List<CropResponseDto> getAllCrops() {
    return cropRepository.findAllCropsAsDto();
  }

  /**
   * Recupera informações sobre uma cultura (crop) pelo seu ID.
   *
   * @param cropId O ID da cultura a ser recuperada.
   * @return Um objeto do tipo Crop que representa a cultura solicitada, se encontrada.
   * @throws CropNotFoundException Se a cultura não for encontrada com o ID especificado.
   */
  public Crop getCropById(Long cropId) {
    Optional<Crop> optionalCrop = cropRepository.findById(cropId);

    if (optionalCrop.isEmpty()) {
      throw new CropNotFoundException();
    }
    return optionalCrop.get();
  }

  /**
   * Verifica se a data fornecida está dentro.
   * De um intervalo de datas especificado.
   *
   * @param start A data de início do intervalo no formato "yyyy-MM-dd".
   * @param end A data de término do intervalo no formato "yyyy-MM-dd".
   * @param myDate A data a ser verificada.
   * @return true se a data está dentro do intervalo, caso contrário, false.
   */
  public boolean verifyHarvestInterval(
      String start, String end, LocalDate myDate
  ) {
    LocalDate startDate = LocalDate.parse(start);
    LocalDate endDate = LocalDate.parse(end);
    return !myDate.isBefore(startDate) && !myDate.isAfter(endDate);
  }


  /**
   * Associa um fertilizante a uma plantação existente.
   *
   * @param cropId       ID da plantação.
   * @param fertilizerId ID do fertilizante.
   * @throws CropNotFoundException      Se a plantação não for encontrada.
   * @throws FertilizerNotFoundException Se o fertilizante não for encontrado.
   */
  public void associateFertilizerAndCrop(
      Long cropId, Long fertilizerId
  ) {
    Crop crop = cropRepository.findById(cropId)
        .orElseThrow(CropNotFoundException::new);
    Fertilizer fertilizer = fertilizerRepository.findById(fertilizerId)
        .orElseThrow(FertilizerNotFoundException::new);

    crop.getFertilizers().add(fertilizer);
    fertilizer.getCrops().add(crop);
    cropRepository.save(crop);
    fertilizerRepository.save(fertilizer);
  }
}


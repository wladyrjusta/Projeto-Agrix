package com.betrybe.agrix.services;

import com.betrybe.agrix.controllers.dtos.FertilizerDto;
import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service para a tabela fertilizers no banco de dados e rota /fertilizers.
 */
@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;

  /**
   * Constructor da entidade Fertilizer.
   *
   * @param fertilizerRepository representa o repository da entidade Fertilizer.
   */
  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Método cria um novo fertilizer.
   *
   * @param fertilizerDto representa o fertilizer da entidade Fertilizer.
   */
  public Long insertFertilizer(FertilizerDto fertilizerDto) {
    Fertilizer newFertilizer = fertilizerRepository.save(
        new Fertilizer(
            fertilizerDto.id(),
            fertilizerDto.name(),
            fertilizerDto.brand(),
            fertilizerDto.composition(),
            null
        )
    );
    return newFertilizer.getId();
  }

  /**
   * Recupera uma lista com todos os fertilizantes cadastrados no sistema.
   *
   * @return Uma lista de objetos Fertilizer.
   */
  public List<FertilizerDto> getAllFertilizers() {
    return fertilizerRepository.findAllFertilizersAsDto();
  }

  /**
   * Recupera informações sobre uma fertilizante ID.
   *
   * @param fertilizerId O ID da cultura a ser recuperada.
   * @return Um objeto do tipo Crop que representa a cultura solicitada, se encontrada.
   * @throws CropNotFoundException Se a cultura não for encontrada com o ID especificado.
   */
  public Fertilizer getFertilizerById(Long fertilizerId) {
    Optional<Fertilizer> fertilizerOptional = fertilizerRepository.findById(fertilizerId);

    if (fertilizerOptional.isEmpty()) {
      throw new FertilizerNotFoundException();
    }

    return fertilizerOptional.get();
  }
}

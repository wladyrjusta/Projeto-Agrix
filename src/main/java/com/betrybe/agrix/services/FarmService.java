package com.betrybe.agrix.services;

import com.betrybe.agrix.controllers.dtos.CropDto;
import com.betrybe.agrix.exceptions.FarmNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service para a tabela farms no banco de dados e rota /farms.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;


  /**
   * Constructor da entidade Crop.
   *
   * @param farmRepository representa o repository da entidade farm.
   * @param cropRepository representa o repository da entidade crop.
   */
  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  /**
   * Método busca uma farm por id.
   *
   * @param farmId representa o id da entidade farm.
   */
  public Farm getFarmById(Long farmId) {
    Optional<Farm> farmOptional = farmRepository.findById(farmId);

    if (farmOptional.isEmpty()) {
      throw new FarmNotFoundException();
    }
    return farmOptional.get();
  }

  /**
   * Método busca uma farm por id.
   * Se farm existente, salva uma plantação e associa a essa farm.
   *
   * @param farmId representa o id da entidade farm.
   * @param cropDto representa a plantação a ser salva.
   */
  public Long insertCrop(Long farmId, CropDto cropDto) {
    Optional<Farm> optionalFarm = farmRepository.findById(farmId);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    Crop newCrop = cropRepository.save(new Crop(
        cropDto.id(),
        optionalFarm.get(),
        cropDto.name(),
        cropDto.plantedArea(),
        cropDto.plantedDate(),
        cropDto.harvestDate(),
        null
    ));
    return newCrop.getId();
  }

  /**
   * Recupera a lista de cultivos associados a uma fazenda específica.
   *
   * @param farmId O identificador único da fazenda.
   * @return Uma lista de objetos Crop associados à fazenda especificada.
   * @throws FarmNotFoundException se a fazenda com o ID fornecido não for encontrada.
   */
  public List<Crop> getFarmsCrops(Long farmId) {
    Optional<Farm> optionalFarm = farmRepository.findById(farmId);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    return optionalFarm.get().getCrops();
  }
}

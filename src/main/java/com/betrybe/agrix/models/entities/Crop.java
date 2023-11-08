package com.betrybe.agrix.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/**
 * Entidade que representa a tabela crops no banco de dados.
 */
@Entity
@Table(name = "crops")
public class Crop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne()
  @JoinColumn(name = "farm_id")
  private Farm farm;
  private String name;
  @Column(name = "planted_area")
  private Double plantedArea;
  @Column(name = "planted_date")
  private LocalDate plantedDate;
  @Column(name = "harvest_date")
  private LocalDate harvestDate;
  @ManyToMany
  @JoinTable(
      name = "crop_fertilizer",
      joinColumns = @JoinColumn(name = "fertilizer_id"),
      inverseJoinColumns = @JoinColumn(name = "crop_id")
  )
  private List<Fertilizer> fertilizers;


  public Crop() {
  }

  /**
   * Constructor da entidade Crop.
   *
   * @param id representa a coluna id na entidade.
   * @param name representa a coluna nome na entidade.
   * @param plantedArea representa a planted_area nome na entidade.
   */
  public Crop(Long id, Farm farm, String name, Double plantedArea,
      LocalDate plantedDate, LocalDate harvestDate, List<Fertilizer> fertilizers) {
    this.id = id;
    this.farm = farm;
    this.name = name;
    this.plantedArea = plantedArea;
    this.plantedDate = plantedDate;
    this.harvestDate = harvestDate;
    this.fertilizers = fertilizers;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Double getPlantedArea() {
    return plantedArea;
  }

  public Farm getFarm() {
    return farm;
  }

  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public List<Fertilizer> getFertilizers() {
    return fertilizers;
  }

  public void setFertilizers(List<Fertilizer> fertilizers) {
    this.fertilizers = fertilizers;
  }

  public void setPlantedDate(LocalDate plantedDate) {
    this.plantedDate = plantedDate;
  }

  public void setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }

  public void setFarm(Farm farm) {
    this.farm = farm;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }
}

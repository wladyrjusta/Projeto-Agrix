package com.betrybe.agrix.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Entidade que representa a tabela crops no banco de dados.
 */
@Entity
@Table(name = "fertilizers")
public class Fertilizer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String name;
  String brand;
  String composition;
  @ManyToMany(mappedBy = "fertilizers")
  @JsonIgnore
  private List<Crop> crops;

  public Fertilizer() {
  }

  /**
   * Constructor da entidade Fertilizer.
   */
  public Fertilizer(Long id, String name, String brand, String composition, List<Crop> crops) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.composition = composition;
    this.crops = crops;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getBrand() {
    return brand;
  }

  public String getComposition() {
    return composition;
  }

  public List<Crop> getCrops() {
    return crops;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public void setComposition(String composition) {
    this.composition = composition;
  }

  public void setCrops(List<Crop> crops) {
    this.crops = crops;
  }
}

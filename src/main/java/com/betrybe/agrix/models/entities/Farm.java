package com.betrybe.agrix.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Entidade que representa a tabela farms no banco de dados.
 */

@Entity
@Table(name = "farms")
public class Farm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Double size;
  @OneToMany(mappedBy = "farm", fetch = FetchType.LAZY)
  @JsonIgnore
  private List<Crop> crops;

  public Farm() {
  }

  /**
   * Constructor da entidade Crop.
   *
   * @param id representa a coluna id na entidade.
   * @param name representa a coluna nome na entidade.
   * @param size representa a size nome na entidade.
   */
  public Farm(Long id, String name, Double size, List<Crop> crops) {
    this.id = id;
    this.name = name;
    this.size = size;
    this.crops = crops;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Double getSize() {
    return size;
  }

  public List<Crop> getCrops() {
    return crops;
  }

  public void setCrops(List<Crop> crops) {
    this.crops = crops;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSize(Double size) {
    this.size = size;
  }
}

package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dtos.PersonDto;
import com.betrybe.agrix.controllers.dtos.PersonResponseDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;
import com.betrybe.agrix.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para operações relacionadas à entidade 'Person'.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  @Autowired

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Cria uma nova instância de 'Person' com base nos dados fornecidos e retorna
   * uma resposta HTTP.
   *
   * @param personDto Um objeto DTO que contém informações do usuário para criar.
   * @return Uma resposta HTTP com os dados do usuário recém-criado.
   */
  @PostMapping
  public ResponseEntity<PersonResponseDto> cretePerson(
      @RequestBody PersonDto personDto
  ) {
    Person personToSave = new Person();
    personToSave.setUsername(personDto.username());
    personToSave.setPassword(personDto.password());
    personToSave.setRole(Role.valueOf(personDto.role()));
    Person newPerson = personService.create(personToSave);

    PersonResponseDto personResponseDto = new PersonResponseDto(
        newPerson.getId(),
        newPerson.getUsername(),
        personDto.role()
    );

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(personResponseDto);
  }
}

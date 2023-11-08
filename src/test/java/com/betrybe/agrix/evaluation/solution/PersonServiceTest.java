package com.betrybe.agrix.evaluation.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.exceptions.PersonNotFoundException;
import com.betrybe.agrix.models.repositories.PersonRepository;
import com.betrybe.agrix.security.Role;
import com.betrybe.agrix.services.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

/**
 * Esta classe contém testes unitários para a classe {@link PersonService}.
 */

@SpringBootTest
@ActiveProfiles("test")
public class PersonServiceTest {

  @Autowired
  PersonService personService;

  @MockBean
  PersonRepository personRepository;

  /**
   * Testa a obtenção de uma pessoa pelo ID.
   * Verifica se a pessoa é encontrada no repositório.
   * E se seus atributos estão corretos.
   */
  @Test
  public void testGetPersonById() {
    Role role = Role.ADMIN;
    Person personToReturn = new Person();
    personToReturn.setId(1L);
    personToReturn.setPassword("123456");
    personToReturn.setUsername("PessoaTeste");
    personToReturn.setRole(role);

    Mockito.when(personRepository.findById(any()))
        .thenReturn(Optional.of(personToReturn));

    Person personById = personService.getPersonById(1L);

    Mockito.verify(personRepository).findById(1L);

    assertEquals(1L, personById.getId());
    assertEquals(personToReturn.getUsername(), personById.getUsername());
    assertEquals(personToReturn.getPassword(), personById.getPassword());
    assertEquals(personToReturn.getRole(), personById.getRole());
  }

  /**
   * Testa a obtenção de uma pessoa pelo ID, quando a pessoa não é encontrada.
   * Verifica se uma exceção {@link PersonNotFoundException}.
   * É lançada quando a pessoa não é encontrada no repositório.
   */
  @Test
  public void testGetPersonByIdPersonNotFoundException() {
    Mockito.when(personRepository.findById(any()))
        .thenReturn(Optional.empty());

    assertThrows(
        PersonNotFoundException.class,
        () -> personService.getPersonById(1L)
    );

    Mockito.verify(personRepository).findById(1L);
  }

  /**
   * Testa a obtenção de uma pessoa pelo nome de usuário.
   * Verifica se a pessoa é encontrada no repositório.
   * E se seus atributos estão corretos.
   */
  @Test
  public void testGetPersonByUsername() {
    Role role = Role.ADMIN;
    Person personToReturn = new Person();
    personToReturn.setId(1L);
    personToReturn.setPassword("123456");
    personToReturn.setUsername("PessoaTeste");
    personToReturn.setRole(role);

    Mockito.when(personRepository.findByUsername(any()))
        .thenReturn(Optional.of(personToReturn));

    Person personByUsername = personService.getPersonByUsername("PessoaTeste");

    Mockito.verify(personRepository).findByUsername("PessoaTeste");

    assertEquals(1L, personByUsername.getId());
    assertEquals(personToReturn.getUsername(), personByUsername.getUsername());
    assertEquals(personToReturn.getPassword(), personByUsername.getPassword());
    assertEquals(personToReturn.getRole(), personByUsername.getRole());
  }

  /**
   * Testa a obtenção de uma pessoa pelo nome de usuário, quando a pessoa não é encontrada.
   * Verifica se uma exceção {@link PersonNotFoundException} é lançada.
   * Quando a pessoa não é encontrada no repositório.
   */
  @Test
  public void testGetPersonByUsernamePersonNotFoundException() {
    Mockito.when(personRepository.findByUsername(any()))
        .thenReturn(Optional.empty());

    assertThrows(
        PersonNotFoundException.class,
        () -> personService.getPersonByUsername("PessoaTeste")
    );

    Mockito.verify(personRepository).findByUsername("PessoaTeste");
  }

  /**
   * Testa a criação de uma pessoa.
   * Verifica se a pessoa é criada corretamente.
   * Verifica se seus atributos estão corretos.
   */
  @Test
  public void testCreatePerson() {
    Role role = Role.ADMIN;

    Person person = new Person();
    person.setUsername("PessoaTeste");
    person.setPassword("123456");
    person.setRole(role);

    Person personToReturn = new Person();
    personToReturn.setId(1L);
    personToReturn.setPassword("123456");
    personToReturn.setUsername("PessoaTeste");
    personToReturn.setRole(role);

    Mockito.when(personRepository.save(any(Person.class)))
        .thenReturn(personToReturn);

    Person savedPerson = personService.create(person);

    Mockito.verify(personRepository).save(any(Person.class));

    assertEquals(personToReturn.getId(), savedPerson.getId());
    assertEquals(personToReturn.getUsername(), savedPerson.getUsername());
    assertEquals(personToReturn.getPassword(), savedPerson.getPassword());
    assertEquals(personToReturn.getRole(), savedPerson.getRole());
  }
}

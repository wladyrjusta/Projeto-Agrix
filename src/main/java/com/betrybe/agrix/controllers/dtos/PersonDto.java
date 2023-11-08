package com.betrybe.agrix.controllers.dtos;


import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * Representa a entidade Person em requisições /POST.
 */
public record PersonDto(
    Long id, String username, String password, String role
) {

}

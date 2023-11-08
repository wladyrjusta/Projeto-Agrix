package com.betrybe.agrix.controllers.dtos;

/**
 * Representa a resposta para requisições /POST/person.
 */
public record PersonResponseDto(
    Long id, String username, String role
) {

}

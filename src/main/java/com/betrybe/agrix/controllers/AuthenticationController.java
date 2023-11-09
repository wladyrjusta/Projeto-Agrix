package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dtos.AuthenticationDto;
import com.betrybe.agrix.controllers.dtos.TokenResponseDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para autenticação de usuários.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  @Autowired
  public AuthenticationController(
      AuthenticationManager authenticationManager, TokenService tokenService
  ) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Realiza o processo de autenticação e gera um token JWT.
   *
   * @param authenticationDto O objeto com credenciais de autenticação.
   * @return Um ResponseEntity com o token JWT gerado.
   */
  @PostMapping("/login")
  public ResponseEntity<TokenResponseDto> login(
      @RequestBody AuthenticationDto authenticationDto
  ) {
    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(
        authenticationDto.username(),
        authenticationDto.password()
        );

    Authentication auth = authenticationManager.authenticate(usernamePassword);

    Person person = (Person) auth.getPrincipal();
    String token = tokenService.generateToken(person);
    TokenResponseDto tokenResponseDto = new TokenResponseDto(token);
    return ResponseEntity.status(HttpStatus.OK).body(tokenResponseDto);

  }
}

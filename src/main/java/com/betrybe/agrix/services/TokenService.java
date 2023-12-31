package com.betrybe.agrix.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.betrybe.agrix.models.entities.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Serviço para geração de tokens JWT.
 */
@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  /**
   * Gera um token JWT com base nas informações do usuário.
   *
   * @param person O objeto Person com informações do usuário.
   * @return O token JWT gerado.
   */
  public String generateToken(Person person) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT
        .create()
        .withIssuer("agrix")
        .withSubject(person.getUsername())
        .sign(algorithm);
  }

  /**
   * Valida um token JWT e retorna o assunto (subject) do token se for válido.
   *
   * @param token O token JWT a ser validado.
   * @return O assunto (subject) do token, se válido, ou null se inválido.
   */
  public String validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.require(algorithm)
        .withIssuer("agrix")
        .build()
        .verify(token)
        .getSubject();
  }
}

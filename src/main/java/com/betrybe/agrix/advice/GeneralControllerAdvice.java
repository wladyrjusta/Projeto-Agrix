package com.betrybe.agrix.advice;

import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.exceptions.FarmNotFoundException;
import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe de conselho para manipular exceções comuns em controladores.
 * Essa classe é anotada com `@ControllerAdvice` e define métodos para
 * manipular diferentes tipos de exceções e retornar respostas HTTP apropriadas.
 */
@ControllerAdvice
public class GeneralControllerAdvice {

  /**
   * Manipula exceções da classe `FarmNotFoundException` e retorna uma resposta HTTP 404 (Not Found)
   * com uma mensagem indicando que a fazenda não foi encontrada.
   *
   * @param exception A exceção `FarmNotFoundException` lançada.
   * @return Uma resposta HTTP com status 404 e uma mensagem informativa.
   */
  @ExceptionHandler(FarmNotFoundException.class)
  public ResponseEntity<String> handleFarmNotFoundException(FarmNotFoundException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Fazenda não encontrada!");
  }

  /**
   * Manipula exceções da classe `CropNotFoundException` e retorna uma resposta HTTP 404 (Not Found)
   * com uma mensagem indicando que a plantação não foi encontrada.
   *
   * @param exception A exceção `CropNotFoundException` lançada.
   * @return Uma resposta HTTP com status 404 e uma mensagem informativa.
   */
  @ExceptionHandler(CropNotFoundException.class)
  public ResponseEntity<String> handleCropNotFoundException(CropNotFoundException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Plantação não encontrada!");
  }

  /**
   * Manipula exceções da classe `FertilizerNotFoundException`
   * e retorna uma resposta HTTP 404 (Not Found)
   * com uma mensagem indicando que um fertilizante não foi encontrado.
   *
   * @param exception A exceção `FertilizerNotFoundException` lançada.
   * @return Uma resposta HTTP com status 404 e uma mensagem informativa.
   */
  @ExceptionHandler(FertilizerNotFoundException.class)
  public ResponseEntity<String> handleFertilizerNotFoundException(
      FertilizerNotFoundException exception
  ) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Fertilizante não encontrado!");
  }

  /**
   * Manipula exceções do tipo IllegalArgumentException e retorna uma resposta HTTP com
   * status de BadRequest (400) e uma mensagem de erro indicando que o valor de 'role' é inválido.
   *
   * @param exception A exceção IllegalArgumentException lançada.
   * @return Uma resposta HTTP com status de BadRequest e uma mensagem de erro.
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public  ResponseEntity<String> handlerIllegalArgumentException(
      IllegalArgumentException exception
  ) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body("Valor de 'role' inválido");
  }
}


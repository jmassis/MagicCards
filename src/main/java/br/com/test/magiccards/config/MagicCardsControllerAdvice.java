package br.com.test.magiccards.config;

import br.com.test.magiccards.exceptions.CardException;
import br.com.test.magiccards.model.dto.CardResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MagicCardsControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CardException.class)
    public ResponseEntity<CardResponseDTO> handleException(CardException exception, WebRequest request) {
        return ResponseEntity.status(exception.getStatusCode()).body(CardResponseDTO.builder()
                .message(exception.getMessage())
                .build());
    }
}
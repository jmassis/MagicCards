package br.com.test.magic_cards.exceptions;

import lombok.Getter;

@Getter
public class CardException extends RuntimeException {
    private Integer statusCode;

    public CardException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
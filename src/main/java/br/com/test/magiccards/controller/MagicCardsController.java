package br.com.test.magiccards.controller;

import br.com.test.magiccards.keys.ControllerKeys;
import br.com.test.magiccards.model.dto.CardDTO;
import br.com.test.magiccards.model.dto.CardListResponseDTO;
import br.com.test.magiccards.model.dto.CardResponseDTO;
import br.com.test.magiccards.service.MagicCardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/magic-cards")
public class MagicCardsController {
    @Autowired private MagicCardsService service;

    @GetMapping
    public ResponseEntity<CardListResponseDTO> getCardList(
        @RequestParam(value = ControllerKeys.GET_CARD_LIST_ORDER, required = false,
                defaultValue = ControllerKeys.GET_CARD_LIST_ORDER_DEFAULT_VALUE) Integer orderId
    ) {
        return ResponseEntity.ok().body(service.getCardList(orderId));
    }

    @PostMapping
    public ResponseEntity<CardResponseDTO> createCard(
        @RequestBody CardDTO card
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCard(card));
    }

    @PutMapping
    public ResponseEntity<CardResponseDTO> updateCard(
        @RequestBody CardDTO card
    ) {
        return ResponseEntity.ok().body(service.updateCard(card));
    }

    @DeleteMapping
    public ResponseEntity<CardResponseDTO> deleteCard(
        @RequestParam(ControllerKeys.DELETE_CARD_ID) Integer cardId
    ) {
        return ResponseEntity.ok().body(service.deleteCard(cardId));
    }
}
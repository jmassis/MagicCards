package br.com.test.magic_cards.controller;

import br.com.test.magic_cards.model.dto.MagicCardDTO;
import br.com.test.magic_cards.model.dto.MagicCardResponseDTO;
import br.com.test.magic_cards.service.MagicCardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/magic_cards")
public class MagicCardsController {

    @Autowired private MagicCardsService service;

//    @GetMapping("/getCardsList")
//    public ResponseEntity<List<MagicCardDTO>> getCardsList(
//        @RequestHeader ("ListIndex") Integer listIndex
//    ) {
//
//    }

    @PostMapping("/createMagicCard")
    public ResponseEntity<MagicCardResponseDTO> createMagicCard(
            @RequestHeader ("ListIndex") Integer listIndex,
            @RequestBody MagicCardDTO magicCard
    ) {
        return ResponseEntity.ok().body(service.createMagicCard(magicCard));
    }

}
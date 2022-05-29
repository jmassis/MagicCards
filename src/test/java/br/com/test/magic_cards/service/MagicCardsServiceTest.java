package br.com.test.magic_cards.service;

import br.com.test.magic_cards.keys.MagicCardsKeys;
import br.com.test.magic_cards.model.dto.MagicCardDTO;
import br.com.test.magic_cards.model.enums.LanguageEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MagicCardsServiceTest {

    private MagicCardsService service;

    @BeforeEach
    void setUp() {
        service = new MagicCardsService();
    }

    @Test
    void testCreateMagicCard() {
        var response = service.createMagicCard(MagicCardDTO.builder()
                .language(LanguageEnum.ENGLISH)
                .price(200.45)
                .build());
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }
    @Test
    void testCreateMagicCardWithError() {
        var response = service.createMagicCard(MagicCardDTO.builder()
                .language(LanguageEnum.ENGLISH)
                .build());
        assertEquals(MagicCardsKeys.ERROR_MESSAGE,response.getMessage());
    }

}
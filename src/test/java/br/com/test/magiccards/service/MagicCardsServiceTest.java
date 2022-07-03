package br.com.test.magiccards.service;

import br.com.test.magiccards.exceptions.CardException;
import br.com.test.magiccards.keys.MessageKeys;
import br.com.test.magiccards.model.dto.CardDTO;
import br.com.test.magiccards.model.entities.Card;
import br.com.test.magiccards.model.enums.LanguageEnum;
import br.com.test.magiccards.repository.MagicCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MagicCardsServiceTest {

    private MagicCardsService service;
    @Mock private MagicCardRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new MagicCardsService();
        ReflectionTestUtils.setField(service,"repository",repository);
        Mockito.when(repository.findAll()).thenReturn(getCardEntityList());
    }

    @Test
    void testGetCardList() {
        var response = service.getCardList(2);
        assertEquals(MessageKeys.SUCCESS_MESSAGE_GET_LIST,response.getResponse().getMessage());
    }

    @Test
    void testCreateCard() {
        var cardDTO = CardDTO.builder()
                .name("name")
                .edition("edition")
                .language(LanguageEnum.ENGLISH)
                .foil(true)
                .price(new BigDecimal(50.00))
                .build();
        var response = service.createCard(cardDTO);
        assertEquals(MessageKeys.SUCCESS_MESSAGE_CREATE,response.getMessage());
    }

    @Test
    void testCreateCardWithError() {
        var cardDTO = CardDTO.builder()
                .name("name")
                .edition("edition")
                .language(LanguageEnum.JAPANESE)
                .foil(true)
                .price(new BigDecimal(20.00))
                .build();
        assertThrows(CardException.class, () -> service.createCard(cardDTO));
    }

    @Test
    void testUpdateCard() {
        Mockito.when(repository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);
        var cardDTO = CardDTO.builder()
                .id(1)
                .name("name")
                .edition("edition")
                .language(LanguageEnum.ENGLISH)
                .foil(true)
                .price(new BigDecimal(50.00))
                .build();
        var response = service.updateCard(cardDTO);
        assertEquals(MessageKeys.SUCCESS_MESSAGE_UPDATE,response.getMessage());
    }

    @Test
    void testUpdateCardWithError() {
        Mockito.when(repository.existsById(ArgumentMatchers.anyInt())).thenReturn(false);
        var cardDTO = CardDTO.builder()
                .id(5)
                .name("name")
                .edition("edition")
                .language(LanguageEnum.ENGLISH)
                .foil(true)
                .price(new BigDecimal(50.00))
                .build();
        assertThrows(CardException.class, () -> service.updateCard(cardDTO));
    }

    @Test
    void testDeleteCard() {
        Mockito.when(repository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);
        var response = service.deleteCard(1);
        assertEquals(MessageKeys.SUCCESS_MESSAGE_DELETE,response.getMessage());
    }

    @Test
    void testDeleteCardWithError() {
        Mockito.when(repository.existsById(ArgumentMatchers.anyInt())).thenReturn(false);
        assertThrows(CardException.class, () -> service.deleteCard(4));
    }

    private List<Card> getCardEntityList() {
        var list = new ArrayList<Card>();
        list.add(Card.builder().id(1).language(LanguageEnum.JAPANESE.name()).foil(true).edition("edition").price(new BigDecimal(20.00)).name("name").build());
        list.add(Card.builder().id(2).language(LanguageEnum.JAPANESE.name()).foil(true).edition("edition").price(new BigDecimal(20.00)).name("name2").build());
        list.add(Card.builder().id(3).language(LanguageEnum.ENGLISH.name()).foil(true).edition("edition").price(new BigDecimal(20.00)).name("name").build());
        return list;
    }
}
package br.com.test.magiccards.util;

import br.com.test.magiccards.model.dto.CardDTO;
import br.com.test.magiccards.model.entities.Card;
import br.com.test.magiccards.model.enums.LanguageEnum;
import br.com.test.magiccards.repository.MagicCardRepository;

public class MagicCardsUtil {
    public static boolean validateCardAlreadyExists(Card newCard, MagicCardRepository repository) {
        var cardList = repository.findAll();
        for (Card existingCard : cardList) {
            if (isEqualCard(existingCard,newCard)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isEqualCard(Card existingCard,Card newCard) {
        if (
            existingCard.getName().equals(newCard.getName())
            && existingCard.getEdition().equals(newCard.getEdition())
            && existingCard.getLanguage().equals(newCard.getLanguage())
            && existingCard.getPrice().equals(newCard.getPrice())
            && existingCard.isFoil() == newCard.isFoil()
        ) {
            return true;
        }
        return false;
    }

    private static boolean isSimilarCard(Card existingCard,Card newCard) {
        if (
            existingCard.getEdition().equals(newCard.getEdition())
            && existingCard.getLanguage().equals(newCard.getLanguage())
            && existingCard.getPrice().equals(newCard.getPrice())
            && existingCard.isFoil() == newCard.isFoil()
        ) {
            return true;
        }
        return false;
    }

    public static Card generateCardEntity(CardDTO cardDTO) {
        return Card.builder()
                .edition(cardDTO.getEdition())
                .foil(cardDTO.isFoil())
                .language(cardDTO.getLanguage().name())
                .name(cardDTO.getName())
                .price(cardDTO.getPrice())
                .build();
    }

    public static Card updateCardEntity(CardDTO cardDTO) {
        var card = generateCardEntity(cardDTO);
        card.setId(cardDTO.getId());
        return card;
    }

    public static CardDTO generateCardDTO(Card cardEntity, MagicCardRepository repository) {
        return CardDTO.builder()
                .id(cardEntity.getId())
                .name(cardEntity.getName())
                .edition(cardEntity.getEdition())
                .language(LanguageEnum.returnLanguageEnum(cardEntity.getLanguage()))
                .foil(cardEntity.isFoil())
                .price(cardEntity.getPrice())
                .similarCards(searchSimilarCards(cardEntity, repository))
                .build();
    }

    private static Integer searchSimilarCards(Card card, MagicCardRepository repository) {
        Integer similarCards = 0;
        var cardList = repository.findAll();
        for (Card cardFromList : cardList) {
            if (isSimilarCard(cardFromList,card)) {
                similarCards++;
            }
        }
        return similarCards;
    }
}
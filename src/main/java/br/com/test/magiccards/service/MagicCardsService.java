package br.com.test.magiccards.service;

import br.com.test.magiccards.exceptions.CardException;
import br.com.test.magiccards.keys.MessageKeys;
import br.com.test.magiccards.model.dto.CardDTO;
import br.com.test.magiccards.model.dto.CardListResponseDTO;
import br.com.test.magiccards.model.dto.CardResponseDTO;
import br.com.test.magiccards.model.entities.Card;
import br.com.test.magiccards.model.enums.OrderListEnum;
import br.com.test.magiccards.repository.MagicCardRepository;
import br.com.test.magiccards.util.MagicCardsUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Slf4j
@Service
public class MagicCardsService {

    @Autowired MagicCardRepository repository;
    private final Gson gson = new Gson();

    public CardListResponseDTO getCardList(Integer orderId) {
        log.info("Init MagicCardsService :: getCardList :: OrderId={}",orderId);
        var cardList = new ArrayList<CardDTO>();
        try {
            var cardEntityList = repository.findAll();
            for (Card cardEntity : cardEntityList) {
                var card = MagicCardsUtil.generateCardDTO(cardEntity, repository);
                cardList.add(card);
            }
            var order = OrderListEnum.getEnum(orderId);
            switch (order) {
                case ALPHABETIC:
                    Collections.sort(cardList, Comparator.comparing(CardDTO::getName));
                    break;
                case PRICE:
                    Collections.sort(cardList, (c1, c2) -> c2.getPrice().compareTo(c1.getPrice()));
                    break;
            }
            log.info("End MagicCardsService :: getCardList :: {}", MessageKeys.SUCCESS_MESSAGE_GET_LIST);
            return CardListResponseDTO.builder()
                    .response(CardResponseDTO.builder()
                            .message(MessageKeys.SUCCESS_MESSAGE_GET_LIST)
                            .build())
                    .cards(cardList)
                    .build();
        } catch (RuntimeException e) {
            log.error("Error on MagicCardsService :: updateCard :: Error={}", e.getMessage(), e);
            throw new CardException(MessageKeys.ERROR_MESSAGE_GET_LIST,HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    public CardResponseDTO createCard(CardDTO card) {
        log.info("Init MagicCardsService :: createCard :: Card={}", gson.toJson(card));
        try {
            var cardEntity = MagicCardsUtil.generateCardEntity(card);
            if (MagicCardsUtil.validateCardAlreadyExists(cardEntity, repository)) {
                log.info("End MagicCardsService :: createCard :: {}", MessageKeys.CARD_ALREADY_EXISTS);
                throw new CardException(MessageKeys.CARD_ALREADY_EXISTS, HttpStatus.BAD_REQUEST.value());
            }
            repository.save(cardEntity);
            log.info("End MagicCardsService :: createCard :: {}", MessageKeys.SUCCESS_MESSAGE_CREATE);
            return CardResponseDTO.builder()
                    .message(MessageKeys.SUCCESS_MESSAGE_CREATE)
                    .build();
        } catch (CardException e) {
            throw e;
        } catch (RuntimeException e) {
            log.error("Error on MagicCardsService :: createCard :: Error={}", e.getMessage(), e);
            throw new CardException(MessageKeys.ERROR_MESSAGE_CREATE,HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    public CardResponseDTO updateCard(CardDTO card) {
        log.info("Init MagicCardsService :: updateCard :: Card={}",gson.toJson(card));
        try {
            if (repository.existsById(card.getId())) {
                var cardEntity = MagicCardsUtil.updateCardEntity(card);
                repository.save(cardEntity);
                return CardResponseDTO.builder()
                        .message(MessageKeys.SUCCESS_MESSAGE_UPDATE)
                        .build();
            }
            log.info("End MagicCardsService :: updateCard :: {}", MessageKeys.CARD_NO_EXISTS);
            throw new CardException(MessageKeys.CARD_NO_EXISTS, HttpStatus.NOT_FOUND.value());
        } catch (CardException e) {
            throw e;
        } catch (RuntimeException e) {
            log.error("Error on MagicCardsService :: updateCard :: Error={}", e.getMessage(), e);
            throw new CardException(MessageKeys.ERROR_MESSAGE_UPDATE,HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    public CardResponseDTO deleteCard(Integer id) {
        log.info("Init MagicCardsService :: deleteCard :: Id={}", id);
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                log.info("End MagicCardsService :: deleteMagicCard :: {}", MessageKeys.SUCCESS_MESSAGE_DELETE);
                return CardResponseDTO.builder()
                        .message(MessageKeys.SUCCESS_MESSAGE_DELETE)
                        .build();
            }
            log.info("End MagicCardsService :: deleteMagicCard :: {}", MessageKeys.CARD_NO_EXISTS);
            throw new CardException(MessageKeys.CARD_NO_EXISTS, HttpStatus.NOT_FOUND.value());
        } catch (CardException e) {
            throw e;
        } catch (RuntimeException e) {
            log.error("Error on MagicCardsService :: deleteMagicCard :: Error={}", e.getMessage(), e);
            throw new CardException(MessageKeys.ERROR_MESSAGE_DELETE, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
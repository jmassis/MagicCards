package br.com.test.magic_cards.service;

import br.com.test.magic_cards.dao.MagicCardDao;
import br.com.test.magic_cards.model.dto.MagicCardDTO;
import br.com.test.magic_cards.model.dto.MagicCardResponseDTO;
import br.com.test.magic_cards.model.entities.Card;
import br.com.test.magic_cards.util.JPAUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MagicCardsService {
    private final Gson gson = new Gson();

    public MagicCardResponseDTO createMagicCard(MagicCardDTO card) {
        log.info("Init MagicCardsService :: createMagicCard :: Card={}",gson.toJson(card));
        var cardEntity = Card.builder()
                .edition(card.getEdition())
                .foil(card.isFoil())
                .language(card.getLanguage())
                .name(card.getName())
                .price(card.getPrice())
                .build();
        this.createCardInDatabase(cardEntity);
        var response = MagicCardResponseDTO.builder()
                .status(HttpStatus.OK.value())
                .card(card)
                .message("CARTA CRIADA COM SUCESSO")
                .build();
        log.info("End MagicCardsService :: createMagicCard :: Response={}",gson.toJson(card));
        return response;
    }

    private void createCardInDatabase(Card card) {
        log.info("Init MagicCardsService :: createCardInDatabase :: Card={}",gson.toJson(card));
        var entityManager = JPAUtil.getEntityManager();
        var magicCardDao = new MagicCardDao(entityManager);
        entityManager.getTransaction().begin();
        magicCardDao.create(card);
        entityManager.getTransaction().commit();
        log.info("End MagicCardsService :: createCardInDatabase");
        entityManager.close();
    }
}
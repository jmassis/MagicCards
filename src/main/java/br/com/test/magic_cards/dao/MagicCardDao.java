package br.com.test.magic_cards.dao;

import br.com.test.magic_cards.model.entities.Card;
import javax.persistence.EntityManager;

public class MagicCardDao {

    private EntityManager entityManager;

    public MagicCardDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Card card) {
        this.entityManager.persist(card);
    }
}
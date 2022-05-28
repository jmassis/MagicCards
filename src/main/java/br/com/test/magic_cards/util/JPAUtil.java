package br.com.test.magic_cards.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    private static final EntityManagerFactory FACTORY = Persistence
            .createEntityManagerFactory("cards");

}
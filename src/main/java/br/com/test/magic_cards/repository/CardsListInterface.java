package br.com.test.magic_cards.repository;

import br.com.test.magic_cards.model.entities.CardsList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardsListInterface extends JpaRepository<CardsList, Long> {
    CardsList findByIndex(Integer index);
}
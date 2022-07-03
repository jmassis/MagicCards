package br.com.test.magic_cards.repository;

import br.com.test.magic_cards.model.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagicCardRepository extends JpaRepository<Card, Integer> {
}
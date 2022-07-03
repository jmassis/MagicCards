package br.com.test.magiccards.repository;

import br.com.test.magiccards.model.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagicCardRepository extends JpaRepository<Card, Integer> {
}
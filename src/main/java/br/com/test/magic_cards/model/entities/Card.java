package br.com.test.magic_cards.model.entities;

import br.com.test.magic_cards.model.enums.LanguageEnum;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer index;
    private String name;
    private String edition;
    private LanguageEnum language;
    private boolean foil;
    private Double price;
    private Integer similarCards;
}
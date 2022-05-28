package br.com.test.magic_cards.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MagicCardDTO {
    private Integer indexCard;
    private Integer indexList;
    private String name;
    private String edition;
    private String language;
    private boolean foil;
    private Double price;
    private Integer similarCards;
}
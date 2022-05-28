package br.com.test.magic_cards.model.dto;

import br.com.test.magic_cards.model.enums.LanguageEnum;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MagicCardDTO {
    private String name;
    private String edition;
    private LanguageEnum language;
    private boolean foil;
    private Double price;
    private Integer similarCards;
}
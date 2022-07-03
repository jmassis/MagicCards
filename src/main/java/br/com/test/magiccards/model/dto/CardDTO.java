package br.com.test.magiccards.model.dto;

import br.com.test.magiccards.model.enums.LanguageEnum;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private Integer id;
    private String name;
    private String edition;
    private LanguageEnum language;
    private boolean foil;
    private BigDecimal price;
    private Integer similarCards;
}
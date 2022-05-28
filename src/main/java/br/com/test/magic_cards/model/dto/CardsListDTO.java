package br.com.test.magic_cards.model.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardsListDTO {
    private Integer index;
    private String listName;
    private List<MagicCardDTO> cards;
}

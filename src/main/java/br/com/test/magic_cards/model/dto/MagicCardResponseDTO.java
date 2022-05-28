package br.com.test.magic_cards.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MagicCardResponseDTO {
    private Integer status;
    private String message;
    private MagicCardDTO card;
}
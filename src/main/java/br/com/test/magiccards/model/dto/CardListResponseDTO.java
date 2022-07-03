package br.com.test.magiccards.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardListResponseDTO {
    private CardResponseDTO response;
    private List<CardDTO> cards;
}
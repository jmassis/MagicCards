package br.com.test.magic_cards.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CardsList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer index;
    private String listName;
    @OneToMany(mappedBy = "id")
    private List<Card> cards = new ArrayList<>();
}
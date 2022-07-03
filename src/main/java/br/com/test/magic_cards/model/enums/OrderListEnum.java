package br.com.test.magic_cards.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderListEnum {
    DEFAULT(1),
    ALPHABETIC(2),
    PRICE(3);

    private final Integer id;

    public static OrderListEnum getEnum(Integer id) {
        for (OrderListEnum orderListEnum : values()) {
            if (orderListEnum.getId().equals(id)) {
                return orderListEnum;
            }
        }
        return DEFAULT;
    }
}
package br.com.test.magic_cards.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormatterUtilTest {

    @Test
    void testFormatMoney() {
        var response = FormatterUtil.convertMoney(350.5);
        assertEquals("R$ 350,50",response);
    }

}
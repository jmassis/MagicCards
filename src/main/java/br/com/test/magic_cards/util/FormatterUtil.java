package br.com.test.magic_cards.util;

import java.text.NumberFormat;
import java.util.Locale;

import static br.com.test.magic_cards.keys.MagicCardsKeys.NUMBER_FORMAT_COUNTRY;
import static br.com.test.magic_cards.keys.MagicCardsKeys.NUMBER_FORMAT_LANGUAGE;

public class FormatterUtil {
    public static String convertMoney(double money) {
        return NumberFormat.getCurrencyInstance(new Locale(NUMBER_FORMAT_LANGUAGE,NUMBER_FORMAT_COUNTRY)).format(money);
    }
}
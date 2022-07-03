package br.com.test.magic_cards.model.enums;

public enum LanguageEnum {
    PORTUGUESE,
    ENGLISH,
    JAPANESE;

    public static LanguageEnum returnLanguageEnum(String languageString) {
        for (LanguageEnum languageEnum : values()) {
            if (languageEnum.name().equals(languageString)) {
                return languageEnum;
            }
        }
        return null;
    }
}
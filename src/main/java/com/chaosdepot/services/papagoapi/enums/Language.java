package com.chaosdepot.services.papagoapi.enums;

/**
 * Language code used for the Papago url.
 */
public enum Language {
    AUTOMATIC("auto"),
    KOREAN("ko"),
    ENGLISH("en"),
    JAPANESE("ja"),
    CHINESE_CN("zh-CN"),
    CHINESE_TW("zh-TW"),
    SPAINSH("es"),
    FRANCH("fr"),
    GERMAN("de"),
    RUSSIAN("ru"),
    PORTUGUESE("pt"),
    ITALIAN("it"),
    VIETNAMESE("vi"),
    THAI("th"),
    INDONESIAN("id"),
    HINDI("hi");

    private String code = null;

    private Language(String code) {
        this.code = code;
    }

    /**
     * Get code value.
     *
     * @return code value
     */
    public String value() {
        return code;
    }
}

package com.cengizhanozeyranoglu.exception;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum MessageType {
    NO_RECORD_EXIST("1004", "kayt bulunamadı"),
    GENERAL_EXCEPTION("9999", "genel bir hata oluştu");

    private String code;

    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;

    }

}

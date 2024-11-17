package com.cengizhanozeyranoglu.exception;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum MessageType {
    NO_RECORD_EXIST("1004", "kayt bulunamadı"),
    GENERAL_EXCEPTION("9999", "genel bir hata oluştu"),
    CUSTOMER_AMOUNT_IS_NOT_ENOUGH("1001","müşterinin parası yeterli değildir"),
    CAR_STATUS_TYPE_IS_NOT_AVAILABLE("1002","araba satın almak için uygun değil");


    private String code;

    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;

    }

}

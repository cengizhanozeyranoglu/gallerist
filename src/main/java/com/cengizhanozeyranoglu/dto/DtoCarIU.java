package com.cengizhanozeyranoglu.dto;

import com.cengizhanozeyranoglu.enums.CarStatusType;
import com.cengizhanozeyranoglu.enums.CurrencyType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCarIU {

    @NotEmpty
    private String plaka;

    @NotEmpty
    private String brand;

    @NotEmpty
    private String model;

    @NotEmpty
    private Integer productionYear;

    @NotEmpty
    private BigDecimal price;

    @NotEmpty
    private CurrencyType currencyType;

    @NotEmpty
    private BigDecimal damagePrice;

    @NotEmpty
    private CarStatusType carStatusType;
}

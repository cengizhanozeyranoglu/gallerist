package com.cengizhanozeyranoglu.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DtoSaledCarIU {

    @NotNull
    private Long galleristId;

    @NotNull
    private Long carId;

    @NotNull
    private Long customerId;
}

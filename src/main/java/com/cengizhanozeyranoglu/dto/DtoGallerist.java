package com.cengizhanozeyranoglu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoGallerist extends DtoBase {

    private String firstName;

    private String lastName;

    private DtoAddress address;

}

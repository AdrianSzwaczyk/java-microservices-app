package org.example.dto.country;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class CountryCreateUpdateDTO {
    private String name;
    private int population;

}
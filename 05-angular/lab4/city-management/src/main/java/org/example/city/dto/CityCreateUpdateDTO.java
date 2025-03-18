package org.example.city.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CityCreateUpdateDTO {
    private String name;
    private int population;
}
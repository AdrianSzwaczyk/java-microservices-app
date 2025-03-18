package org.example.city.dto;

import lombok.*;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CityListDTO {
    private UUID id;
    private String name;
    private String cityName;
}
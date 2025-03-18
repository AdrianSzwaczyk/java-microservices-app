package org.example.dto.city;

import lombok.*;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CityReadDTO {
    private UUID id;
    private String name;
    private int population;
    private String countryName;
}
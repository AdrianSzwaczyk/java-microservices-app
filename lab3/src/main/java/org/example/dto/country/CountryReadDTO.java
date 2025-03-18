package org.example.dto.country;

import lombok.*;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CountryReadDTO {
    private UUID id;
    private String name;
    private int population;
}
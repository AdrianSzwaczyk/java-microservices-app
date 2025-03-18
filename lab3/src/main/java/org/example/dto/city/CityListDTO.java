package org.example.dto.city;

import lombok.*;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CityListDTO {
    private UUID id;
    private String name;
}
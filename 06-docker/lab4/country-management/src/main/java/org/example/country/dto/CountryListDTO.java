package org.example.country.dto;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CountryListDTO {
    private UUID id;
    private String name;
}
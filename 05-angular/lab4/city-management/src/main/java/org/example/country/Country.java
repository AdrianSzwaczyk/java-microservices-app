package org.example.country;

import jakarta.persistence.*;
import lombok.*;
import org.example.city.City;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "countries")
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Country implements Serializable {
    @Id
    private UUID id;
    private String name;
}
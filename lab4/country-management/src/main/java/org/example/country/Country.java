package org.example.country;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
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
    private int population;

    public static class CountryBuilder {
        public Country build() {
            if (this.id == null) {
                this.id = UUID.randomUUID();
            }
            return new Country(id, name, population);
        }
    }
    @Override
    public String toString() {
        return String.format("%-12s population: %d", this.name + ",", population);
    }
}
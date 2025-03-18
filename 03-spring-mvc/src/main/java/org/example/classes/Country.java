package org.example.classes;

import lombok.*;

import jakarta.persistence.*;
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
    private int population;
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<City> cities;
    public static class CountryBuilder {
        public Country build() {
            if (this.id == null) {
                this.id = UUID.randomUUID();
            }
            return new Country(id, name, population, cities);
        }
    }
    @Override
    public String toString() {
        return String.format("%-12s population: %d", this.name + ",", population);
    }
}
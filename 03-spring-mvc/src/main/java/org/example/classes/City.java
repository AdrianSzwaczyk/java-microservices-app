package org.example.classes;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "cities")
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class City implements Serializable {
    @Id
    private UUID id;
    private String name;
    private int population;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    @EqualsAndHashCode.Exclude
    private Country country;
    public static class CityBuilder {
        public City build() {
            if (this.id == null) {
                this.id = UUID.randomUUID();
            }
            return new City(id, name, population, country);
        }
    }
    @Override
    public String toString() {
        return String.format("%-15s%-12s%s%d",
                this.name,
                "in " + this.country.getName() + ",",
                " population: ",
                population);
    }
}
package org.example;

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
    @GeneratedValue
    private UUID id;
    private String name;
    private int population;
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<City> cities;
    @Override
    public String toString() {
        return String.format("%-12s population: %d", this.name + ",", population);
    }
}
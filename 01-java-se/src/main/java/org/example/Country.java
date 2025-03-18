package org.example;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Country implements Serializable {
    private String name;
    private int population;
    private List<City> cities;

    @Override
    public String toString() {
        return String.format("%-12s population: %d", this.name + ",", population);
    }
}

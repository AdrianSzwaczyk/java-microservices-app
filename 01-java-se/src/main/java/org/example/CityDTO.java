package org.example;

import lombok.*;

@Getter
@Builder
@Setter
@ToString
@EqualsAndHashCode
public class CityDTO {
    private String name;
    private int population;
    private String country;

    @Override
    public String toString() {
        return String.format("%-15s%-12s%s%d",
                this.name,
                "in " + this.country + ",",
                " population: ",
                population);
    }
}

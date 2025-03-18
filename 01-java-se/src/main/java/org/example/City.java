package org.example;

import lombok.*;

import java.io.Serializable;

@Builder
@Setter
@Getter
@EqualsAndHashCode
public class City implements Serializable {
    private String name;
    private int population;
    @EqualsAndHashCode.Exclude private Country country;

    @Override
    public String toString() {
        return String.format("%-15s%-12s%s%d",
                this.name,
                "in " + this.country.getName() + ",",
                " population: ",
                population);
    }
}

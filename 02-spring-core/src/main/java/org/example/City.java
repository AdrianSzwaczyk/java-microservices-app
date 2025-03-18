package org.example;

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
    @GeneratedValue
    private UUID id;
    private String name;
    private int population;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    @EqualsAndHashCode.Exclude
    private Country country;

    @Override
    public String toString() {
        return String.format("%-15s%-12s%s%d",
                this.name,
                "in " + this.country.getName() + ",",
                " population: ",
                population);
    }
}
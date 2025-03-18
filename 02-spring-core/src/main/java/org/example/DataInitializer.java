package org.example;

import org.example.services.CityService;
import org.example.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {
    @Autowired
    private CountryService countryService;
    @Autowired
    private CityService cityService;
    @PostConstruct
    public void init() {
        Country gabon = Country.builder().name("Gabon").population(1908000).build();
        Country congo = Country.builder().name("Congo").population(6142180).build();

        countryService.save(gabon);
        countryService.save(congo);

        City gabonCity1 = City.builder().name("Libreville").population(703940).country(gabon).build();
        City gabonCity2 = City.builder().name("Mandji").population(136462).country(gabon).build();
        City congoCity1 = City.builder().name("Brazzaville").population(1733263).country(congo).build();

        cityService.save(gabonCity1);
        cityService.save(gabonCity2);
        cityService.save(congoCity1);
    }
}

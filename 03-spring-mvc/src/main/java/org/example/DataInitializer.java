package org.example;

import org.example.classes.City;
import org.example.classes.Country;
import org.example.services.CityService;
import org.example.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private CountryService countryService;
    @Autowired
    private CityService cityService;
    @Override
    public void run(String... args) throws Exception {
        Country gabon = Country.builder().name("Gabon").population(1908000).build();
        Country congo = Country.builder().name("Congo").population(6142180).id(UUID.fromString(("7db409f8-8daf-4055-ac6b-6496fcbb5e5f"))).build();
        Country nigeria = Country.builder().name("Nigeria").population(206139589).id(UUID.fromString(("d6682a3a-18ca-4e7d-a948-e54959e5d4f8"))).build();

        countryService.save(gabon);
        countryService.save(congo);
        countryService.save(nigeria);


        City gabonCity1 = City.builder().name("Libreville").population(703940).country(gabon).build();
        City gabonCity2 = City.builder().name("Mandji").population(136462).country(gabon).id(UUID.fromString("de20c344-2ef2-48b7-a685-b2dc215c4319")).build();
        City congoCity1 = City.builder().name("Lagos").population(10733263).country(nigeria).id(UUID.fromString("4dd7f64d-69d6-4e57-87a9-28b10b42037e")).build();

        cityService.save(gabonCity1);
        cityService.save(gabonCity2);
        cityService.save(congoCity1);
    }
}

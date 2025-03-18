package org.example;

import org.example.country.Country;
import org.example.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private CountryService countryService;
    @Override
    public void run(String... args) throws Exception {
        Country gabon = Country.builder().name("Gabon").population(1908000).id(UUID.fromString(("b30e64a4-c672-411b-b92c-314417bff2b6"))).build();
        Country congo = Country.builder().name("Congo").population(6142180).id(UUID.fromString(("7db409f8-8daf-4055-ac6b-6496fcbb5e5f"))).build();
        Country nigeria = Country.builder().name("Nigeria").population(206139589).id(UUID.fromString(("d6682a3a-18ca-4e7d-a948-e54959e5d4f8"))).build();

        countryService.save(gabon);
        countryService.save(congo);
        countryService.save(nigeria);
    }
}

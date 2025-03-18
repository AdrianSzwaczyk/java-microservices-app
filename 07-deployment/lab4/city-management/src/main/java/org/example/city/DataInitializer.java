package org.example.city;

import org.antlr.v4.runtime.misc.LogManager;
import org.example.country.Country;
import org.example.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @Override
    public void run(String... args) throws Exception {
        Country gabon = Country.builder().name("Gabon").id(UUID.fromString(("b30e64a4-c672-411b-b92c-314417bff2b6"))).build();
        Country congo = Country.builder().name("Congo").id(UUID.fromString(("7db409f8-8daf-4055-ac6b-6496fcbb5e5f"))).build();
        Country nigeria = Country.builder().name("Nigeria").id(UUID.fromString(("d6682a3a-18ca-4e7d-a948-e54959e5d4f8"))).build();
        countryService.save(gabon);
        countryService.save(congo);
        countryService.save(nigeria);

        City gabonCity1 = City.builder().name("Libreville").id(UUID.fromString("4dd7f64d-69d6-4e57-87a9-28b10b42037e")).population(703940).country(gabon).build();
        City gabonCity2 = City.builder().name("Mandji").id(UUID.fromString("4dd7f64d-69d6-4e57-87a9-28b10b42037c")).population(136462).country(gabon).build();
        cityService.save(gabonCity1);
        cityService.save(gabonCity2);

        City congoCity1 = City.builder().name("Brazzaville").id(UUID.fromString("de20c344-2ef2-48b7-a685-b2dc215c4319")).population(1740000).country(congo).build();
        cityService.save(congoCity1);

        City nigeriaCity1 = City.builder().name("Lagos").id(UUID.fromString("4dd7f64d-69d6-4e57-87a9-28b10b42037d")).population(10733263).country(nigeria).build();
        City nigeriaCity2 = City.builder().name("Abuja").id(UUID.fromString("4dd7f64d-69d6-4e57-87a9-28b10b42037f")).population(1235880).country(nigeria).build();
        cityService.save(nigeriaCity1);
        cityService.save(nigeriaCity2);
    }
}

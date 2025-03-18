package org.example.country;

import org.example.country.dto.CountryCreateUpdateDTO;
import org.example.country.dto.CountryListDTO;
import org.example.country.dto.CountryReadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(CountryController.class);

    @GetMapping
    public List<CountryListDTO> getAllCountries() {
        return countryService.findAll().stream()
                .map(country -> new CountryListDTO(country.getId(), country.getName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CountryReadDTO getCountry(@PathVariable UUID id) {
        Country country = countryService.findById(id);
        return new CountryReadDTO(country.getId(), country.getName(), country.getPopulation());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CountryReadDTO createCountry(@RequestBody CountryCreateUpdateDTO dto) {
        Country country = Country.builder().name(dto.getName()).population(dto.getPopulation()).id(null).build();
        country = countryService.save(country);
        try {
            restTemplate.postForObject("http://city-management/api/cities/country/{id}", country.getName(), Void.class, country.getId());
        }
        catch (ResourceAccessException e) {
            log.error("Failed to connect to city-management service", e.getMessage());
            throw new RuntimeException("Unable to update cities for the country", e);
        }
        return new CountryReadDTO(country.getId(), country.getName(), country.getPopulation());
    }

    @PutMapping("/{id}")
    public CountryReadDTO updateCountry(@PathVariable UUID id, @RequestBody CountryCreateUpdateDTO dto) {
        Country existingCountry = countryService.findById(id);

        existingCountry.setName(dto.getName());
        existingCountry.setPopulation(dto.getPopulation());

        Country updatedCountry = countryService.save(existingCountry);
        try {
            restTemplate.put("http://city-management/api/cities/country/{id}", updatedCountry.getName(), updatedCountry.getId());
        }
        catch (ResourceAccessException e) {
            log.error("Failed to connect to city-management service", e.getMessage());
            throw new RuntimeException("Unable to update cities for the country", e);
        }
        return new CountryReadDTO(updatedCountry.getId(), updatedCountry.getName(), updatedCountry.getPopulation());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCountry(@PathVariable UUID id) {
        countryService.delete(id);
        try {
            restTemplate.delete("http://city-management/api/cities/country/{id}", id);
        }
        catch (ResourceAccessException e) {
            log.error("Failed to connect to city-management service", e.getMessage());
            throw new RuntimeException("Unable to update cities for the country", e);
        }
    }
}

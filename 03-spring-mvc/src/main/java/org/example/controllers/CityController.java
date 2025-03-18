package org.example.controllers;

import org.example.classes.City;
import org.example.classes.Country;
import org.example.dto.city.*;
import org.example.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<CityListDTO> getAllCities() {
        return cityService.findAll().stream()
                .map(city -> new CityListDTO(city.getId(), city.getName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CityReadDTO getCity(@PathVariable UUID id) {
        City city = cityService.findById(id);
        if (city == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
        }
        return new CityReadDTO(city.getId(), city.getName(), city.getPopulation(), city.getCountry().getName());
    }

    @PostMapping("/{countryId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CityReadDTO createCity(@PathVariable UUID countryId, @RequestBody CityCreateUpdateDTO dto) {
        Country country = countryService.findById(countryId);
        if (country == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
        }
        City city = City.builder().name(dto.getName()).population(dto.getPopulation()).id(null).country(country).build();
        city = cityService.save(city);
        return new CityReadDTO(city.getId(), city.getName(), city.getPopulation(), country.getName());
    }

    @PutMapping("/{id}")
    public CityReadDTO updateCity(@PathVariable UUID id, @RequestBody CityCreateUpdateDTO dto) {
        City existingCity = cityService.findById(id);
        existingCity.setName(dto.getName());
        existingCity.setPopulation(dto.getPopulation());
        City updatedCity = cityService.save(existingCity);
        return new CityReadDTO(updatedCity.getId(), updatedCity.getName(), updatedCity.getPopulation(), updatedCity.getCountry().getName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable UUID id) {
        cityService.delete(id);
    }
}

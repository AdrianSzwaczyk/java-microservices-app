package org.example.city;

import org.example.city.dto.CityCreateUpdateDTO;
import org.example.city.dto.CityListDTO;
import org.example.city.dto.CityReadDTO;
import org.example.country.*;
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
                .map(city -> new CityListDTO(city.getId(), city.getName(),city.getCountry().getName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CityReadDTO getCity(@PathVariable UUID id) {
        City city = cityService.findById(id);
        if (city == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
        }
        return new CityReadDTO(city.getId(), city.getName(), city.getPopulation());
    }

    @GetMapping("/country/{countryId}")
    public List<CityListDTO> getCitiesByCountry(@PathVariable UUID countryId) {
        List<City> cities = cityService.findByCountryId(countryId);
        if (cities.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No cities found for the specified country");
        }
        return cities.stream()
                .map(city -> new CityListDTO(city.getId(), city.getName(), city.getCountry().getName()))
                .collect(Collectors.toList());
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
        return new CityReadDTO(city.getId(), city.getName(), city.getPopulation());
    }
    @PostMapping("/country/{countryId}")
    public void createNewCountry(@PathVariable UUID countryId, @RequestBody String newCountryName) {
        Country country = new Country();
        country.setId(countryId);
        country.setName(newCountryName);

        countryService.save(country);
    }
    @PutMapping("/{id}")
    public CityReadDTO updateCity(@PathVariable UUID id, @RequestBody CityCreateUpdateDTO dto) {
        City existingCity = cityService.findById(id);
        existingCity.setName(dto.getName());
        existingCity.setPopulation(dto.getPopulation());
        City updatedCity = cityService.save(existingCity);
        return new CityReadDTO(updatedCity.getId(), updatedCity.getName(), updatedCity.getPopulation());
    }
    @PutMapping("/country/{id}")
    public Country updateCountry(@PathVariable UUID id, @RequestBody String updated) {
        Country existingCountry = countryService.findById(id);

        existingCountry.setName(updated);

        Country updatedCountry = countryService.save(existingCountry);
        return new Country(updatedCountry.getId(), updatedCountry.getName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable UUID id) {
        cityService.delete(id);
    }

    @DeleteMapping("/country/{countryId}")
    public void deleteCitiesByCountry(@PathVariable UUID countryId) {
        List<City> cities = cityService.findByCountryId(countryId);
        for (City city : cities) {
            cityService.delete(city.getId());
        }
        countryService.delete(countryId);
    }
}

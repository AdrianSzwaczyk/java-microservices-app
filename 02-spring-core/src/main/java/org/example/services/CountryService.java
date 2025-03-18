package org.example.services;

import org.example.Country;
import org.example.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;
    public List<Country> findAll() {
        return countryRepository.findAll();
    }
    public Country save(Country country) {
        return countryRepository.save(country);
    }
    public void delete(UUID id) {
        countryRepository.deleteById(id);
    }
}

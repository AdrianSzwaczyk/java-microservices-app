package org.example.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public Country save(Country country) {
        return countryRepository.save(country);
    }
    public void delete(UUID id) {
        countryRepository.deleteById(id);
    }
    public Country findById(UUID id) {
        return countryRepository.findById(id).orElse(null);
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }
}

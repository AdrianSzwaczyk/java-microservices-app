package org.example.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;
    public List<City> findAll() {
        return cityRepository.findAll();
    }
    public List<City> findByCountryId(UUID countryId) {
        return cityRepository.findByCountryId(countryId);
    }
    public City save(City city) {
        return cityRepository.save(city);
    }
    public void delete(UUID id) {
        cityRepository.deleteById(id);
    }
    public City findById(UUID id) {
        return cityRepository.findById(id).orElse(null);
    }
}

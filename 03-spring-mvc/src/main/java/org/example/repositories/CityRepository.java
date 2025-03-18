package org.example.repositories;

import org.example.classes.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {
    List<City> findByCountryId(UUID countryId);
}
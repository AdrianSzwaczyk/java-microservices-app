package org.example;

import org.example.services.CityService;
import org.example.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static java.lang.System.exit;

@Component
public class CMDRunner implements CommandLineRunner {
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("""
                    Available commands:
                    1. list_countries
                    2. list_cities
                    3. add_city
                    4. delete_city
                    5. exit""");
            command = scanner.nextLine();

            switch (command) {
                case "1":
                case "list_countries":
                    List<Country> countries = countryService.findAll();
                    countries.forEach(System.out::println);
                    break;
                case "2":
                case "list_cities":
                    List<City> cities = cityService.findAll();
                    cities.forEach(System.out::println);
                    break;
                case "3":
                case "add_city":
                    System.out.print("City name: ");
                    String cityName = scanner.nextLine();
                    System.out.print("Population: ");
                    int population;
                    try {
                        population = Integer.parseInt(scanner.nextLine());
                        if (population < 0) {
                            System.out.println("Invalid population. Please enter a number between 0 and " + Integer.MAX_VALUE + ".");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number between 0 and " + Integer.MAX_VALUE + ".");
                        break;
                    }
                    countries = countryService.findAll();
                    if (countries.isEmpty()) {
                        System.out.println("No countries available.");
                        return;
                    }
                    Country selectedCountry = null;
                    while (selectedCountry == null) {
                        System.out.println("Available countries:");
                        for (int i = 0; i < countries.size(); i++) {
                            System.out.printf("%d. %s%n", i + 1, countries.get(i).getName());
                        }
                        System.out.print("Select a country by number: ");
                        try {
                            int countryIndex = Integer.parseInt(scanner.nextLine()) - 1;
                            if (countryIndex < 0 || countryIndex >= countries.size()) {
                                System.out.println("Invalid selection. Please try again.");
                                continue;
                            }
                            selectedCountry = countries.get(countryIndex);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number.");
                        }
                    }
                    City newCity = City.builder().name(cityName).population(population).country(selectedCountry).build();
                    cityService.save(newCity);
                    break;
                case "4":
                case "delete_city":
                    cities = cityService.findAll();
                    if (cities.isEmpty()) {
                        System.out.println("No cities available.");
                        break;
                    }
                    System.out.println("Available cities:");
                    for (int i = 0; i < cities.size(); i++) {
                        System.out.printf("%d. %s%n", i + 1, cities.get(i).getName());
                    }
                    System.out.print("Select a city by number to delete: ");
                    try {
                        int cityIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        if (cityIndex < 0 || cityIndex >= cities.size()) {
                            System.out.println("Invalid selection. Please try again.");
                            break;
                        }
                        UUID cityId = cities.get(cityIndex).getId();
                        String cityN = cities.get(cityIndex).getName();
                        String cityC = String.valueOf(cities.get(cityIndex).getCountry());
                        cityService.delete(cityId);
                        System.out.println(cityN + " in " + cityC + " deleted successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;
                case "5":
                case "exit":
                    exit(0);
                default:
                    System.out.println("Unknown command.");
            }
        }
    }
}

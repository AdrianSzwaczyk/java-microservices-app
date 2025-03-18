package org.example;

import java.io.*;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // implementation                          TASK 1
        ArrayList<Country> list = initialize(); // TASK 2
        Set<City> set = createSet(list);        // TASK 3
        filterAndSort(set);                     // TASK 4
        createDTO(list);                        // TASK 5
        serialization(list);                    // TASK 6
        parallelProcessing(list);               // TASK 7
    }

    // TASK 2
    private static ArrayList<Country> initialize() {
        System.out.println("------- TASK 2 -------");

        ArrayList<Country> countryList = new ArrayList<>();
        ArrayList<City> cityList1 = new ArrayList<>();
        ArrayList<City> cityList2 = new ArrayList<>();

        Country gabon = Country.builder().name("Gabon").population(1908000).build();
        Country congo = Country.builder().name("Congo").population(6142180).build();
        countryList.add(gabon);
        countryList.add(congo);

        City gabonCity1 = City.builder().name("Libreville").population(703940).country(gabon).build();
        City gabonCity2 = City.builder().name("Mandji").population(136462).country(gabon).build();
        City gabonCity3 = City.builder().name("Masuku").population(110568).country(gabon).build();
        City congoCity1 = City.builder().name("Brazzaville").population(1733263).country(congo).build();
        City congoCity2 = City.builder().name("Pointe-Noire").population(787799).country(congo).build();
        City congoCity3 = City.builder().name("Dolisie").population(83798).country(congo).build();

        cityList1.add(gabonCity1);
        cityList1.add(gabonCity2);
        cityList1.add(gabonCity3);
        cityList2.add(congoCity1);
        cityList2.add(congoCity2);
        cityList2.add(congoCity3);

        gabon.setCities(cityList1);
        congo.setCities(cityList2);

        countryList.forEach(country -> {
            System.out.println(country);
            country.getCities().forEach(System.out::println);
        });
        System.out.println();

        return countryList;
    }

    // TASK 3
    private static Set<City> createSet(ArrayList<Country> countryList) {
        System.out.println("------- TASK 3 -------");
        System.out.println("Cities:");
        Set<City> citySet = countryList.stream()
                .flatMap(country -> country.getCities().stream())
                .collect(Collectors.toSet());
        citySet.stream().forEach(System.out::println);
        System.out.println();
        return citySet;
    }

    // TASK 4
    private static void filterAndSort(Set<City> citySet) {
        System.out.println("------- TASK 4 -------");
        System.out.println("List of big cities sorted by name:");
        citySet.stream()
                .filter(city -> city.getPopulation() > 500000)
                .sorted(Comparator.comparing(City::getName))
                .forEach(System.out::println);
        System.out.println();
    }

    // TASK 5
    private static void createDTO(ArrayList<Country> countryList) {
        System.out.println("------- TASK 5 -------");
        System.out.println("DTO City list:");

        List<CityDTO> cityDTOList = countryList.stream()
                .flatMap(country -> country.getCities().stream())
                .map(city -> new CityDTO(city.getName(), city.getPopulation(), city.getCountry().getName()))
                .sorted(Comparator.comparing(CityDTO::getName))
                .toList();

        cityDTOList.stream().forEach(System.out::println);

        System.out.println();
    }


    // TASK 6
    private static void serialization(ArrayList<Country> countryList) throws IOException, ClassNotFoundException {
        System.out.println("------- TASK 6 -------");
        System.out.println("Country's cities list from file");

        try (FileOutputStream outputFile = new FileOutputStream("countries.ser");
             ObjectOutputStream outputObject = new ObjectOutputStream(outputFile)) {
            outputObject.writeObject(countryList);
        }

        try (FileInputStream inputFile = new FileInputStream("countries.ser");
             ObjectInputStream inputObject = new ObjectInputStream(inputFile)) {
            ArrayList<Country> countryFromFile = (ArrayList<Country>) inputObject.readObject();

            for (Country country : countryFromFile) {
                System.out.println(country);
                country.getCities().forEach(System.out::println);
            }
        }
        System.out.println();
    }

    // TASK 7
    private static void parallelProcessing(ArrayList<Country> countryList) {
        System.out.println("------- TASK 7 -------");
        System.out.println("Parallel processing of cities");
        ForkJoinPool cityFork = new ForkJoinPool(2);

        countryList.forEach(country -> {
            CityProcessing task = new CityProcessing(new ArrayList<>(country.getCities()), 2000 + country.getPopulation() / 1000);
            cityFork.submit(task);
        });

        cityFork.shutdown();
        try {
            cityFork.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println();
    }
}

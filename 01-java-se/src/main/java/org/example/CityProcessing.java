package org.example;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

public class CityProcessing extends RecursiveTask<Void> {
    private final ArrayList<City> cities;
    private final int delay;
    CityProcessing(ArrayList<City> cities, int delay) {
        this.cities = cities;
        this.delay = delay;
    }
    @Override
    protected Void compute() {
        cities.forEach(city -> {
            try {
                Thread.sleep(delay);
                System.out.println(city.getName() + " in " + city.getCountry().getName()
                        + ", population: " + city.getPopulation() + ", is being processed in thread: " + Thread.currentThread().getId());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        return null;
    }
}

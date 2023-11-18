package tec.bd.weather.repository.memory;

import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.Repository;

import java.util.*;

public class InMemoryForecastRepository implements Repository<Forecast, Integer> {

    private Set<Forecast> inMemoryForecastData;

    public InMemoryForecastRepository() {
        // "inicializando" la base de datos

       /* this.inMemoryForecastData = new HashSet<>();
        this.inMemoryForecastData.add(new Forecast(2001, 23.0 , "13-11-2023");
        this.inMemoryForecastData.add(new Forecast(2, "Costa Rica", "Cartago", "20201", new Date(), 24.0f));
        this.inMemoryForecastData.add(new Forecast(3, "Costa Rica", "San Jose", "30301", new Date(), 25.0f));
        this.inMemoryForecastData.add(new Forecast(4, "Costa Rica", "Limon", "40401", new Date(), 25.0f));*/
    }

    public int getCurrentMaxId() {
        return this.inMemoryForecastData
                .stream()
                .max(Comparator.comparing(Forecast::getId))
                .map(Forecast::getId)
                .orElse(0);
    }

    @Override
    public Optional<Forecast> findById(Integer id) {
        return this.inMemoryForecastData
                .stream()
                .filter(e -> Objects.equals(e.getId(), id))
                .findFirst();
    }

    @Override
    public List<Forecast> findAll() {
        return new ArrayList<>(this.inMemoryForecastData);
    }

    @Override
    public Forecast save(Forecast forecast) {
        forecast.setId(this.getCurrentMaxId() + 1);
        this.inMemoryForecastData.add(forecast);
        return forecast;
    }

    @Override
    public void delete(Integer id) {
        this.inMemoryForecastData.removeIf(forecast -> Objects.equals(forecast.getId(), id));
    }

    @Override
    public Forecast update(Forecast source) {
        var current = this.findById(source.getId()).orElseThrow(() -> new RuntimeException("Weather forecast ID not found"));

        // Update the fields
        current.setCityCode(source.getCityCode());
        current.setTemperature(source.getTemperature());
        current.setForecastDate(source.getForecastDate());

        return current;
    }
}

package tec.bd.weather.service;

import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.Repository;

import java.util.List;

public class WeatherServiceImpl implements WeatherService {

    private Repository<Forecast, Integer> weatherRepository;

    public WeatherServiceImpl(Repository<Forecast, Integer> weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public float getCityTemperature(String city) {
        var weather = this.weatherRepository
                .findAll()
                .stream()
                .filter(e -> e.getCityCode().equals(city)) // Assuming cityCode is used for city identification
                .findFirst()
                .orElseThrow(() -> new RuntimeException(city + " is not supported"));

        return weather.getTemperature();
    }

    @Override
    public float getZipCodeTemperature(String zipCode) {
        var weather = this.weatherRepository
                .findAll()
                .stream()
                .filter(e -> e.getCityCode().equals(zipCode))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(zipCode + " is not supported"));

        return weather.getTemperature();
    }

    @Override
    public List<Forecast> getAllForecasts() {
        // TODO: aqui podria ir logica de conversion de tipos
        return this.weatherRepository.findAll();
    }

    @Override
    public Forecast newForecast(Forecast newForecast) {
        Forecast.validate(newForecast);
        if (newForecast.getId() != null && newForecast.getId() != 0) {
            throw new RuntimeException("Invalid forecast ID for a new forecast");
        }

        return this.weatherRepository.save(newForecast);
    }

    @Override
    public Forecast updateForecast(Forecast forecast) {
        Forecast.validate(forecast);
        if (forecast.getId() < 1) {
            throw new RuntimeException("Invalid forecast ID " + forecast.getId());
        }
        var current = this.weatherRepository.findById(forecast.getId());
        if (current.isEmpty()) {
            throw new RuntimeException("Weather forecast ID doesn't exist in the database");
        }
        return this.weatherRepository.update(forecast);
    }

    @Override
    public void removeForecast(int forecastId) {
        var current = this.weatherRepository.findById(forecastId);
        if (current.isEmpty()) {
            throw new RuntimeException("Weather forecast ID doesn't exist in the database");
        }
        this.weatherRepository.delete(forecastId);
    }
}

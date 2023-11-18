package tec.bd.weather.service;

import tec.bd.weather.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityService {

    List<City> getAllCities();

    Optional<City> getCityById(int cityId);

    City newCity(String cityName, int stateCode);

    void removeByCityId(int cityId);

    City updateCity(City city);
}

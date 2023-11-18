package tec.bd.weather.service;

import tec.bd.weather.entity.City;
import tec.bd.weather.repository.Repository;

import java.util.List;
import java.util.Optional;

public class CityServiceImpl implements CityService {

    private Repository<City, Integer> cityRepository;

    public CityServiceImpl(Repository<City, Integer> cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getAllCities() {
        return this.cityRepository.findAll();
    }

    @Override
    public Optional<City> getCityById(int cityId) {
        // Validaciones. El cityId es mayor que cero?

        return this.cityRepository.findById(cityId);
    }

    @Override
    public City newCity(String cityName, int stateCode) {
        // No permitir que el cityName o zipcode sea nulo o vacío

        // Validar si el cityName ya existe en la base de datos
        // para esto habría que buscar el nombre de la ciudad cityName en la base de datos
        // y ver si existe. Si ya existe no se guarda.

        var cityToBeSaved = new City(cityName, stateCode);
        return this.cityRepository.save(cityToBeSaved);
    }

    @Override
    public void removeByCityId(int cityId) {
        // Validaciones. El cityId es mayor que cero?

        var cityFromDBOpt = this.getCityById(cityId);

        if (cityFromDBOpt.isEmpty()) {
            throw new RuntimeException("City Id: " + cityId + " not found");
        }

        this.cityRepository.delete(cityId);
    }

    @Override
    public City updateCity(City city) {
        // validar si el city.Id existe en la base de datos
        // validar si el nombre de la ciudad ya existe en la BD

        var cityUpdated = this.cityRepository.update(city);
        return cityUpdated;
    }
}

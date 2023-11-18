package tec.bd.weather.cli.city;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;

@CommandLine.Command(name = "city-create", aliases = {"ccity"}, description = "Create new city")
public class CreateCityCommand implements Runnable {
    @CommandLine.Parameters(paramLabel = "<city name>", description = "The city name")
    private String cityName;

    @CommandLine.Parameters(paramLabel = "<state code>", description = "The state id")
    private int stateId;

    @Override
    public void run() {
        System.out.println("Create City Command");
        // Crear la ciudad

        var appContext = new ApplicationContext();
        var cityService = appContext.getCityService();
        var newCity = cityService.newCity(cityName, stateId);

        System.out.println("New City: " + newCity.getIdCiudad() + ", " + newCity.getCityName() + ", " + newCity.getIdState());
    }
}

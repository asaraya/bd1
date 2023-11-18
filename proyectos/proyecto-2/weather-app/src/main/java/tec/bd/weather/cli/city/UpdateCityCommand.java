package tec.bd.weather.cli.city;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;

@CommandLine.Command(name = "city-update", aliases = {"cuc"}, description = "Update city")
public class UpdateCityCommand implements Runnable {

    @CommandLine.Parameters(index = "0", paramLabel = "<city Id>", description = "The city Id.")
    private int cityId;

    @CommandLine.Parameters(index = "1", paramLabel = "<new city name>", description = "The new city name")
    private String newCityName;

    @CommandLine.Parameters(index = "2", paramLabel = "<state Id>", description = "The new state Id")
    private int newStateId;

    @Override
    public void run() {
        System.out.println("Update City Command");

        var appContext = new ApplicationContext();
        var cityService = appContext.getCityService();

        try {
            var existingCity = cityService.getCityById(cityId);

            if (existingCity.isPresent()) {
                existingCity.get().setCityName(newCityName);
                existingCity.get().setStateId(newStateId);

                cityService.updateCity(existingCity.get());
                System.out.println("City Id updated: " + cityId);
            } else {
                System.out.println("City not found with Id: " + cityId);
            }
        } catch (Exception e) {
            System.err.println("City Id: " + cityId + " not updated. Reason: " + e.getMessage());
        }
    }
}
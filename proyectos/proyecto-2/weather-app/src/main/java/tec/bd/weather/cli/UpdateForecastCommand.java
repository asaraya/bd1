package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.Forecast;

import java.util.Date;

@CommandLine.Command(name = "update-forecast", aliases = {"uf"}, description = "Update existing forecast data")
public class UpdateForecastCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<forecast id>", description = "The new forecast id")
    private int newForecastId;

    @CommandLine.Parameters(paramLabel = "<city code>", description = "The city code")
    private Integer cityCode;

    @CommandLine.Parameters(paramLabel = "<zip code>", description = "The Zip code")
    private String zipCode;

    @CommandLine.Parameters(paramLabel = "<temperature>", description = "Temperature value")
    private float temperature;

    @CommandLine.Parameters(paramLabel = "<forecast date>", description = "The Forecast date")
    private Date forecastDate;

    @Override
    public void run() {
        try {
            var appContext = new ApplicationContext();
            var weatherService = appContext.getWeatherService();

            // Updated to match the constructor parameters of Forecast
            var newForecast = new Forecast(cityCode, temperature, forecastDate);

            // Set the ID separately since it might not be modifiable
            newForecast.setId(newForecastId);

            // Validate the updated forecast before updating
            Forecast.validate(newForecast);

            var updatedForecast = weatherService.updateForecast(newForecast);
            System.out.println(updatedForecast);
        } catch (Exception e) {
            System.err.println("Can't update forecast. " + e.getMessage());
        }
    }
}

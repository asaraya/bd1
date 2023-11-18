package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.Forecast;

import java.util.Date;

@CommandLine.Command(name = "forecast-create", aliases = {"cf"}, description = "Create new forecast for a city")
public class CreateForecastCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<city code>", description = "The city code")
    private Integer cityCode;

    @CommandLine.Parameters(paramLabel = "<temperature>", description = "Temperature value")
    private float temperature;

    @CommandLine.Parameters(paramLabel = "<forecast date>", description = "The Forecast date")
    private Date forecastDate;

    @Override
    public void run() {
       // try {
            System.out.println("Create Forecast Command");
            var appContext = new ApplicationContext();
            var weatherService = appContext.getWeatherService();

            // Updated to match the constructor parameters of Forecast
            var forecastToBeCreated = new Forecast(cityCode, temperature, forecastDate);

            // Validate the forecast before creating it
            Forecast.validate(forecastToBeCreated);

            var newForecast = weatherService.newForecast(forecastToBeCreated);
            System.out.println(newForecast);
        /*} catch (Exception e) {
            System.err.println("Can't create forecastss. " + e.getMessage());
        }*/
    }
}

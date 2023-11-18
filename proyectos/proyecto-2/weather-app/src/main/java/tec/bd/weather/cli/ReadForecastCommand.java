package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.Forecast;

@CommandLine.Command(name = "forecast-read", aliases = {"fr"}, description = "Read all forecasts.")
public class ReadForecastCommand implements Runnable {

    @Override
    public void run() {
        System.out.println("Read All Forecasts");

        var appContext = new ApplicationContext();
        var weatherService = appContext.getWeatherService();

        var forecasts = weatherService.getAllForecasts();

        System.out.println("Forecasts");
        System.out.println("=============================================");
        for (Forecast f : forecasts) {
            System.out.println("ID: " + f.getId() +
                    ", City Code: " + f.getCityCode() +
                    ", Temperature: " + f.getTemperature() +
                    ", Forecast Date: " + f.getForecastDate());
        }
    }
}


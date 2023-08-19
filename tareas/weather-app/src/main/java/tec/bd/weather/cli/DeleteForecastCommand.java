package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.Forecast;

@CommandLine.Command(name = "delete-forecast", aliases = { "df" }, description = "Delete existing forecast data")
public class UpdateForecastCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<forecast id>", description = "The forecast id to delete")
    private int ForecastIdToDelete;

    @Override
    public void run() {
        try {
            var appContext = new ApplicationContext();
            var weatherService = appContext.getWeatherService();
            weatherService.removeForecast(ForecastIdToDelete)
            System.out.println("Deleted the forecast with the id: " + ForecastIdToDelete);
        } catch (Exception e) {
            System.err.println("Can't delete forecast. " +  e.getMessage());
        }
    }

}
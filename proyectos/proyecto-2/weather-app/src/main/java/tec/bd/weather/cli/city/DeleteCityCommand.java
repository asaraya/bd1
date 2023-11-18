package tec.bd.weather.cli.city;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;

@CommandLine.Command(name = "city-delete", aliases = {"ccityd"}, description = "Delete city by Id")
public class DeleteCityCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<city Id>", description = "The city Id.", defaultValue = "0")
    private int cityId;

    @Override
    public void run() {

        var appContext = new ApplicationContext();
        var cityService = appContext.getCityService();

        try {
            cityService.removeByCityId(cityId);
            System.out.println("City Id: " + cityId + " deleted");
        } catch (Exception e) {
            System.err.println("City Id: " + cityId + " not deleted. Reason: " + e.getMessage());
        }
    }
}

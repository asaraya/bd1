package tec.bd.weather.cli.city;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.City;

@CommandLine.Command(name = "city-read", aliases = {"ccr"}, description = "Read all cities or city by Id.")
public class ReadCityCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<city Id>", description = "The city Id.", defaultValue = "0")
    private int cityId;

    @Override
    public void run() {
        System.out.println("Read City. City Id: " + cityId);

        var appContext = new ApplicationContext();
        var cityService = appContext.getCityService();

        // Si no se pasa un city Id como parámetro al comando
        if (cityId == 0) {
            var cities = cityService.getAllCities();

            System.out.println("Cities");
            System.out.println("=============================================");
            for (City c : cities) {
                System.out.println(c.getIdCiudad() + "\t" + c.getCityName() + "\t" + c.getZipcode());
            }
        } else {

            var city = cityService.getCityById(cityId);
            if (city.isPresent()) {
                System.out.println("City");
                System.out.println("=============================================");
                System.out.println(city.get().getIdCiudad() + "\t" + city.get().getCityName() + "\t" + city.get().getZipcode());
            } else {
                System.out.println("City Id: " + cityId + " not found.");
            }
        }
    }
}

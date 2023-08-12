package tec.bd.weather;

import picocli.CommandLine;

@CommandLine.Command(name = "by-zip", description = "Get weather for a Zip code")
public class WeatherByZipCodeCommand implements Runnable{

    @CommandLine.Parameters(paramLabel = "<zip code>", description = "The Zip code")
    private String zipCode;

    @Override
    public void run() {

        System.out.println("By Zip Code: " + zipCode);

        try {
            WeatherService weatherService = new WeatherServiceImpl();
            System.out.println(weatherService.getZipCodeTemperature(zipCode));
        } catch (Exception e) {
            System.out.println(zipCode + " is not supported");
        }
    }
}

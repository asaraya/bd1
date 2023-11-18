package tec.bd.weather.cli.country;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;

@CommandLine.Command(name = "country-update", aliases = {"cu"}, description = "Update country")
public class UpdateCountryCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<country code>", description = "The country code")
    private int countryCode;

    @CommandLine.Parameters(paramLabel = "<new country name>", description = "The new country name")
    private String newCountryName;

    @Override
    public void run() {
        var appContext = new ApplicationContext();
        var countryService = appContext.getCountryService();

        try {
            var existingCountry = countryService.getCountryById(countryCode);

            if (existingCountry.isPresent()) {
                existingCountry.get().setCountryName(newCountryName);

                var result = countryService.updateCountry(existingCountry.get());
                System.out.println("Country updadted: " + existingCountry.get().getId() + ", " + existingCountry.get().getCountryName());
            } else {
                System.out.println("Country not found with code: " + countryCode);
            }
        } catch (Exception e) {
            System.err.println("Can't update country. " + e.getMessage());
        }
    }
}

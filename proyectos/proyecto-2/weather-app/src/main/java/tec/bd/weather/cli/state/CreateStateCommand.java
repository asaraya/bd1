package tec.bd.weather.cli.state;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;

@CommandLine.Command(name = "state-create", aliases = {"cstate"}, description = "Create new state")
public class CreateStateCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<state name>", description = "The state name")
    private String stateName;

    @CommandLine.Parameters(paramLabel = "<country code>", description = "The country id")
    private int countryId;

    @Override
    public void run() {
        System.out.println("Create State Command");

        // Validar que el country_id existe
        var appContext = new ApplicationContext();
        var countryService = appContext.getCountryService();

        // Crear el estado
        var stateService = appContext.getStateService();
        try {
            var newState = stateService.newState(stateName, countryId);
            System.out.println("New State: " + newState.getId() + ", " + newState.getStateName() + "," + newState.getCountryId());
        } catch (RuntimeException e) {
            System.out.println("Error: No existe pais con ese ID");
        }
    }
}


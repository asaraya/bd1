package tec.bd.weather.cli.state;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.State;

@CommandLine.Command(name = "state-update", aliases = {"ustate"}, description = "Update an existing state")
public class UpdateStateCommand implements Runnable {

    @CommandLine.Parameters(index = "0", paramLabel = "<state-id>", description = "The state id")
    private int stateId;

    @CommandLine.Parameters(index = "1", paramLabel = "<state-name>", description = "The new state name")
    private String stateName;

    @CommandLine.Parameters(index = "2", paramLabel = "<country-id>", description = "The new country id")
    private int countryId;

    @Override
    public void run() {
        System.out.println("Update State Command");

        var appContext = new ApplicationContext();
        var stateService = appContext.getStateService();

        // Verificar si el estado existe antes de intentar actualizar
        var existingState = stateService.getStateById(stateId);
        if (existingState.isPresent()) {
            // Crear un nuevo objeto State con los nuevos valores
            var updatedState = new State(stateId, stateName, countryId);

            // Actualizar el estado
            stateService.updateState(updatedState);

            System.out.println("State updated successfully:");
            System.out.println("State Id: " + updatedState.getId() +
                    ", State Name: " + updatedState.getStateName() +
                    ", Country Id: " + updatedState.getCountryId());
        } else {
            System.out.println("State Id: " + stateId + " not found. Unable to update.");
        }
    }
}

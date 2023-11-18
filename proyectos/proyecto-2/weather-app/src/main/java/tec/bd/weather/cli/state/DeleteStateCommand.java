package tec.bd.weather.cli.state;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;

@CommandLine.Command(name = "state-delete", aliases = {"dstate"}, description = "Delete state")
public class DeleteStateCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<state Id>", description = "The state Id.", defaultValue = "0")
    private int stateId;

    @Override
    public void run() {

        var appContext = new ApplicationContext();
        var stateService = appContext.getStateService();

        try {
            stateService.removeByStateId(stateId);
            System.out.println("State Id: " + stateId + " deleted");
        } catch (Exception e) {
            System.err.println("State Idasda: " + stateId + " not deleted. Reason: " + e.getMessage());
        }
    }
}

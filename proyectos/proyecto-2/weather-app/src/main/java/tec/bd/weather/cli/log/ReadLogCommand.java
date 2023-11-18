package tec.bd.weather.cli.log;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.Log;

@CommandLine.Command(name = "Log", aliases = {"lr"}, description = "Read logs.")
public class ReadLogCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<numElements>", description = "Number of elements to display.", defaultValue = "0")
    private Integer numElements;

    @Override
    public void run() {
        System.out.println("Read Log. Number of Elements: " + numElements);

        var appContext = new ApplicationContext();
        var logService = appContext.getLogService();

        if (numElements == 0) {
            var logs = logService.getLogs();
            if (logs != null) {
                System.out.println("Logs");
                System.out.println("=============================================");
                for (Log log : logs) {
                    System.out.println(log.getId() + "\t" + log.getEntryText());
                }
            } else {
                System.out.println("No logs found or an error occurred while retrieving logs.");
            }
        } else {
            var logs = logService.getLogs(numElements);
            if (logs != null) {
                System.out.println("Logs");
                System.out.println("=============================================");
                for (Log log : logs) {
                    System.out.println(log.getId() + "\t" + log.getEntryText());
                }
            } else {
                System.out.println("No logs found or an error occurred while retrieving logs.");
            }
        }
    }
}

package markciurea.controller.activityCoordinator.commands;

import markciurea.controller.activityCoordinator.ControllerActivityCoordinator;
import markciurea.controller.helper.ICommand;
import markciurea.model.entities.thrashLocation.ThrashLocation;
import markciurea.model.server.ThrashLocationAPI;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GenerateCSVCommand implements ICommand {

    private final ControllerActivityCoordinator vm;

    public GenerateCSVCommand(ControllerActivityCoordinator vm) {
        this.vm = vm;
    }

    @Override
    public void execute() {
        String csvFile = "thrashLocations.csv";

        List<ThrashLocation> thrashLocationList = ThrashLocationAPI.getAllThrashLocations();
        try {
            BufferedWriter file = new BufferedWriter(new FileWriter((csvFile)));
            file.write("ID,ADDRESS_NAME,X,Y,EMPLOYEE_EMAIL\n");
            for (ThrashLocation thrashLocation : thrashLocationList) {
                file.write(thrashLocation.getId() + ","
                        + thrashLocation.getAddressName() + ","
                        + thrashLocation.getX() + ","
                        + thrashLocation.getY() + ","
                        + thrashLocation.getEmployee().getEmail() + "\n"
                );
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

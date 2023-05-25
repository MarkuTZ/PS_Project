package markciurea.controller.activityCoordinator.commands;

import markciurea.controller.activityCoordinator.ControllerActivityCoordinator;
import markciurea.controller.helper.ICommand;
import markciurea.model.entities.thrashLocation.ThrashLocation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GenerateTXTCommand implements ICommand {

    private final ControllerActivityCoordinator vm;

    public GenerateTXTCommand(ControllerActivityCoordinator vm) {
        this.vm = vm;
    }

    @Override
    public void execute() {
        String textFile = "thrashLocations.txt";

        // TODO
//        List<ThrashLocation> transportLineList = ThrashLocationRepository.getInstance().getAllThrashLocations();
        List<ThrashLocation> transportLineList = null;

        try {
            FileWriter fw = new FileWriter(textFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for (ThrashLocation thrashLocation : transportLineList) {
                bw.write("ID: " + thrashLocation.getId());
                bw.write(" ADDRESS_NAME: " + thrashLocation.getAddressName());
                bw.write(" X: " + thrashLocation.getX());
                bw.write(" Y: " + thrashLocation.getY());
                bw.write(" EMPLOYEE_EMAIL: " + thrashLocation.getEmployee().getEmail() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
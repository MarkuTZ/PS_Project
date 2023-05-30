package markciurea.controller.activityCoordinator.commands;

import markciurea.controller.activityCoordinator.ControllerActivityCoordinator;
import markciurea.controller.helper.ICommand;
import markciurea.model.entities.dto.UserShort;
import markciurea.model.entities.thrashLocation.ThrashLocation;
import markciurea.model.server.ThrashLocationAPI;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenerateJSONCommand implements ICommand {

    private final ControllerActivityCoordinator vm;

    public GenerateJSONCommand(ControllerActivityCoordinator vm) {
        this.vm = vm;
    }

    @Override
    public void execute() {
        String jsonFile = "thrashLocations.json";

        List<ThrashLocation> thrashLocationList = ThrashLocationAPI.getAllThrashLocations();
        try {
            JSONObject mainObj = new JSONObject();
            List<JSONObject> jsonList = new ArrayList<>();
            for (ThrashLocation thrashLocation : thrashLocationList) {
                JSONObject obj = new JSONObject();
                // Parse simple fields
                obj.put("id", thrashLocation.getId());
                obj.put("address_name", thrashLocation.getAddressName());
                obj.put("x", thrashLocation.getX());
                obj.put("y", thrashLocation.getY());
                // Parse employee
                UserShort employee = thrashLocation.getEmployee();
                JSONObject employeeObj = new JSONObject();
                employeeObj.put("id", employee.getId());
                employeeObj.put("email", employee.getEmail());
                employeeObj.put("role", employee.getRole());
                employeeObj.put("phone_nr", employee.getPhoneNr());

                obj.put("employee", employeeObj);
                jsonList.add(obj);
            }
            mainObj.put("thrash_locations", jsonList);

            FileWriter file = new FileWriter((jsonFile));
            file.write(mainObj.toString());
            file.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
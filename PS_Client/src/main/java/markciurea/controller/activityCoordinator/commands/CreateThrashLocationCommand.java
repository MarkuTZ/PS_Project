package markciurea.controller.activityCoordinator.commands;

import markciurea.controller.activityCoordinator.ControllerActivityCoordinator;
import markciurea.controller.helper.ICommand;
import markciurea.model.entities.thrashLocation.ThrashLocation;
import markciurea.model.entities.user.User;
import markciurea.view.ShowError;

import java.util.ArrayList;
import java.util.List;

public class CreateThrashLocationCommand implements ICommand {

    private final ControllerActivityCoordinator controller;

    public CreateThrashLocationCommand(ControllerActivityCoordinator controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        String email = controller.getEmployeeEmail();
        String address = controller.getAddress();
        String xCord = controller.getXCoord();
        String yCord = controller.getYCoord();

        List<String> invalidFields = new ArrayList<>();
        if (email == null || email.isBlank()) invalidFields.add("Employee email");
        if (address == null || address.isBlank()) invalidFields.add("Address");
        if (xCord == null || xCord.isBlank()) invalidFields.add("X coord");
        if (yCord == null || yCord.isBlank()) invalidFields.add("Y coord");
        if (!invalidFields.isEmpty()) {
            ShowError.showError(String.join(", ", invalidFields) + " can't be null!");
            return;
        }
        long x;
        long y;
        try {
            x = Long.parseLong(xCord);
            y = Long.parseLong(yCord);

        } catch (NumberFormatException e) {
            ShowError.showError("Enter valid numbers for X or Y.");
            return;
        }

        // TODO
//        Employee user = (Employee) UserRepository.getInstance().getUserByEmail(email);
        User user = null;
        if (user == null) {
            ShowError.showError("User with email: " + email + " doesn't exist!");
            return;
        }
        ThrashLocation newThrashLocation = new ThrashLocation();
        newThrashLocation.setX(x);
        newThrashLocation.setY(y);
        newThrashLocation.setAddressName(address);

        // TODO
//        user.addThrashLocation(newThrashLocation);
//        UserRepository.getInstance().saveUser(user);
        controller.getTableModel().addThrashLocation(newThrashLocation);
    }
}

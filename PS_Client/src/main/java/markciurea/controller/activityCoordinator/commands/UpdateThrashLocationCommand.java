package markciurea.controller.activityCoordinator.commands;

import markciurea.controller.activityCoordinator.ControllerActivityCoordinator;
import markciurea.controller.helper.ICommand;
import markciurea.model.entities.thrashLocation.ThrashLocation;
import markciurea.model.entities.user.Role;
import markciurea.model.entities.user.User;
import markciurea.view.ShowError;

public class UpdateThrashLocationCommand implements ICommand {

    private final ControllerActivityCoordinator controller;

    public UpdateThrashLocationCommand(ControllerActivityCoordinator controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        Integer row = controller.getRow();
        Integer column = controller.getColumn();
        Long id = (Long) controller.getValueAt(row, 0);
        Object valueToUpdate = controller.getValueAt(row, column);

        // TODO
//        ThrashLocation thrashLocation = ThrashLocationRepository.getInstance().getThrashLocationById(id);
        ThrashLocation thrashLocation = null;
        if (thrashLocation == null) {
            ShowError.showError("COULDN'T UPDATE THRASH LOCATION WITH ID = " + id);
            return;
        }
        switch (column) {
            case 1 -> thrashLocation.setAddressName((String) valueToUpdate);
            case 2 -> thrashLocation.setX(Long.parseLong(valueToUpdate.toString()));
            case 3 -> thrashLocation.setY(Long.parseLong(valueToUpdate.toString()));
            case 4 -> {
                // TODO
//                User employee = UserRepository.getInstance().getUserByEmail((String) valueToUpdate);
                User employee = null;
                if (employee == null || !employee.getRole().equals(Role.EMPLOYEE)) {
                    ShowError.showError(valueToUpdate + " is not an EMPLOYEE");
                    controller.refreshThrashTableModel();
                    return;
                }
                // TODO
//                ((Employee) employee).addThrashLocation(thrashLocation);
            }
        }
        // TODO
//        ThrashLocationRepository.getInstance().saveThrashLocation(thrashLocation);
        controller.refreshThrashTableModel();
    }
}

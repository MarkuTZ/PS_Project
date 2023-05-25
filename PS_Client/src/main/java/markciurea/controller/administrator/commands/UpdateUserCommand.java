package markciurea.controller.administrator.commands;

import markciurea.controller.administrator.ControllerAdministrator;
import markciurea.controller.helper.ICommand;
import markciurea.model.entities.user.User;
import markciurea.view.ShowError;

public class UpdateUserCommand implements ICommand {

    private final ControllerAdministrator controller;

    public UpdateUserCommand(ControllerAdministrator controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        Integer row = controller.getRow();
        Integer column = controller.getColumn();
        Long id = (Long) controller.getValueAt(row, 0);
        Object valueToUpdate = controller.getValueAt(row, column);

        // TODO
//        User user = UserRepository.getInstance().getUserById(id);
        User user = null;
        if (user == null) {
            ShowError.showError("COULDN'T UPDATE USER WITH ID = " + id);
            return;
        }
        switch (column) {
            case 2 -> user.setEmail((String) valueToUpdate);
            case 3 -> user.setPassword((String) valueToUpdate);
            case 4 -> user.setPhoneNr((String) valueToUpdate);
        }
        // TODO
//        UserRepository.getInstance().saveUser(user);
        controller.refreshEmployeeTableModel();
    }
}

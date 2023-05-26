package markciurea.controller.administrator.commands;

import markciurea.controller.administrator.ControllerAdministrator;
import markciurea.controller.helper.ICommand;
import markciurea.model.entities.user.User;
import markciurea.model.server.UserAPI;

import java.util.ArrayList;
import java.util.List;

public class DeleteUsersCommand implements ICommand {

    private final ControllerAdministrator controller;

    public DeleteUsersCommand(ControllerAdministrator controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        int[] indexes = controller.getSelectedRows();
        List<Long> ids = new ArrayList<>();

        for (int index : indexes) {
            ids.add((Long) controller.getValueAt(index, 0));
        }
        ids.forEach(UserAPI::deleteUserById);
        controller.refreshEmployeeTableModel();
    }
}

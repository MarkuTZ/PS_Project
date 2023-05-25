package markciurea.controller.administrator.commands;

import markciurea.controller.administrator.ControllerAdministrator;
import markciurea.controller.helper.ICommand;
import markciurea.model.entities.dto.UserShort;
import markciurea.model.entities.user.Role;
import markciurea.model.server.UserAPI;
import markciurea.view.ShowError;

import java.util.ArrayList;
import java.util.List;

public class CreateUserCommand implements ICommand {

    private final ControllerAdministrator controller;

    public CreateUserCommand(ControllerAdministrator controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        String email = controller.getEmail();
        String password = controller.getPassword();
        Role role = Role.valueOf(controller.getRole());

        List<String> invalidFields = new ArrayList<>();
        if (email == null || email.isBlank()) invalidFields.add("Email");
        if (password == null || password.isBlank()) invalidFields.add("Password");
        if (!invalidFields.isEmpty()) {
            ShowError.showError(String.join(", ", invalidFields) + " can't be null");
            return;
        }

        UserShort newUser = new UserShort();
        newUser.setRole(role);
        newUser.setEmail(email);
        newUser.setPassword(password);

        UserAPI.createUser(newUser);
        controller.refreshEmployeeTableModel();
    }
}

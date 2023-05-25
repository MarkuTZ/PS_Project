package markciurea.controller.login.commands;

import markciurea.controller.activityCoordinator.ControllerActivityCoordinator;
import markciurea.controller.administrator.ControllerAdministrator;
import markciurea.controller.employee.ControllerEmployee;
import markciurea.controller.helper.ICommand;
import markciurea.controller.login.ControllerLogin;
import markciurea.model.entities.user.User;
import markciurea.model.server.UserAPI;
import markciurea.view.ShowError;

public class LoginCommand implements ICommand {

    private final ControllerLogin controllerLogin;

    public LoginCommand(ControllerLogin controllerLogin) {
        this.controllerLogin = controllerLogin;
    }

    @Override
    public void execute() {
        // TODO
        User loggedIn = UserAPI.logIn(controllerLogin.getUserField(), controllerLogin.getPassField());
        if (loggedIn == null) {
            ShowError.showError("USER NOT FOUND/ BAD CREDENTIALS");
            throw new RuntimeException("USER NOT FOUND/ BAD CREDENTIALS!");
        }
        switch (loggedIn.getRole()) {
            case EMPLOYEE -> {
                new ControllerEmployee(loggedIn);
            }
            case ADMINISTRATOR -> {
                new ControllerAdministrator(loggedIn);
            }
            case ACTIVITY_COORDINATOR -> {
                new ControllerActivityCoordinator(loggedIn);
            }
        }
    }
}

package markciurea.controller.employee.commands;

import markciurea.controller.helper.ControllerRoute;
import markciurea.controller.helper.DrawRoute;
import markciurea.controller.helper.ICommand;

import javax.swing.*;
import java.awt.*;

public class ShowRouteCommand implements ICommand {

    private final ControllerRoute controllerRoute;
    private JFrame drawFrame = null;

    public ShowRouteCommand(ControllerRoute controllerRoute) {
        this.controllerRoute = controllerRoute;
    }

    @Override
    public void execute() {
        // 1. Create new JFrame
        if (drawFrame != null) {
            drawFrame.dispose();
        }
        drawFrame = new JFrame("Route for: " + controllerRoute.getEmailForRoute());

        // 2. Get points for route and create DrawRoute component
        DrawRoute drawRoute = new DrawRoute(controllerRoute.getRouteCheckpoints());
        drawFrame.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        drawFrame.add(drawRoute);
        drawFrame.setLayout(null);
        drawFrame.setSize(500, 500);
        drawFrame.setVisible(true);
    }

}

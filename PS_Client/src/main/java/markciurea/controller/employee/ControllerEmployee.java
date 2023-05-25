package markciurea.controller.employee;

import markciurea.controller.employee.commands.ShowRouteCommand;
import markciurea.controller.helper.ControllerRoute;
import markciurea.controller.helper.ICommand;
import markciurea.controller.helper.RouteHelperService;
import markciurea.controller.tableModels.ThrashTableModel;
import markciurea.model.entities.thrashLocation.ThrashLocation;
import markciurea.model.entities.user.Role;
import markciurea.model.entities.user.User;
import markciurea.model.language.Language;
import markciurea.model.server.ThrashLocationAPI;
import markciurea.view.EmployeeGUI;
import org.apache.commons.lang3.tuple.Pair;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class ControllerEmployee implements ControllerRoute {

    private final EmployeeGUI gui;
    private final User loggedIn;

    private final ICommand showRoute = new ShowRouteCommand(this);

    public ControllerEmployee(User loggedIn) {
        this.loggedIn = loggedIn;
        if (loggedIn == null || !loggedIn.getRole().equals(Role.EMPLOYEE)) {
            throw new RuntimeException(loggedIn.getEmail() + " is not an EMPLOYEE");
        }
        gui = new EmployeeGUI(loggedIn.getEmail());

        // Instantiate table
        List<ThrashLocation> thrashLocationList = ThrashLocationAPI.getAllThrashLocationsForEmail(loggedIn.getEmail());
        gui.getThrashLocationsTable().setModel(new ThrashTableModel(thrashLocationList, false));
        gui.getShowRoute().addActionListener(e -> showRoute.execute());

        // Observer Language
        Language.getInstance().attachObserver(gui);
        // Logout when cross is clicked
        gui.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                gui.dispose();
                Language.getInstance().detachObserver(gui);
            }
        });
        // Logout on ESCAPE
        gui.getContentPane().registerKeyboardAction(e -> {
            gui.dispose();
            Language.getInstance().detachObserver(gui);
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        gui.update();
    }

    @Override
    public List<Pair<Integer, Integer>> getRouteCheckpoints() {
        return RouteHelperService.computeRoute(loggedIn);
    }

    @Override
    public String getEmailForRoute() {
        return loggedIn.getEmail();
    }


}

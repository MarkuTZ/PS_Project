package markciurea.controller.activityCoordinator;

import markciurea.controller.activityCoordinator.commands.*;
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
import markciurea.model.server.UserAPI;
import markciurea.view.ActivityCoordinatorGUI;
import markciurea.view.ShowError;
import org.apache.commons.lang3.tuple.Pair;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class ControllerActivityCoordinator implements ControllerRoute {

    private final User loggedIn;
    private final ActivityCoordinatorGUI gui;

    private Integer row;
    private Integer column;
    private int[] selectedRows;

    private final ICommand createThrashLocationCommand = new CreateThrashLocationCommand(this);
    private final ICommand deleteThrashLocationsCommand = new DeleteThrashLocationsCommand(this);
    private final ICommand updateThrashLocationCommand = new UpdateThrashLocationCommand(this);
    private final ICommand showRouteCommand = new ShowRouteCommand(this);
    private final ICommand generateCSV = new GenerateCSVCommand(this);
    private final ICommand generateXML = new GenerateXMLCommand(this);
    private final ICommand generateJSON = new GenerateJSONCommand(this);
    private final ICommand generateTXT = new GenerateTXTCommand(this);
    private final ICommand generateCharts = new GenerateChartsCommand(this);

    public ControllerActivityCoordinator(User loggedIn) {
        this.loggedIn = loggedIn;
        if (loggedIn == null || !loggedIn.getRole().equals(Role.ACTIVITY_COORDINATOR)) {
            throw new RuntimeException(loggedIn.getEmail() + " is not an ACTIVITY COORDINATOR");
        }
        gui = new ActivityCoordinatorGUI(loggedIn.getEmail());

        // Instantiate the comboBoxModel
        DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<>();
        List<User> employees = UserAPI.getAllUsers(List.of(Role.EMPLOYEE));
        defaultComboBoxModel.addAll(employees.stream().map(User::getEmail).toList());
        defaultComboBoxModel.setSelectedItem(employees.get(0).getEmail());
        gui.getEmployeeComboBox().setModel(defaultComboBoxModel);

        // Instantiate the ThrashTableModel
        refreshThrashTableModel();

        // Link buttons to commands
        gui.getShowRouteButton().addActionListener(e -> showRouteCommand.execute());
        gui.getCreateButton().addActionListener(e -> createThrashLocationCommand.execute());

        // Delete users on BACKSPACE
        gui.getThrashLocationsTable().registerKeyboardAction(e -> deleteThrashLocationsCommand.execute(), KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        gui.getThrashLocationsTable().getSelectionModel().addListSelectionListener(e -> setSelectedRows(gui.getThrashLocationsTable().getSelectedRows()));

        // Generate reports buttons
        gui.getCSVButton().addActionListener(e -> generateCSV.execute());
        gui.getXMLButton().addActionListener(e -> generateXML.execute());
        gui.getJSONButton().addActionListener(e -> generateJSON.execute());
        gui.getTXTButton().addActionListener(e -> generateTXT.execute());

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

        generateCharts.execute();

        gui.update();
        gui.pack();
    }

    public void refreshThrashTableModel() {
        List<ThrashLocation> thrashLocationList = ThrashLocationAPI.getAllThrashLocations();
        ThrashTableModel thrashTableModel = new ThrashTableModel(thrashLocationList, true);
        thrashTableModel.addTableModelListener(e -> {
            row = e.getFirstRow();
            column = e.getColumn();
            if (row >= 0) updateThrashLocationCommand.execute();
        });
        gui.getThrashLocationsTable().setModel(thrashTableModel);
    }

    public String getAddress() {
        return gui.getAddressField().getText();
    }

    public String getXCoord() {
        return gui.getXField().getText();
    }

    public String getYCoord() {
        return gui.getYField().getText();
    }

    public String getEmployeeEmail() {
        return gui.getEmployeeComboBox().getModel().getSelectedItem() != null ? gui.getEmployeeComboBox().getModel().getSelectedItem().toString() : null;
    }

    public ThrashTableModel getTableModel() {
        return (ThrashTableModel) gui.getThrashLocationsTable().getModel();
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public Object getValueAt(int row, int column) {
        return gui.getThrashLocationsTable().getModel().getValueAt(row, column);
    }

    public int[] getSelectedRows() {
        return selectedRows;
    }

    public void setSelectedRows(int[] selectedRows) {
        this.selectedRows = selectedRows;
    }

    public JPanel getChartPanel1() {
        return gui.getChartPanel1();
    }

    public JPanel getChartPanel2() {
        return gui.getChartPanel2();
    }

    @Override
    public List<Pair<Integer, Integer>> getRouteCheckpoints() {
        User user = UserAPI.getUserByEmail(getEmailForRoute());
        return RouteHelperService.computeRoute(user);
    }

    @Override
    public String getEmailForRoute() {
        if (selectedRows == null || selectedRows.length == 0) {
            ShowError.showError("You must select a row first!");
            throw new RuntimeException("No selected row");
        }
        return (String) getValueAt(selectedRows[0], 4);
    }

}

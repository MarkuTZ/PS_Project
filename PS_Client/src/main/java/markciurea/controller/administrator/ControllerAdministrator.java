package markciurea.controller.administrator;

import markciurea.controller.administrator.commands.CreateUserCommand;
import markciurea.controller.administrator.commands.DeleteUsersCommand;
import markciurea.controller.administrator.commands.UpdateUserCommand;
import markciurea.controller.helper.ICommand;
import markciurea.controller.tableModels.EmployeeTableModel;
import markciurea.model.entities.user.Role;
import markciurea.model.entities.user.User;
import markciurea.model.language.Language;
import markciurea.model.server.UserAPI;
import markciurea.view.AdministratorGUI;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class ControllerAdministrator {

    private final AdministratorGUI gui;
    private final User loggedIn;

    private Integer row;
    private Integer column;
    private int[] selectedRows;

    private final ICommand createUserCommand = new CreateUserCommand(this);
    private final ICommand deleteUsersCommand = new DeleteUsersCommand(this);
    private final ICommand updateUserCommand = new UpdateUserCommand(this);

    public ControllerAdministrator(User loggedIn) {
        this.loggedIn = loggedIn;
        if (loggedIn == null || !loggedIn.getRole().equals(Role.ADMINISTRATOR)) {
            throw new RuntimeException(loggedIn.getEmail() + " is not an ADMINISTRATOR");
        }
        gui = new AdministratorGUI(loggedIn.getEmail());

        // Instantiate the comboBoxModel
        DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<>();
        defaultComboBoxModel.addAll(Arrays.stream(Role.values()).map(Role::toString).toList());
        defaultComboBoxModel.setSelectedItem(Role.EMPLOYEE.toString());
        gui.getRoleComboBox().setModel(defaultComboBoxModel);

        gui.getCreateUserButton().addActionListener(e -> createUserCommand.execute());
        // Delete users on BACKSPACE
        gui.getEmployeesTable().registerKeyboardAction(e -> deleteUsersCommand.execute(), KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        gui.getEmployeesTable().getSelectionModel().addListSelectionListener(e -> setSelectedRows(gui.getEmployeesTable().getSelectedRows()));

        gui.getAdminsCheckBox().addActionListener(e -> refreshEmployeeTableModel());
        gui.getCoordinatorsCheckBox().addActionListener(e -> refreshEmployeeTableModel());
        gui.getEmployeesCheckBox().addActionListener(e -> refreshEmployeeTableModel());

        // Instantiate the EmployeeTableModel
        refreshEmployeeTableModel();

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

    public void refreshEmployeeTableModel() {
        List<User> userList = UserAPI.getAllUsers();
        List<Role> showRoles = new ArrayList<>();
        if (getShowAdmins()) {
            showRoles.add(Role.ADMINISTRATOR);
        }
        if (getShowCoordinators()) {
            showRoles.add(Role.ACTIVITY_COORDINATOR);
        }
        if (getShowEmployees()) {
            showRoles.add(Role.EMPLOYEE);
        }
        EmployeeTableModel employeeTableModel = new EmployeeTableModel(userList.stream().filter(user -> showRoles.contains(user.getRole())).toList(), true);
        employeeTableModel.addTableModelListener(e -> {
            row = e.getFirstRow();
            column = e.getColumn();
            if (row >= 0)
                updateUserCommand.execute();
        });
        gui.getEmployeesTable().setModel(employeeTableModel);

    }

    public String getRole() {
        return gui.getRoleComboBox().getModel().getSelectedItem() != null ? gui.getRoleComboBox().getModel().getSelectedItem().toString() : null;
    }

    public String getEmail() {
        return gui.getEmailField().getText();
    }

    public String getPassword() {
        return gui.getPasswordField().getText();
    }

    public Boolean getShowAdmins() {
        return gui.getAdminsCheckBox().isSelected();
    }

    public Boolean getShowCoordinators() {
        return gui.getCoordinatorsCheckBox().isSelected();
    }

    public Boolean getShowEmployees() {
        return gui.getEmployeesCheckBox().isSelected();
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public int[] getSelectedRows() {
        return selectedRows;
    }

    public void setSelectedRows(int[] selectedRows) {
        this.selectedRows = selectedRows;
    }

    public Object getValueAt(int row, int column) {
        return gui.getEmployeesTable().getModel().getValueAt(row, column);
    }

}

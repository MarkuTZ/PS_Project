package markciurea.view;

import markciurea.controller.tableModels.EmployeeTableModel;
import markciurea.model.language.Language;
import markciurea.model.language.Observer;

import javax.swing.*;
import java.util.ResourceBundle;

public class AdministratorGUI extends JFrame implements Observer {
    private JPanel contentPane;
    private JTable employeesTable;
    private JScrollPane tableScrollPane;
    private JPanel newUserPanel;
    private JTextField emailField;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JTextField passwordField;
    private JLabel roleLabel;
    private JComboBox<String> roleComboBox;
    private JButton createUserButton;
    private JCheckBox adminsCheckBox;
    private JCheckBox coordinatorsCheckBox;
    private JCheckBox employeesCheckBox;

    public AdministratorGUI(String loggedInEmail) {
        setContentPane(contentPane);

        contentPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), loggedInEmail));

        pack();
        setVisible(true);
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public JTable getEmployeesTable() {
        return employeesTable;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public JComboBox<String> getRoleComboBox() {
        return roleComboBox;
    }

    public JButton getCreateUserButton() {
        return createUserButton;
    }

    public JCheckBox getAdminsCheckBox() {
        return adminsCheckBox;
    }

    public JCheckBox getCoordinatorsCheckBox() {
        return coordinatorsCheckBox;
    }

    public JCheckBox getEmployeesCheckBox() {
        return employeesCheckBox;
    }

    @Override
    public void update() {
        ResourceBundle rb = Language.getInstance().getRb();
        setTitle(rb.getString("adminViewName"));
        passwordLabel.setText(rb.getString("password") + ":");
        roleLabel.setText(rb.getString("role") + ":");
        createUserButton.setText(rb.getString("createUserButton"));
        adminsCheckBox.setText(rb.getString("admins"));
        coordinatorsCheckBox.setText(rb.getString("coordinators"));
        employeesCheckBox.setText(rb.getString("employee"));
        ((EmployeeTableModel) employeesTable.getModel()).updateLanguage();
    }

}

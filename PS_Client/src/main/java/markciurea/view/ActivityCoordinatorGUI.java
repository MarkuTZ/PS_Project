package markciurea.view;

import markciurea.controller.tableModels.ThrashTableModel;
import markciurea.model.language.Language;
import markciurea.model.language.Observer;

import javax.swing.*;
import java.util.ResourceBundle;

public class ActivityCoordinatorGUI extends JFrame implements Observer {
    private JPanel contentPane;
    private JScrollPane tableScrollPane;
    private JTable thrashLocationsTable;
    private JPanel newThrashLocationPanel;
    private JTextField addressField;
    private JTextField xField;
    private JTextField yField;
    private JComboBox<String> employeeComboBox;
    private JButton createButton;
    private JButton showRouteButton;
    private JButton CSVButton;
    private JButton XMLButton;
    private JButton JSONButton;
    private JButton TXTButton;
    private JLabel addressLabel;
    private JLabel employeeLabel;
    private JPanel reportPanel;
    private JPanel cPanel1;
    private JPanel cPanel2;

    public ActivityCoordinatorGUI(String loggedInEmail) {
        setContentPane(contentPane);

        contentPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), loggedInEmail));

        pack();
        setVisible(true);
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public JTable getThrashLocationsTable() {
        return thrashLocationsTable;
    }

    public JTextField getAddressField() {
        return addressField;
    }

    public JTextField getXField() {
        return xField;
    }

    public JTextField getYField() {
        return yField;
    }

    public JComboBox<String> getEmployeeComboBox() {
        return employeeComboBox;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public JButton getShowRouteButton() {
        return showRouteButton;
    }

    public JButton getCSVButton() {
        return CSVButton;
    }

    public JButton getXMLButton() {
        return XMLButton;
    }

    public JButton getJSONButton() {
        return JSONButton;
    }

    public JButton getTXTButton() {
        return TXTButton;
    }

    public JPanel getChartPanel1() {
        return cPanel1;
    }

    public JPanel getChartPanel2() {
        return cPanel2;
    }

    @Override
    public void update() {
        ResourceBundle rb = Language.getInstance().getRb();
        setTitle(rb.getString("coordinatorViewName"));
        showRouteButton.setText(rb.getString("showRouteButton"));
        addressLabel.setText(rb.getString("address") + ':');
        employeeLabel.setText(rb.getString("employee") + ':');
        reportPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), rb.getString("generateReportBorder")));
        createButton.setText(rb.getString("createThrashButton"));
        ((ThrashTableModel) thrashLocationsTable.getModel()).updateLanguage();
    }

}

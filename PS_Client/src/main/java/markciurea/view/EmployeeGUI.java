package markciurea.view;

import markciurea.controller.tableModels.ThrashTableModel;
import markciurea.model.language.Language;
import markciurea.model.language.Observer;

import javax.swing.*;
import java.util.ResourceBundle;

public class EmployeeGUI extends JFrame implements Observer {
    private JPanel contentPane;
    private JTable thrashLocationsTable;
    private JButton showRoute;

    public EmployeeGUI(String loggedInEmail) {
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

    public JButton getShowRoute() {
        return showRoute;
    }

    @Override
    public void update() {
        ResourceBundle rb = Language.getInstance().getRb();
        setTitle(rb.getString("employeeViewName"));
        showRoute.setText(rb.getString("showRouteButton"));
        ((ThrashTableModel) thrashLocationsTable.getModel()).updateLanguage();
    }

}

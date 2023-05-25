package markciurea.view;

import javax.swing.*;

public class ShowError {

    public static void showError(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

}

package markciurea.view;

import markciurea.model.language.Language;
import markciurea.model.language.Observer;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

public class LoginGUI extends JFrame implements Observer {
    private JPanel contentPane;
    private JPasswordField passwordField;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JTextField emailField;
    private JButton loginButton;
    private JComboBox<String> languageCombobox;
    private JLabel chooseLanguageLabel;

    public LoginGUI() {
        setContentPane(contentPane);

        // close on ESCAPE
        contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pack();
        setVisible(true);
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JComboBox<String> getLanguageCombobox() {
        return languageCombobox;
    }

    @Override
    public void update() {
        ResourceBundle rb = Language.getInstance().getRb();
        chooseLanguageLabel.setText(rb.getString("chooseLanguageLabel"));
        setTitle(rb.getString("loginViewName"));
        passwordLabel.setText(rb.getString("password") + ":");
    }

}

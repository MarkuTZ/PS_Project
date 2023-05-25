package markciurea.controller.login;

import markciurea.controller.helper.ICommand;
import markciurea.controller.login.commands.LoginCommand;
import markciurea.model.language.LANGUAGES;
import markciurea.model.language.Language;
import markciurea.view.LoginGUI;

import javax.swing.*;
import java.util.Arrays;

public class ControllerLogin {

    private final LoginGUI gui;
    private final ICommand loginCommand = new LoginCommand(this);

    public ControllerLogin() {
        gui = new LoginGUI();
        gui.getLoginButton().addActionListener(e -> {
            loginCommand.execute();
        });

        // Instantiate the comboBoxModel
        DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<>();
        defaultComboBoxModel.addAll(Arrays.stream(LANGUAGES.values()).map(LANGUAGES::toString).toList());
        defaultComboBoxModel.setSelectedItem(LANGUAGES.ENGLISH.toString());
        gui.getLanguageCombobox().setModel(defaultComboBoxModel);

        Language.getInstance().attachObserver(gui);
        // Attach Language modification to the comboBox
        gui.getLanguageCombobox().addActionListener(e -> {
            if (gui.getLanguageCombobox().getModel().getSelectedItem() == null)
                return;

            LANGUAGES selectedLanguage = LANGUAGES.valueOf(
                    gui.getLanguageCombobox().getModel().getSelectedItem().toString()
            );
            Language.getInstance().setCurrentLanguage(selectedLanguage);
        });
        gui.update();
    }

    public String getUserField() {
        return gui.getEmailField().getText();
    }

    public String getPassField() {
        return new String(gui.getPasswordField().getPassword());
    }

    public static void main(String[] args) {
        new ControllerLogin();
    }

}

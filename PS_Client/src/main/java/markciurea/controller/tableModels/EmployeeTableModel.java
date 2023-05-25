package markciurea.controller.tableModels;

import markciurea.model.entities.user.Role;
import markciurea.model.entities.user.User;
import markciurea.model.language.Language;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeTableModel extends AbstractTableModel {

    private final List<User> userList;

    private List<String> header = List.of("ID", "ROLE", "E-MAIL", "PASSWORD", "PHONE NR.");

    private final Boolean editable;

    public EmployeeTableModel(List<User> userList, Boolean editable) {
        this.userList = userList;
        this.editable = editable;
    }

    public void updateLanguage() {
        ResourceBundle rb = Language.getInstance().getRb();
        header = List.of("ID", rb.getString("role").toUpperCase(), "E-MAIL", rb.getString("password").toUpperCase(), rb.getString("phoneNr").toUpperCase());
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        return userList.size();
    }

    @Override
    public int getColumnCount() {
        return header.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0)
            return null;
        User user = userList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> user.getId();
            case 1 -> user.getRole();
            case 2 -> user.getEmail();
            case 3 -> user.getPassword();
            case 4 -> user.getPhoneNr();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return header.get(column);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Long.class;
            case 1 -> Role.class;
            case 2, 3, 4 -> String.class;
            default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (editable && columnIndex >= 2);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        User u = userList.get(rowIndex);
        switch (columnIndex) {
            case 2 -> u.setEmail((String) aValue);
            case 3 -> u.setPassword((String) aValue);
            case 4 -> u.setPhoneNr((String) aValue);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void addUser(User u) {
        userList.add(u);
        fireTableDataChanged();
    }

    public void removeUser(User u) {
        userList.remove(u);
        fireTableDataChanged();
    }
}

package markciurea.controller.tableModels;

import markciurea.model.entities.thrashLocation.ThrashLocation;
import markciurea.model.language.Language;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ResourceBundle;

public class ThrashTableModel extends AbstractTableModel {

    private final List<ThrashLocation> thrashLocationList;

    private List<String> header = List.of("ID", "Address", "X", "Y", "Employee");

    private final Boolean editable;

    public ThrashTableModel(List<ThrashLocation> thrashLocationList, Boolean editable) {
        this.thrashLocationList = thrashLocationList;
        this.editable = editable;
    }

    public void updateLanguage() {
        ResourceBundle rb = Language.getInstance().getRb();
        header = List.of("ID", rb.getString("address").toUpperCase(), "X", "Y", rb.getString("employee").toUpperCase());
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        return thrashLocationList.size();
    }

    @Override
    public int getColumnCount() {
        return header.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0)
            return null;
        ThrashLocation thrashLocation = thrashLocationList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> thrashLocation.getId();
            case 1 -> thrashLocation.getAddressName();
            case 2 -> thrashLocation.getX();
            case 3 -> thrashLocation.getY();
            case 4 -> thrashLocation.getEmployee().getEmail();
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
            case 0, 2, 3 -> Long.class;
            case 1, 4 -> String.class;
            default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (editable && columnIndex >= 1);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ThrashLocation thrashLocation = thrashLocationList.get(rowIndex);
        switch (columnIndex) {
            case 1 -> thrashLocation.setAddressName((String) aValue);
            case 2 -> thrashLocation.setX((Long) aValue);
            case 3 -> thrashLocation.setY((Long) aValue);
            case 4 -> thrashLocation.getEmployee().setEmail((String) aValue);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void addThrashLocation(ThrashLocation thrashLocation) {
        thrashLocationList.add(thrashLocation);
        fireTableDataChanged();
    }

    public void removeThrashLocation(ThrashLocation thrashLocation) {
        thrashLocationList.remove(thrashLocation);
        fireTableDataChanged();
    }
}

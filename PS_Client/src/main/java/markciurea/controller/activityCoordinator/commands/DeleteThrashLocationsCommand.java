package markciurea.controller.activityCoordinator.commands;

import markciurea.controller.activityCoordinator.ControllerActivityCoordinator;
import markciurea.controller.helper.ICommand;
import markciurea.model.entities.thrashLocation.ThrashLocation;

import java.util.ArrayList;
import java.util.List;

public class DeleteThrashLocationsCommand implements ICommand {

    private final ControllerActivityCoordinator controller;

    public DeleteThrashLocationsCommand(ControllerActivityCoordinator controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        int[] indexes = controller.getSelectedRows();
        List<Long> ids = new ArrayList<>();

        for (int index : indexes) {
            ids.add((Long) controller.getValueAt(index, 0));
        }

        // TODO
//        List<ThrashLocation> toDelete = ThrashLocationRepository.getInstance().getAllThrashLocationsWithIdInList(ids);
        List<ThrashLocation> toDelete = null;
        toDelete.forEach(thrashLocation -> {
            // TODO
//            ThrashLocationRepository.getInstance().deleteThrashLocation(thrashLocation);
        });
        controller.refreshThrashTableModel();
    }
}

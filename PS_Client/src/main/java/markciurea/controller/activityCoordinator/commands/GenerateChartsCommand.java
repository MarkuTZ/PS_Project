package markciurea.controller.activityCoordinator.commands;

import markciurea.controller.activityCoordinator.ControllerActivityCoordinator;
import markciurea.controller.helper.DrawCharts;
import markciurea.controller.helper.ICommand;
import markciurea.model.entities.thrashLocation.ThrashLocation;
import markciurea.model.server.ThrashLocationAPI;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class GenerateChartsCommand implements ICommand {

    private final ControllerActivityCoordinator controller;

    public GenerateChartsCommand(ControllerActivityCoordinator controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        List<ThrashLocation> thrashLocationList = ThrashLocationAPI.getAllThrashLocations();
        AtomicReference<Long> sec1 = new AtomicReference<>(0L);
        AtomicReference<Long> sec2 = new AtomicReference<>(0L);
        AtomicReference<Long> sec3 = new AtomicReference<>(0L);
        AtomicReference<Long> sec4 = new AtomicReference<>(0L);
        // The middle of the city is in 250, 250
        thrashLocationList.forEach(thrashLocation -> {
            // Sector 1
            if (thrashLocation.getX() <= 250 && thrashLocation.getY() <= 250)
                sec1.getAndSet(sec1.get() + 1);
            if (thrashLocation.getX() > 250 && thrashLocation.getY() <= 250)
                sec2.getAndSet(sec2.get() + 1);
            if (thrashLocation.getX() <= 250 && thrashLocation.getY() > 250)
                sec3.getAndSet(sec3.get() + 1);
            if (thrashLocation.getX() > 250 && thrashLocation.getY() > 250)
                sec4.getAndSet(sec4.get() + 1);
        });
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        dataset.setValue("Sec. I", sec1.get());
        dataset.setValue("Sec. II", sec2.get());
        dataset.setValue("Sec. III", sec3.get());
        dataset.setValue("Sec. IV", sec4.get());

        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        dataset2.addValue(sec1.get(), "Sec. I", "");
        dataset2.addValue(sec2.get(), "Sec. II", "");
        dataset2.addValue(sec3.get(), "Sec. III", "");
        dataset2.addValue(sec4.get(), "Sec. IV", "");

        DrawCharts drawCharts = new DrawCharts(dataset, dataset2);
        drawCharts.createPanel();
    }
}

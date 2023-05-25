package markciurea.controller.helper;

import org.apache.commons.lang3.tuple.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawRoute extends JPanel {

    private static final Integer WIDTH = 10;
    private static final Integer HEIGHT = 10;
    private final List<Pair<Integer, Integer>> points;

    public DrawRoute(List<Pair<Integer, Integer>> points) {
        setBackground(Color.GRAY);
        setSize(500, 500);
        this.points = points;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Draw polyline first
        g.setColor(Color.BLUE);
        g.drawPolyline(
                points.stream().mapToInt(pair -> pair.getLeft() + WIDTH / 2).toArray(),
                points.stream().mapToInt(pair -> pair.getRight() + HEIGHT / 2).toArray(),
                points.size()
        );

        // Draw every point
        for (int i = 0; i < points.size(); i++) {
            Pair<Integer, Integer> currPoint = points.get(i);
            Color color;
            if (i == 0) {
                // color start
                color = Color.GREEN;
            } else if (i == points.size() - 1) {
                // color end
                color = Color.RED;
            } else {
                color = Color.BLACK;
            }
            g.setColor(color);
            g.fillOval(currPoint.getLeft(), currPoint.getRight(), WIDTH, HEIGHT);
        }
    }
}

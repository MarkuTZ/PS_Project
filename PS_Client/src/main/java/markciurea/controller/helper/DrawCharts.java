package markciurea.controller.helper;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class DrawCharts extends JFrame {
    private static JFrame frame;

    private JFreeChart chart;
    private JFreeChart chart2;
    private ChartPanel chartPanel;
    private ChartPanel chartPanel2;

    private DefaultPieDataset<String> dataset;
    private DefaultCategoryDataset dataset2;

    public DrawCharts(DefaultPieDataset<String> dataset, DefaultCategoryDataset dataset2) {
        this.dataset = dataset;
        this.dataset2 = dataset2;

        frame = new JFrame();
        frame.getContentPane().setBackground(Color.white);
        frame.setBounds(0, 0, 530, 290);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        PiePlot plot = new PiePlot();
        chart = new JFreeChart(plot);
        chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(0, 0, 250, 250);
        frame.getContentPane().add(chartPanel);

        chart2 = new JFreeChart(plot);
        chartPanel2 = new ChartPanel(chart2);
        chartPanel2.setBounds(250, 0, 250, 250);
        frame.getContentPane().add(chartPanel2);

        frame.setVisible(true);
    }

    public void createPanel() {
        JFreeChart chart = ChartFactory.createPieChart("Thrash Locations distribution", dataset, true, true, false);
        chart.setBackgroundPaint(Color.white);
        chartPanel.setChart(chart);

        JFreeChart chart2 = ChartFactory.createBarChart("",
                "Sector names",
                "Nr. of Thrash Locations",
                dataset2,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        chartPanel2.setChart(chart2);
    }
}
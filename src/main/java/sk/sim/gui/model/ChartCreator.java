package sk.sim.gui.model;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import sk.sim.RestaurantSimObject;
import sk.sim.gui.model.utill.StringToIntegerConverter;
import sk.sim.objects.GuestSimObject;

import java.util.Comparator;
import java.util.List;

public class ChartCreator
{
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private LineChart<Number, Number> chart;

    public ChartCreator()
    {
        prepareAxis();
        chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle("Liczba gości do czasu wizyty");
        setSeries();
    }

    public LineChart<Number, Number> getChart()
    {
        return chart;
    }

    private void prepareAxis()
    {
        xAxis = new NumberAxis();
        xAxis.setLabel("Liczba gości");


        xAxis.setTickLabelFormatter(new StringToIntegerConverter());
        xAxis.setTickUnit(1);
        yAxis = new NumberAxis();
        yAxis.setLabel("Czaś wizyty");

    }

    private void setSeries()
    {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Czas wizyty");

        setData(series);

        chart.getData().add(series);
    }

    private void setData(XYChart.Series<Number, Number> series)
    {
        List<GuestSimObject> guests = RestaurantSimObject.servicedGuests;
        guests.stream().sorted(Comparator.comparingInt(GuestSimObject::getId)).forEach(guest -> {
            series.getData().add(new XYChart.Data<>(guest.getId(), guest.getTimeOfVisit()));
        });
    }
}



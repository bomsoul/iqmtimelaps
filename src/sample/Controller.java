package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import sample.model.TimeLap;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Pane paneView;
    @FXML
    private Button button;
    @FXML private Label label;

    ArrayList<TimeLap> timeLaps ;

    private String average(){
        long duration = 0;
        for (TimeLap x:timeLaps) {
            duration += x.diffential();
        }

        duration /= timeLaps.size();

        long millis = duration % 1000;
        long second = (duration / 1000) % 60;
        long minute = (duration / (1000 * 60)) % 60;
        long hour = (duration / (1000 * 60 * 60)) % 24;

        String time = String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);

        return time;
    }

    public void reFreshBtn(){
        loadData();
    }

    private void loadData(){
        Random random = new Random();
        timeLaps.clear();
        //int n = random.nextInt(86400001);
        for (int i = 0 ;i < 7 ;i++){
            timeLaps.add(new TimeLap(random.nextInt(86400001),random.nextInt(86400001)));
        }
        paneView.getChildren().clear();
        CategoryAxis xAxis  = new CategoryAxis();
        xAxis.setLabel("Duration hh:mm:ss.ms");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Millisecond(ms)");
        BarChart milkChart = new BarChart(xAxis,yAxis);
        milkChart.setTitle("Time Differentiation");
        XYChart.Series series = new XYChart.Series();
        series.setName("Time Diff");
        for (TimeLap x:timeLaps) {
            series.getData().add(new XYChart.Data<>(x.toString(),x.diffential()));
        }
        milkChart.getData().add(series);
        paneView.getChildren().add(milkChart);
        label.setText("Avg is "+ average());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timeLaps = new ArrayList<>();
        loadData();
    }
}

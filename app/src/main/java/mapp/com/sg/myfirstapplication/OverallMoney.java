package mapp.com.sg.myfirstapplication;

import android.os.Bundle;

//import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class OverallMoney extends AppCompatActivity {

    String months[] =  {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    double expenses[] = {100.40, 29.50, 36.40, 47.00, 5.00, 67.00, 7.60, 8.80, 9.10, 10.70, 11.00, 12.90};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overall_money);
        setupChart();
    }

    private void setupChart() {
        // populating a list of PieEntries
        List<PieEntry> pieEntries = new ArrayList<>();

        for (int i = 0; i < expenses.length; i++) {
            pieEntries.add(new PieEntry((float) expenses[i], months[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "label???chg ltr");
        PieData data = new PieData(dataSet);

        // get the chart
        PieChart chart = findViewById(R.id.chart);
        chart.setData(data);
        chart.invalidate();


    } // End of method

}

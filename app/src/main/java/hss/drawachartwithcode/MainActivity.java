package hss.drawachartwithcode;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import hss.drawachartwithcode.data.ChartData;
import hss.drawachartwithcode.data.Point;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FancyChart chart = (FancyChart) findViewById(R.id.chart);
        chart.setOnPointClickListener(new FancyChartPointListener() {

            @Override
            public void onClick(Point point) {
                Toast.makeText(MainActivity.this, "I clicked point " + point, Toast.LENGTH_LONG).show();
            }
        });

        ChartData data = new ChartData(ChartData.LINE_COLOR_BLUE);
        int[] yValues = new int[]{0, 30, 60, 18, 35, 30, 33, 32, 46, 53, 50, 42};
        for (int i = 8; i <= 19; i++) {
            data.addPoint(i, yValues[i - 8]);
            data.addXValue(i, i + ":00");
        }
        chart.addData(data);

        ChartData data2 = new ChartData(ChartData.LINE_COLOR_RED);
        int[] yValues2 = new int[]{0, 5, 9, 23, 15, 35, 45, 50, 41, 45, 32, 24};
        for (int i = 8; i <= 19; i++) {
            data2.addPoint(i, yValues2[i - 8]);
            data2.addXValue(i, i + ":00");
        }
        chart.addData(data2);
    }

}
package hss.drawachartwithcode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by dinhdv on 12/7/2017.
 */

public class GraphDataActivity extends AppCompatActivity {
    private MyChartView mLlView;
    private MyChartView mLlView2;
    private MyCoordinatesView mCoordinates, mCoordinates2;
    private ArrayList<String> valuesX = new ArrayList<>();
    private ArrayList<Integer> listX = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_data);
        mLlView = findViewById(R.id.view);
        mLlView2 = findViewById(R.id.view_2);
        mCoordinates = findViewById(R.id.rl_coordinates);
        mCoordinates2 = findViewById(R.id.rl_coordinates_2);
        /// init values x.
        valuesX.add("23.0");
        valuesX.add("24.0");
        valuesX.add("25.0");
        valuesX.add("26.0");
        valuesX.add("27.0");
        valuesX.add("28.0");
        valuesX.add("29.0");
        valuesX.add("30.0");

        // init list X:
        listX.add(78);
        listX.add(100);
        listX.add(86);
        listX.add(95);
        listX.add(80);
        listX.add(100);
        listX.add(79);
        listX.add(95);
        // Chart 1
        mLlView.setLayoutParams(new LinearLayout.LayoutParams(AppUtils.getScreenWidth() / 3 * listX.size(), ViewGroup.LayoutParams.WRAP_CONTENT));
        mLlView.setParams("#1292eb", false, valuesX, listX);
        mCoordinates.setLayoutParams(new RelativeLayout.LayoutParams(AppUtils.getScreenWidth() / 3 * listX.size(), ViewGroup.LayoutParams.WRAP_CONTENT));
        mCoordinates.setParams(listX);
        // Chart 2

        mLlView2.setLayoutParams(new LinearLayout.LayoutParams(AppUtils.getScreenWidth() / 3 * listX.size(), ViewGroup.LayoutParams.WRAP_CONTENT));
        mLlView2.setParams("#fa835e", true, valuesX, listX);
        mCoordinates2.setLayoutParams(new RelativeLayout.LayoutParams(AppUtils.getScreenWidth() / 3 * listX.size(), ViewGroup.LayoutParams.WRAP_CONTENT));
        mCoordinates2.setParams(listX);
    }
}

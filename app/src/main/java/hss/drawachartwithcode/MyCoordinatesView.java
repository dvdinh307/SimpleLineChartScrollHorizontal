package hss.drawachartwithcode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by dinhdv on 12/7/2017.
 */

public class MyCoordinatesView extends View {
    private ArrayList<Integer> values = new ArrayList<>();
    private ArrayList<String> mListRows = new ArrayList<>();
    private int mNumberRow = 5;
    private int mMarginScreen = 80;
    private String colorLine = "#d0f2ff";
    private int mNumberSub = 30;

    public MyCoordinatesView(Context context) {
        super(context);
        initView(context);
    }

    public MyCoordinatesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyCoordinatesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        mListRows = createListTitle(values);
    }

    public void setParams(ArrayList<Integer> vals) {
        values = vals;
        initView(getContext());
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (values.size() == 0)
            return;
        int heightView = getHeight() - mMarginScreen;
        // Get with of row.
        int heightRow = heightView / mNumberRow - mNumberSub;
        // space title.
        //Create list vales y
        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.FILL_AND_STROKE);
        paint1.setStrokeWidth(8);
        paint1.setColor(Color.parseColor(colorLine));
        for (int i = 0; i < mListRows.size(); i++) {
            int y = heightView - (heightRow * i);
            drawText(canvas, mListRows.get(i), 0, y);
        }
    }

    private ArrayList<String> createListTitle(ArrayList<Integer> values) {
        ArrayList<String> result = new ArrayList<>();
        if (null == values || values.size() == 0)
            return result;
        int maxRaw = Collections.max(values);
        int minRaw = Collections.min(values);
        int max = (maxRaw % 10 == 0 ? maxRaw : ((maxRaw / 10) + 1) * 10);
        int min = (minRaw % 10 == 0) ? minRaw : (minRaw / 10) * 10;
        int space = (max - min) / mNumberRow;
        for (int i = min; i < max; i += space) {
            result.add(String.valueOf(i));
        }
        result.add(String.valueOf(max));
        return result;
    }

    private void drawText(Canvas canvas, String title, int x, int y) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        canvas.drawText(title, x, y, paint);
    }
}

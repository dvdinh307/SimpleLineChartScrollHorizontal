package hss.drawachartwithcode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by dinhdv on 12/7/2017.
 */

public class MyChartView extends View {
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<Integer> values = new ArrayList<>();
    private ArrayList<String> mListRows = new ArrayList<>();
    private int mIntX = 0;
    private int mIntY = 0;
    private int mStartX;
    private int mStartY;
    private int mNumberRow = 5;
    // Số cột hiển thị trên màn hình
    private int mNumberColumn = 2;
    private int mMargin = 80;
    private int mMarginScreen = 80;
    private int mSpace = 1;
    private int mMinValues = 1;
    private String mColor = "#1292eb";
    private String colorLine = "#d0f2ff";
    private int mNumberSub = 30;
    private boolean mIsSquare = false;
    private int radius = 50;

    public MyChartView(Context context) {
        super(context);
        initView(context);
    }

    public MyChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        mListRows = createListTitle(values);
    }

    public void setParams(String color, boolean isSquare, ArrayList<String> titleY, ArrayList<Integer> vals) {
        mColor = color;
        mIsSquare = isSquare;
        titles = titleY;
        if (titles.size() > 5) {
            mNumberColumn = 4;
        } else {
            mNumberColumn = titles.size() - 1;
        }
        values = vals;
        initView(getContext());
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (values.size() == 0)
            return;
        int widthView = getWidth() - mMarginScreen;
        int heightView = getHeight() - mMarginScreen;
        // Get with of row.
        int heightRow = heightView / mNumberRow - mNumberSub;
        // space title.
        int widthRow = (AppUtils.getScreenWidth() - mMarginScreen) / mNumberColumn;
        //Create list vales y
        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.FILL_AND_STROKE);
        paint1.setStrokeWidth(8);
        paint1.setColor(Color.parseColor(colorLine));
        // Vẽ trục X nằm ngang
        for (int i = 0; i < mListRows.size(); i++) {
            int y = heightView - (heightRow * i);
            canvas.drawLine(mMargin, y, widthView, y, paint1);
//            drawText(canvas, mListRows.get(i), 0, y);
        }
        // Vẽ trục Y thẳng đứng
        for (int i = 0; i < titles.size(); i++) {
            drawText(canvas, titles.get(i), widthRow * i + mMargin, heightView + mMargin);
        }
        // Vẽ các điểm trên trục tọa độ
        int ratioHeight = (heightView - ((mNumberRow - 1) * mNumberSub)) / mSpace;
        for (int i = 0; i < titles.size(); i++) {
            int startX = (i * widthRow) + mMargin;
            int startY = heightView - (((values.get(i) - mMinValues) * ratioHeight));
            ;
            if (i == 0) {
                mStartX = startX;
                mStartY = startY;
            }
            if (i == 1) {
                mIntX = startX;
                mIntY = startY;
                drawLine(canvas, mStartX, mIntX, mStartY, mIntY);
            }
            if (i > 1) {
                drawLine(canvas, startX, mIntX, startY, mIntY);
                mIntX = startX;
                mIntY = startY;
            }
            if (mIsSquare) {
                drawSquareColor(canvas, startX, startY);
            } else {
                drawPoint(canvas, startX, startY);
            }

        }

        for (int i = 0; i < titles.size(); i++) {
            int startX = (i * widthRow) + mMargin;
            int startY = heightView - (((values.get(i) - mMinValues) * ratioHeight));
            if (mIsSquare) {
                drawWhiteSquare(canvas, startX, startY);
            } else {
                drawWhitePoint(canvas, startX, startY);
            }
            drawTextOnTop(canvas, values.get(i), startX, startY);
        }
    }

    private void drawTextOnTop(Canvas canvas, int text, int startX, int startY) {
        int x = startX - 20;
        int y = startY - 60;
        // RECT
        int cornerRadius = 50;
        Paint round = new Paint();
        round.setStyle(Paint.Style.FILL);
        round.setColor(Color.parseColor(mColor));
        Rect rect = new Rect();
        round.getTextBounds(String.valueOf(text), 0, String.valueOf(text).length(), rect);
        int widthText = rect.width();
        int heightText = rect.height();
        RectF rectF = new RectF(x - (2 * widthText), y - (4 * heightText), x + (5 * widthText), y + (2 * heightText));
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, round);
        //Draw text
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        canvas.drawText(String.valueOf(text), x, y, paint);
    }

    private ArrayList<String> createListTitle(ArrayList<Integer> values) {
        ArrayList<String> result = new ArrayList<>();
        if (null == values || values.size() == 0)
            return result;
        int maxRaw = Collections.max(values);
        int minRaw = Collections.min(values);
        int max = (maxRaw % 10 == 0 ? maxRaw : ((maxRaw / 10) + 1) * 10);
        int min = (minRaw % 10 == 0) ? minRaw : (minRaw / 10) * 10;
        mSpace = max - min;
        mMinValues = min;
        int space = (max - min) / mNumberRow;
        for (int i = min; i < max; i += space) {
            result.add(String.valueOf(i));
        }
        result.add(String.valueOf(max));
        return result;
    }

    private void drawPoint(Canvas canvas, int x, int y) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor(mColor));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y, 30, paint);
    }

    private void drawSquareColor(Canvas canvas, int x, int y) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor(mColor));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x - 20, y - 20, x + radius - 10, y + radius - 10, paint);
    }

    private void drawWhiteSquare(Canvas canvas, int x, int y) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x - 10, y - 10, x + radius - 20, y + radius - 20, paint);
    }

    private void drawWhitePoint(Canvas canvas, int x, int y) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y, 22, paint);
    }

    private void drawLine(Canvas canvas, Integer startX, Integer stopX, Integer startY, Integer stopY) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor(mColor));
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    private void drawText(Canvas canvas, String title, int x, int y) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        canvas.drawText(title, x, y, paint);
    }
}

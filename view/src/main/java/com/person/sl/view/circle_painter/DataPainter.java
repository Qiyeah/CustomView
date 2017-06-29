package com.person.sl.view.circle_painter;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.person.sl.util.LogUtil;
import com.person.sl.view.Constants;
import com.person.sl.view.Painter;

/**
 * Created by sl on 2017/6/28.
 */

public class DataPainter implements Painter {
    int left, top, right, bottom;
    int radius;
    int centerX, centerY;

    private DecimalFormat df;

    private float receivedValue = 0;

    public DataPainter() {
        df = new DecimalFormat("0");
    }


    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Constants.getInstance().getDataColor());
        //中间的显示数据
        paint.setTextSize(96);

        paint.setStrokeWidth(1);

        canvas.drawText(df.format(receivedValue), centerX - 96, centerY - 24, paint);
        //LogUtil.e("draw data");
    }
    public void setValue(float value){
        receivedValue = value;
    }
    @Override
    public void onSizeChanged(int w, int h) {
        if (w > h) {
            left = (w - h) / 2;
            right = left + h;
            top = 0;
            bottom = h;
        } else {
            left = 0;
            top = (h - w) / 2;
            right = w;
            bottom = top + w;
        }
        radius = (bottom - top) / 2;
        centerX = (right - left) / 2 + left;
        centerY = (bottom - top) / 2 + top;
    }
}

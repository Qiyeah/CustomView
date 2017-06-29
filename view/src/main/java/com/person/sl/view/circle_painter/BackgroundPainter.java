package com.person.sl.view.circle_painter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

import com.person.sl.view.Constants;
import com.person.sl.view.Painter;

/**
 * Created by sl on 2017/6/22.
 */

public class BackgroundPainter implements Painter {
    int left, top, right, bottom;
    int centerX, centerY;
    int radius;
    float startAngle = 144;
    float endAngle;

    @Override
    public void draw(Canvas canvas) {
        drawBg(canvas);
        drawScale(canvas);
    }

    private void drawBg(Canvas canvas) {
        Paint paint = new Paint();
        //单位
        paint.setTextSize(36);
        canvas.drawText(Constants.getInstance().getUnit(), centerX - 36, centerY + 24, paint);
        //外四个圆弧及边界线
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(left, top, right, bottom);
        float tempAngle = 342;
        canvas.rotate(-126, centerX, centerY);
        for (int i = 0; i < 8; i++) {
            if ((i + 1) % 2 == 1) {
                canvas.drawArc(rectF, tempAngle, 36, false, paint);
                tempAngle += 72;
            }
            canvas.drawLine(centerX, centerY - radius, centerX, centerY - radius + 90, paint);
            canvas.rotate(36, centerX, centerY);
        }
       // canvas.restore();
        canvas.rotate(-162,centerX,centerY);
        //中间的细线圆弧
        canvas.drawArc(new RectF(left + 30, top + 30, right - 30, bottom - 30), 144, 252, false, paint);
        //开始绘制灰色背景圆弧
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(40);
        canvas.drawArc(new RectF(left + 70, top + 70, right - 70, bottom - 70), 144, 252, false, paint);
    }

    private void drawScale(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        //画中间的刻度及值
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(1);
        canvas.drawArc(new RectF(left + 95, top + 95, right - 95, bottom - 95), 144, 252, false, paint);
        canvas.rotate(-126, centerX, centerY);
        paint.setTextSize(20);
        for (int i = 0; i < 16; i++) {
            if (i % 3 == 0) {
                canvas.drawText(i + "k", centerX - 10, centerY - radius + 130, paint);
            }
            canvas.drawLine(centerX, centerY - radius + 95, centerX, centerY - radius + 110, paint);
            canvas.rotate(16.8f, centerX, centerY);

        }
        canvas.rotate(-142.8f, centerX, centerY);
        //  canvas.restore();
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

    public void initSize() {
        startAngle = 144;
        endAngle = 36;
    }
}

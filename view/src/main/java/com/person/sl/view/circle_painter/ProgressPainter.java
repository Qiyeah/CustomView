package com.person.sl.view.circle_painter;

import android.app.Application;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;

import com.person.sl.App;
import com.person.sl.view.Constants;
import com.person.sl.view.Painter;
import com.person.sl.view.R;

/**
 * Created by sl on 2017/6/25.
 */

public class ProgressPainter implements Painter {

    int left, top, right, bottom;
    private int w, h;
    int centerX, centerY;
    int radius;
    float startAngle = 144;
    float endAngle ;
    float sweepAngle = 236;
    float smallRadius,largeRadius;
    Constants mConstants = null;
    private void drawProgress(Canvas canvas){

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
 /*       canvas.drawLine(left,centerY,right,centerY,paint);
        canvas.drawLine(centerX,top,centerX,bottom,paint);*/
        /**
         *进度条
         */
        endAngle = startAngle+sweepAngle;

        paint.setColor(Color.parseColor("#95CAF9"));
        paint.setStrokeWidth(40);
        canvas.drawArc(new RectF(left + 70,top + 70 ,right -70,bottom - 70),startAngle,sweepAngle,false,paint);
        paint.setColor(Constants.getInstance().getProgressColor());
        paint.setStrokeWidth(30);
        canvas.drawArc(new RectF(left+75,top+75,right-75,bottom-75),startAngle,sweepAngle,false,paint);
        /**
         * 绘制进度条的头部
         */
        float startX = 0,startY = 0,endX = 0,endY = 0;
        double value;
        if (sweepAngle<36){
            value = (180 - startAngle -sweepAngle )/180d*Math.PI;
            startX =centerX -  (float) (Math.cos(value)*smallRadius);

            startY =(float) (Math.sin(value)*smallRadius)+centerY;
            endY =(float) (Math.sin(value)*largeRadius)+centerY;
            endX = centerX - (float) (Math.cos(value)*largeRadius);
        }else if (sweepAngle>36&&sweepAngle<=126){
            value = (sweepAngle - 36)/180d*Math.PI;
            startX =centerX -  (float) (Math.cos(value)*smallRadius);

            startY =centerY -(float) (Math.sin(value)*smallRadius);
            endY =centerY -(float)(Math.sin(value)*largeRadius);
            endX = centerX - (float) (Math.cos(value)*largeRadius);
        }else if (sweepAngle>126&&sweepAngle<=216){
            value = (180 -sweepAngle + 36)/180d*Math.PI;
            startX =centerX +  (float) (Math.cos(value)*smallRadius);

            startY =centerY -(float) (Math.sin(value)*smallRadius);
            endY =centerY -(float)(Math.sin(value)*largeRadius);
            endX = centerX + (float) (Math.cos(value)*largeRadius);
        }else if (sweepAngle>216&&sweepAngle<252){
            value = (sweepAngle -180 - 36)/180d*Math.PI;
            startX =centerX +  (float) (Math.cos(value)*smallRadius);

            startY =centerY +(float) (Math.sin(value)*smallRadius);
            endY =centerY +(float)(Math.sin(value)*largeRadius);
            endX = centerX + (float) (Math.cos(value)*largeRadius);
        }
        paint.setStrokeWidth(10);
        paint.setColor(Constants.getInstance().getProgressColor());
        canvas.drawLine(startX,startY,endX,endY,paint);
    }

    @Override
    public void draw(Canvas canvas) {
        drawProgress(canvas);
        mConstants = Constants.getInstance();

    }

    @Override
    public void onSizeChanged(int w, int h) {
        this.w = w;
        this.h = h;

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

        smallRadius = centerX - left-90;
        largeRadius = centerX - left-35;
    }
    public void setValue(float value){
        sweepAngle = value;
    }
}

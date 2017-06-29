package com.person.sl.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.person.sl.util.DisplayUtil;
import com.person.sl.util.LogUtil;
import com.person.sl.util.MeasureUtil;
import com.person.sl.view.circle_painter.BackgroundPainter;
import com.person.sl.view.circle_painter.DataPainter;
import com.person.sl.view.circle_painter.ProgressPainter;

/**
 * Created by sl on 2017/6/22.
 */

public class CircularProgressView extends BaseViewImpl{

    private Constants mConstants;

    private BackgroundPainter mBgPainter;
    private DataPainter mDataPainter;
    private ProgressPainter mProgressPainter;

    private ValueAnimator progressAnimator;

    private float lastAngle = 0;
    private float receivedValue;
    private float max = 252;
    private float min = 0;
    public CircularProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mBgPainter = new BackgroundPainter();
        mProgressPainter = new ProgressPainter();
        mDataPainter = new DataPainter();
    }

    @Override
    public void draw(Canvas canvas) {
        mBgPainter.draw(canvas);
        mProgressPainter.draw(canvas);
        mDataPainter.draw(canvas);
        invalidate();
    }

    @Override
    public void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs,R.styleable.CircularProgressView);
        mConstants = Constants.getInstance();

        mConstants.setBgColor(attributes.getColor(R.styleable.CircularProgressView_bgColor,Color.GRAY));
        mConstants.setTitleColor(attributes.getColor(R.styleable.CircularProgressView_titleColor,Color.BLACK));
        mConstants.setProgressColor( attributes.getColor(R.styleable.CircularProgressView_progressColor,Color.BLUE));
        mConstants.setDataColor(attributes.getColor(R.styleable.CircularProgressView_dataColor,Color.BLACK));
        mConstants.setUnitColor(attributes.getColor(R.styleable.CircularProgressView_unitColor,Color.GRAY));
        mConstants.setTitleSize( attributes.getInt(R.styleable.CircularProgressView_titleSize, DisplayUtil.px2dip(context,16)));
        mConstants.setViewSize(attributes.getInt(R.styleable.CircularProgressView_viewSize, DisplayUtil.px2dip(context,360)));
        mConstants.setScaleSize(attributes.getInt(R.styleable.CircularProgressView_scaleTextSize, DisplayUtil.px2dip(context,8)));
        mConstants.setScaleAmount(attributes.getInt(R.styleable.CircularProgressView_scaleAmount, 10));
        mConstants.setDataSize(attributes.getInt(R.styleable.CircularProgressView_dataSize, DisplayUtil.px2dip(context,20)));
        mConstants.setUnitSize(attributes.getInt(R.styleable.CircularProgressView_unitSize, DisplayUtil.px2dip(context,8)));
        mConstants.setTitleFont(attributes.getString(R.styleable.CircularProgressView_textFont));
        mConstants.setUnit(attributes.getString(R.styleable.CircularProgressView_unit));
        /*LogUtil.e("bgColor: "+mConstants.getBgColor());
        LogUtil.e("titleColor: "+mConstants.getTitleColor());
        LogUtil.e("progressColor: "+progressColor);
        LogUtil.e("dataColor: "+dataColor);
        LogUtil.e("unitColor: "+unitColor);
        LogUtil.e("titleSize: "+titleSize);
        LogUtil.e("viewSize: "+viewSize);
        LogUtil.e("scaleAmount: "+scaleAmount);
        LogUtil.e("dataSize: "+dataSize);
        LogUtil.e("unitSize: "+unitSize);
        LogUtil.e("titleFont: "+titleFont);
        LogUtil.e("unit: "+unit);*/

        initProgressAnimator();
    }

    @Override
    public void setValue(float value, boolean animate) {
        this.receivedValue = value;
        mProgressPainter.setValue( value);
        if (value <= max && value >= min) {
            if (!animate) {
                mProgressPainter.setValue(value);
                //mDataPainter.setValue(value);
            } else {
                startProgressAnimator();
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBgPainter.onSizeChanged(w,h);
        mProgressPainter.onSizeChanged(w,h);
        mDataPainter.onSizeChanged(w,h);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureUtil.measureWidth(widthMeasureSpec,400),
                MeasureUtil.measureHeight(heightMeasureSpec,400));
    }

    private void initProgressAnimator(){
        progressAnimator = new ValueAnimator();
        progressAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        progressAnimator.addUpdateListener(new ProgressAnimatorListener());
    }

    private void startProgressAnimator(){
        progressAnimator.setFloatValues(lastAngle,receivedValue);
        progressAnimator.setDuration(3000);
        progressAnimator.start();

    }

    class ProgressAnimatorListener implements ValueAnimator.AnimatorUpdateListener{

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            float value = (float) animation.getAnimatedValue();
            updateProgressValue(value);
            lastAngle = value;
        }

        private void updateProgressValue(float value) {
            mProgressPainter.setValue(value);
           mDataPainter.setValue(value);
        }
    }
}

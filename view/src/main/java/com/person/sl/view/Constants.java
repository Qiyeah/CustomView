package com.person.sl.view;

import android.graphics.Canvas;

/**
 * Created by sl on 2017/6/23.
 */

public class Constants {
    private int titleColor;//标题文字颜色
    private int titleSize;//标题文字大小
    private String titleFont;//标题文字字体
    private int viewSize;//视图整体大小
    private int progressColor;//进度条颜色
    private int bgColor;//视图背景颜色
    //private int scaleColor;//刻度颜色
    private int scaleSize;//刻度文字大小
    private int scaleAmount;//刻度总数量
    private int dataColor;//显示数据的颜色
    private int dataSize;//显示数据的文字大小
    private int unitSize;//单位字体大小
    private int unitColor;//单位字体颜色
    private String unit;//单位
    private static Constants instance = null;
    private Constants() {}

    public static synchronized Constants getInstance() {
        if (null == instance){
            synchronized (Constants.class){
                if (null == instance){
                    instance = new Constants();
                }
            }
        }
        return instance;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public int getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(int titleSize) {
        this.titleSize = titleSize;
    }

    public String getTitleFont() {
        return titleFont;
    }

    public void setTitleFont(String titleFont) {
        this.titleFont = titleFont;
    }

    public int getViewSize() {
        return viewSize;
    }

    public void setViewSize(int viewSize) {
        this.viewSize = viewSize;
    }

    public int getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public int getScaleSize() {
        return scaleSize;
    }

    public void setScaleSize(int scaleSize) {
        this.scaleSize = scaleSize;
    }

    public int getScaleAmount() {
        return scaleAmount;
    }

    public void setScaleAmount(int scaleAmount) {
        this.scaleAmount = scaleAmount;
    }

    public int getDataColor() {
        return dataColor;
    }

    public void setDataColor(int dataColor) {
        this.dataColor = dataColor;
    }

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    public int getUnitSize() {
        return unitSize;
    }

    public void setUnitSize(int unitSize) {
        this.unitSize = unitSize;
    }

    public int getUnitColor() {
        return unitColor;
    }

    public void setUnitColor(int unitColor) {
        this.unitColor = unitColor;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public static void setInstance(Constants instance) {
        Constants.instance = instance;
    }
}

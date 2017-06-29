package com.person.sl.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sl on 2017/6/22.
 */

public interface BaseView{
    void parseAttrs(Context context, AttributeSet attrs);

    void setValue(float value, boolean animate);
}

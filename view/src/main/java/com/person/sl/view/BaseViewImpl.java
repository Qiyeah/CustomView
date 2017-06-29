package com.person.sl.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sl on 2017/6/22.
 */

public abstract class BaseViewImpl extends View implements BaseView {
    public BaseViewImpl(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttrs(context,attrs);
    }

}

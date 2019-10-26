package com.she.suandao.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.she.suandao.R;

/**
 * Description :
 *
 * @author : mrj
 * Date : 2019/10/23
 * Time : 16:12
 */
public class FontTextView extends AppCompatTextView {


    public FontTextView(Context context) {
        this(context, null);
    }

    public FontTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FontTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray t = getContext().obtainStyledAttributes(attrs, R.styleable.FontTextView);
        String type = t.getString(R.styleable.FontTextView_fontPath);
        if (TextUtils.isEmpty(type)) {
            setTypeface(createTypeface(getContext(), "xingkai.ttf"));
        } else {
            setTypeface(createTypeface(getContext(), type + ".ttf"));
        }
        t.recycle();

    }

    private Typeface createTypeface(Context context, String fontPath) {
        return Typeface.createFromAsset(context.getAssets(), fontPath);
    }
}

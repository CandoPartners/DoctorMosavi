package com.northlinuxpioneers.arash.medicalarticles.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class PersianTextView extends TextView {
    public PersianTextView(Context context) {
        super(context);
        if (!isInEditMode())
            init(context);

    }

    public PersianTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            init(context);
    }

    public PersianTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode())
            init(context);
    }

    private void init(Context context) {
        setTypeface(com.northlinuxpioneers.arash.medicalarticles.widget.FontHelper.getInstance(context).getPersianTextTypeface());
    }

   /* @Override
    public void setText(CharSequence text, BufferType type) {
        if (text != null)
            text = FormatHelper.toPersianNumber(text.toString());
        super.setText(text, type);
    }*/
}

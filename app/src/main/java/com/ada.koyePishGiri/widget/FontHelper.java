package com.northlinuxpioneers.arash.medicalarticles.widget;

import android.content.Context;
import android.graphics.Typeface;

public class FontHelper {
    private static FontHelper instance;
    private static Typeface iconTypeface;
    private static Typeface persianTypeface;

    private FontHelper(Context context) {
        persianTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/YekanMob-Regular-v4.ttf");
    }

    public static synchronized FontHelper getInstance(Context context) {
        if (instance == null)
            instance = new FontHelper(context);
        return instance;
    }

    public Typeface getPersianTextTypeface() {
        return persianTypeface;
    }
}

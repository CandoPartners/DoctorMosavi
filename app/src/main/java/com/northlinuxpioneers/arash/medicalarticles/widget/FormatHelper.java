package com.northlinuxpioneers.arash.medicalarticles.widget;


import java.text.DecimalFormat;

/**
 * Created by Hamed on 3/31/14.
 */
public class FormatHelper {
    private static String[] persianNumbers = new String[]{"۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹"};

    public static String toNumberFormat(Number num, int fraction) {
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance();
        formatter.setGroupingSize(3);
        formatter.setMaximumFractionDigits(fraction);
        formatter.setMinimumFractionDigits(fraction);
        formatter.setGroupingUsed(true);
        return formatter.format(num);
    }

    public static String toPercentFormat(Number num) {
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getPercentInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        return formatter.format(num);
    }

    public static String toPersianNumber(String text) {
        if (text == null)
            return null;
        String out = "";
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if ('0' <= c && c <= '9') {
                int number = Integer.parseInt(String.valueOf(c));
                out += persianNumbers[number];
            } else if (c == '٫') {
                out += '،';
            } else {
                out += c;
            }
        }
        return out;
    }

    public static String toNumberFormat(Number num) {
        return toNumberFormat(num, 0);
    }
}

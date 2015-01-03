package com.northlinuxpioneers.arash.medicalarticles;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class GeneralHelper {
    public static String getArticleFromAssets(Context context) {
        // file to input stream
        InputStream input = null;
        int size = 0;
        try {
            input = context.getAssets().open(
                    "first_text.txt");
            size = input.available();
        } catch (IOException e) {
        }
        // myData.txt can't be more than 2 gigs.
        byte[] buffer = new byte[size];
        try {
            input.read(buffer);
            input.close();
        } catch (IOException e) {
        }

        // byte buffer into a string
        String string = new String(buffer);

        return string;
    }
}

package com.ada.koyePishGiri;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class GeneralHelper {
    public static String getArticleFromAssets(Context context, String id) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(id +".txt"), Charset.forName("UTF-8")));
        } catch (IOException e) {
        }
        int c;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while((c = reader.read()) != -1) {
                char character = (char) c;
                stringBuilder.append(character);
            }
        } catch (IOException e) {
        }

        return stringBuilder.toString();
    }
}

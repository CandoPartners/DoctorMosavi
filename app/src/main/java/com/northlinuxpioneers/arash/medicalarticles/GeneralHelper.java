package com.northlinuxpioneers.arash.medicalarticles;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class GeneralHelper {
    public static String getArticleFromAssets(Context context, int id) {
//        // file to input stream
//        InputStream input = null;
//        int size = 0;
//        try {
//            input = context.getAssets().open(id +
//                    ".txt");
//            size = input.available();
//        } catch (IOException e) {
//        }
//        // myData.txt can't be more than 2 gigs.
//        byte[] buffer = new byte[size];
//        try {
//            input.read(buffer);
//            input.close();
//        } catch (IOException e) {
//        }
//
//        // byte buffer into a string
//        String string = new String(buffer);
//
//        return string;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(id + ".txt"), Charset.forName("UTF_8")));
        } catch (IOException e) {

        }
        int c;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((c = reader.read()) != -1) {
                char character = (char) c;
                stringBuilder.append(character);
            }
        } catch (IOException e) {

        }
        return stringBuilder.toString();
    }
}

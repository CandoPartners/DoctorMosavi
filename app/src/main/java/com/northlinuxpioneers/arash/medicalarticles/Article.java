package com.northlinuxpioneers.arash.medicalarticles;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;


public class Article extends ActionBarActivity {

    private TextView articleTitleHolder;
    private ImageView articleImageHolder;
    private TextView articleTextHolder;

    private int articleID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getResources().getString(R.string.author_name));

        articleTitleHolder = (TextView) findViewById(R.id.articleTitleHolder);
        articleImageHolder = (ImageView) findViewById(R.id.articleImageHolder);
        articleTextHolder = (TextView) findViewById(R.id.articleTextHolder);

        getData(List.getItems(), articleID);
    }

    private Item getData(Item[] items, int id)
    {
        Item temp = items[id];

        // code for setting item text

        //////////////////////////////////////////////

        // at the moment, we have only ONE item in the list

        articleTitleHolder.setText(items[0].getTitle());
        articleImageHolder.setImageURI(Uri.parse(items[0].getPicURL()));

        // file to input stream
        InputStream input = null;
        int size = 0;
        try {
            input = getAssets().open(
                    "first_text.txt");
            size = input.available();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // myData.txt can't be more than 2 gigs.
        byte[] buffer = new byte[size];
        try {
            input.read(buffer);
            input.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // byte buffer into a string
        String string = new String(buffer);

        temp.setText(string);

        articleTextHolder.setText(temp.getText());

        //////////////////////////////////////////////

        return temp;
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_article, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}

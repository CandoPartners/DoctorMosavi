package com.northlinuxpioneers.arash.medicalarticles;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class ArticleActivity extends ActionBarActivity {

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
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        actionBar.setTitle(getResources().getString(R.string.author_name));

        articleTitleHolder = (TextView) findViewById(R.id.articleTitleHolder);
        articleImageHolder = (ImageView) findViewById(R.id.articleImageHolder);
        articleTextHolder = (TextView) findViewById(R.id.articleTextHolder);

        articleID = getIntent().getExtras().getInt("articleID");

        getData(articleID);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("articleID", articleID);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        articleID = savedInstanceState.getInt("articleID");
        super.onRestoreInstanceState(savedInstanceState);
    }

    private class DataLoaderTask extends AsyncTask<Object, Integer, Long> {

        Item articlebla;

        protected Long doInBackground(Object... params) {
            Item article = (Item) params[0];
            int identifier = (int) params[1];

            article.setText(GeneralHelper.getArticleFromAssets(ArticleActivity.this, identifier));
            articlebla = article;

            return 1L;
        }

        protected void onProgressUpdate(Integer progress) {

        }

        protected void onPostExecute(Long result) {
            articleTextHolder.setText(articlebla.getText());
        }
    }

    private void getData(int id)
    {
        Item[] articles = new Item[2];

        // code for getting the articles

        //////////////////////////////////////////////

        // at the moment, we have only ONE item in the list

        Item first = new Item();
        first.setId(0);
        first.setTitle(getResources().getString(R.string.firstItem));
        first.setPicURL("android.resource://com.northlinuxpioneers.arash.medicalarticles/raw/first_pic");
        articles[0] = first;

        Item second = new Item();
        second.setId(1);
        second.setTitle(getResources().getString(R.string.secondItem));
        second.setPicURL("android.resource://com.northlinuxpioneers.arash.medicalarticles/raw/first_pic");
        articles[1] = second;

        //////////////////////////////////////////////

        Item temp = articles[id];

        // code for setting item text

        //////////////////////////////////////////////

        // at the moment, we have only ONE item in the list

        articleTitleHolder.setText(temp.getTitle());
        articleImageHolder.setImageURI(Uri.parse(temp.getPicURL()));
//        temp.setText(GeneralHelper.getArticleFromAssets(ArticleActivity.this, articleID));
        Object[] paramsBundle = new Object[2];
        paramsBundle[0] = temp;
        paramsBundle[1] = id;
        new DataLoaderTask().execute(paramsBundle);
//        articleTextHolder.setText(temp.getText());

        //////////////////////////////////////////////
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.slide_out_right,R.anim.slide_in_left);
                return true;
        }
        return false;
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

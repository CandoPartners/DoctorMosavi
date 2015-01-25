package com.ada.koyePishGiri;

import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import com.ada.koyePishGiri.Article;


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
       /* if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }*/
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

    /*private class DataLoaderTask extends AsyncTask<Integer, Integer, String> {
        protected String doInBackground(Integer... id) {
            return
        }

        protected void onPostExecute(String result) {
            Animation animation = new AlphaAnimation(0,1f);
            animation.setDuration(1000);
            articleTextHolder.setText(result);
            articleTextHolder.startAnimation(animation);
        }
    }*/

    private void getData(int id)
    {
        com.ada.koyePishGiri.Article[] articles = new Article[3];

        // code for getting the articles

        //////////////////////////////////////////////

        // at the moment, we have only ONE item in the list

        Article first = new Article();
        first.setId(0);
        first.setTitle(getResources().getString(R.string.firstItem));
        first.setPicURL("android.resource://com.ada.koyePishGiri/raw/b1");
        articles[0] = first;

        Article second = new Article();
        second.setId(1);
        second.setTitle(getResources().getString(R.string.secondItem));
        second.setPicURL("android.resource://com.ada.koyePishGiri/raw/b2");
        articles[1] = second;

        Article three = new Article();
        three.setId(2);
        three.setTitle(getResources().getString(R.string.threeItem));
        three.setPicURL("android.resource://com.ada.koyePishGiri/raw/b3");
        articles[2] = three;

        //////////////////////////////////////////////

        Article temp = articles[id];

        // code for setting item text

        //////////////////////////////////////////////

        // at the moment, we have only ONE item in the list

        articleTitleHolder.setText(temp.getTitle());
        articleImageHolder.setImageURI(Uri.parse(temp.getPicURL()));
//        temp.setText(GeneralHelper.getArticleFromAssets(ArticleActivity.this, articleID));
//        new DataLoaderTask().execute(id);
//        articleTextHolder.setText(temp.getText());
        handler.postDelayed(runnable, 100);
        //////////////////////////////////////////////
    }


    Handler handler  = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            final String articleFromAssets = com.ada.koyePishGiri.GeneralHelper.getArticleFromAssets(ArticleActivity.this, String.valueOf(articleID));
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Animation animation = new AlphaAnimation(0,1f);
                    animation.setDuration(1000);
                    articleTextHolder.setText(Html.fromHtml(articleFromAssets));
                    articleTextHolder.startAnimation(animation);
                }
            });
        }
    };
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_right,R.anim.slide_in_left);
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

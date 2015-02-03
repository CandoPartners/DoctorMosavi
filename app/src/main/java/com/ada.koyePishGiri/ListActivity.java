package com.ada.koyePishGiri;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ada.koyePishGiri.Article;

public class ListActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Article[] Articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.listArticles);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ListAdapter(getData());
        mRecyclerView.setAdapter(mAdapter);
    }

    private Article[] getData()
    {
        Article[] articles = new Article[3];

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

        Articles = articles;

        return articles;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            startActivity(new Intent(ListActivity.this,AboutActivity.class));
            overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_right);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
        private Article[] mDataset;

        // Provide a reference to the views for each data item
        // Complex data Articles may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {

            // define views that are inside the view_holder : public
            public TextView articleName;
            public TextView articleDetail;
            public TextView button;
            public ImageView articleImage;
            public int id;

            public ViewHolder(View v) {
                super(v);

                // initialize views inside the view_holder
                articleName = (TextView) v.findViewById(R.id.articleName);
                articleDetail = (TextView) v.findViewById(R.id.articleDetail);
//                button = (TextView) v.findViewById(R.id.button);
                articleImage = (ImageView) v.findViewById(R.id.articleImage);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), com.ada.koyePishGiri.ArticleActivity.class);
                        intent.putExtra("articleID", id);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_right);
                    }
                });
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public ListAdapter(Article[] items) {
            mDataset = items;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            // set the view's size, margins, paddings and layout parameters

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element

            // code to assign data to different views inside the view_holder
            holder.articleName.setText(mDataset[position].getTitle());
            holder.articleDetail.setText(com.ada.koyePishGiri.GeneralHelper.getArticleFromAssets(ListActivity.this, position + "titles"));
            holder.articleImage.setImageURI(Uri.parse(mDataset[position].getPicURL()));
            holder.id = Articles[position].getId();
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}

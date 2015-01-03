package com.northlinuxpioneers.arash.medicalarticles;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ListActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private static Item[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

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

    private Item[] getData()
    {
        Item[] articles = new Item[1];

        // code for getting the articles

        //////////////////////////////////////////////

        // at the moment, we have only ONE item in the list

        Item first = new Item();
        first.setId(0);
        first.setTitle(getResources().getString(R.string.firstItem));
        first.setPicURL("android.resource://com.northlinuxpioneers.arash.medicalarticles/raw/first_pic");
        articles[0] = first;

        //////////////////////////////////////////////

        items = articles;

        return articles;
    }

    public static Item[] getItems()
    {
        return items;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_list, menu);
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

    private class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
        private Item[] mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {

            // define views that are inside the view_holder : public
            public TextView articleName;
            public TextView articleDetail;
            public TextView button;
            public ImageView articleImage;

            public ViewHolder(View v) {
                super(v);

                // initialize views inside the view_holder
                articleName = (TextView) v.findViewById(R.id.articleName);
                articleDetail = (TextView) v.findViewById(R.id.articleDetail);
                button = (TextView) v.findViewById(R.id.button);
                articleImage = (ImageView) v.findViewById(R.id.articleImage);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public ListAdapter(Item[] items) {
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

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ArticleActivity.class);
                    startActivity(intent);
//                    overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_right);
                }
            });

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
            holder.articleDetail.setText(GeneralHelper.getArticleFromAssets(ListActivity.this));
            holder.articleImage.setImageURI(Uri.parse(mDataset[position].getPicURL()));
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}

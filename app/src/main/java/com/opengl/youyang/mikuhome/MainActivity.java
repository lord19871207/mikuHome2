package com.opengl.youyang.mikuhome;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LinearLayoutManager mManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mManager = new LinearLayoutManager(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.base_swipe_list);
        recyclerView.setLayoutManager(mManager);
        BitSet set = new BitSet();
        List<NewsListEntity> list = new ArrayList<>();
        for(int i =0;i<5;i++){
            NewsListEntity entity = new NewsListEntity(set,i,"",Constont.array.get(i));
            list.add(entity);
        }

        NewsListAdapter adapter = new NewsListAdapter(this,list);

        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "显示", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;


import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_word_list);

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("red","weṭeṭṭi", R.drawable.color_red));
        words.add(new Word("green","chokokki", R.drawable.color_green));
        words.add(new Word("brown","ṭakaakki", R.drawable.color_brown));
        words.add(new Word("gray","ṭopoppi", R.drawable.color_gray));
        words.add(new Word("black","kululli", R.drawable.color_black));
        words.add(new Word("white" ,"elelli", R.drawable.color_white));
        words.add(new Word("dusty yellow","ṭopiisә", R.drawable.color_dusty_yellow));
        words.add(new Word("mustard yellow","chiwiiṭә", R.drawable.color_mustard_yellow));

        //ArrayAdapter adapter using ListView
        /*WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);*/

        //RecyclerView Adapter in use
        // Lookup the recyclerview in activity layout
        RecyclerView rvWords = (RecyclerView) findViewById(R.id.rvWordList);
        rvWords.setHasFixedSize(true);

        // Create adapter passing in the sample user data
        RvWordAdapter adapter = new RvWordAdapter(this, words, R.color.category_colors);
        // Attach the adapter to the recyclerview to populate items
        rvWords.setAdapter(adapter);
        // Set layout manager to position the items
        rvWords.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        rvWords.addItemDecoration(itemDecoration);

    }

}

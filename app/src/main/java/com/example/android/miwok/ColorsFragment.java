package com.example.android.miwok;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    WordAdapter myAdapter;


    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_list, container, false);

        ArrayList<Word> colorsWords = new ArrayList<Word>();
        colorsWords.add(new Word("red","weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        colorsWords.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        colorsWords.add(new Word("brown","tolookosu",R.drawable.color_brown,R.raw.color_brown));
        colorsWords.add(new Word("gray","ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        colorsWords.add(new Word("black","kululli",R.drawable.color_black,R.raw.color_black));
        colorsWords.add(new Word("white","kelelli",R.drawable.color_white,R.raw.color_white));
        colorsWords.add(new Word("dusty yellow", "ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        colorsWords.add(new Word("mustard yelow","chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));

        myAdapter = new WordAdapter(getActivity(),colorsWords,R.color.category_colors);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(myAdapter);

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        myAdapter.releaseMediaPlayer();
    }

}

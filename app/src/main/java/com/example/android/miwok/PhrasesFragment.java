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
public class PhrasesFragment extends Fragment {

    WordAdapter myAdapter;


    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onStop() {
        super.onStop();
        myAdapter.releaseMediaPlayer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_list, container, false);

        ArrayList<Word> phrasesWords = new ArrayList<Word>();
        phrasesWords.add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        phrasesWords.add(new Word("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        phrasesWords.add(new Word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        phrasesWords.add(new Word("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
        phrasesWords.add(new Word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        phrasesWords.add(new Word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        phrasesWords.add(new Word("Yes, I’m coming.", "hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        phrasesWords.add(new Word("I’m coming.","әәnәm",R.raw.phrase_im_coming));
        phrasesWords.add(new Word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        phrasesWords.add(new Word("Come here.","әnni'nem",R.raw.phrase_come_here));

        myAdapter = new WordAdapter(getActivity(),phrasesWords,R.color.category_phrases);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(myAdapter);

        return rootView;
    }

}

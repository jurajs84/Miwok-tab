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
public class FamilyFragment extends Fragment {

    WordAdapter familyAdapter;


    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public void onStop() {
        super.onStop();
        familyAdapter.releaseMediaPlayer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_list, container, false);

        final ArrayList<Word> familyWords = new ArrayList<Word>();
        familyWords.add(new Word("father","epe",R.drawable.family_father,R.raw.family_father));
        familyWords.add(new Word("mother","eta",R.drawable.family_mother,R.raw.family_mother));
        familyWords.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        familyWords.add(new Word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        familyWords.add(new Word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        familyWords.add(new Word("older sister","tete",R.drawable.family_older_sister,R.raw.family_older_sister));
        familyWords.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        familyWords.add(new Word("grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        familyWords.add(new Word("grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));

        familyAdapter = new WordAdapter(getActivity(),familyWords,R.color.category_family);

        ListView familyListView = (ListView)rootView.findViewById(R.id.list);
/*        familyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mediaPlayer = MediaPlayer.create(getBaseContext(),familyWords.get(i).getSoundResourceID());
                mediaPlayer.start();
            }
        });*/

        familyListView.setAdapter(familyAdapter);

        return rootView;
    }

}

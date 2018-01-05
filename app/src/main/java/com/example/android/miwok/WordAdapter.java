package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jirig on 21.11.2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int backroundColor;
    private MediaPlayer mediaPlayer;
    private AudioManager am;

    public  WordAdapter(Activity context, ArrayList<Word> wordsList, int color){
        super(context,0,wordsList);
        this.backroundColor = color;
        am = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }

        final Word currentWord = getItem(position);

        TextView miwokTextView = (TextView)listItemView.findViewById(R.id.miwok_word);
        miwokTextView.setText(currentWord.getMiwokWord());

        TextView defoultText = (TextView)listItemView.findViewById(R.id.default_word);
        defoultText.setText(currentWord.getDefaultWord());

        ImageView imageID = (ImageView) listItemView.findViewById(R.id.imageID);

        if (currentWord.getImageResourceID() != 0) {
            imageID.setImageResource(currentWord.getImageResourceID());
        }
        else{
            imageID.setVisibility(View.GONE);
        }

        RelativeLayout myLinearLayout = (RelativeLayout) listItemView.findViewById(R.id.rootLinearLayout);
        myLinearLayout.setBackgroundColor(ContextCompat.getColor(getContext(),backroundColor));

        RelativeLayout root = (RelativeLayout) listItemView.findViewById(R.id.rootLinearLayout);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                releaseMediaPlayer();

                int result = am.requestAudioFocus(afChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_GAIN){
                    mediaPlayer = MediaPlayer.create(getContext(),currentWord.getSoundResourceID());
                    getMediaPlayer().start();

                    getMediaPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(getContext(), "Přehráno", Toast.LENGTH_SHORT).show();

                            releaseMediaPlayer();
                        }
                    });
                }
            }
        });

        return listItemView;
    }

    /**
     * Clean up the media player by releasing its resources. Vycisteni pameti
     */
    public void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (getMediaPlayer() != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            getMediaPlayer().release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

            am.abandonAudioFocus(afChangeListener);
        }
    }

    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // Pause playback because your Audio Focus was
                // temporarily stolen, but will be back soon.
                // i.e. for a phone call
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // Stop playback, because you lost the Audio Focus.
                // i.e. the user started some other playback app
                // Remember to unregister your controls/buttons here.
                // And release the kra — Audio Focus!
                // You’re done.
                releaseMediaPlayer();
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // Resume playback, because you hold the Audio Focus
                // again!
                // i.e. the phone call ended or the nav directions
                // are finished
                // If you implement ducking and lower the volume, be
                // sure to return it to normal here, as well.
                getMediaPlayer().start();
            }
        }
    };



    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}

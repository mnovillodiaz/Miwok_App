package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener listener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    public FamilyFragment() {
        // Required empty public constructor
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.rv_word_list, container, false);


        final AudioManager am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        final AudioManager.OnAudioFocusChangeListener afChangeListener =
                new AudioManager.OnAudioFocusChangeListener() {
                    public void onAudioFocusChange(int focusChange) {
                        if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                            releaseMediaPlayer();
                        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                            mediaPlayer.pause();
                        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                            mediaPlayer.pause();
                        } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                            if (mediaPlayer != null) {
                                mediaPlayer.start();
                            }
                        }
                    }
                };

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));


        //RecyclerView Adapter in use
        // Lookup the recyclerview in activity layout
        RecyclerView rvWords = (RecyclerView) rootView.findViewById(R.id.rvWordList);
        rvWords.setHasFixedSize(true);

        // Create adapter passing in the sample user data
        RvWordAdapter adapter = new RvWordAdapter(getActivity(), words, R.color.category_family,
                new RvWordAdapter.OnListItemClick() {
                    @Override
                    public void onClickListener(Word item) {
                        // Request audio focus for playback
                        int result = am.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC,
                                AudioManager.AUDIOFOCUS_GAIN);
                        // if audio focus granted
                        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                            releaseMediaPlayer();
                            mediaPlayer = MediaPlayer.create(getActivity(), item.getSound());
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(listener);
                            am.abandonAudioFocus(afChangeListener);
                        }
                    }
                });
        // Attach the adapter to the RecyclerView to populate items
        rvWords.setAdapter(adapter);
        // Set layout manager to position the items
        rvWords.setLayoutManager(new LinearLayoutManager(getActivity()));

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        rvWords.addItemDecoration(itemDecoration);

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}

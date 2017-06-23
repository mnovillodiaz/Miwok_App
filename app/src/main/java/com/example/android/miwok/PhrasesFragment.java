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
public class PhrasesFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener listener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    public PhrasesFragment() {
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
        words.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        //RecyclerView Adapter in use
        // Lookup the RecyclerView in activity layout
        RecyclerView rvWords = (RecyclerView) rootView.findViewById(R.id.rvWordList);
        rvWords.setHasFixedSize(true);

        // Create adapter passing in the sample user data
        RvWordAdapter adapter = new RvWordAdapter(getActivity(), words, R.color.category_phrases, new RvWordAdapter.OnListItemClick() {
            @Override
            public void onClickListener(Word item) {
                // Request audio focus for playback
                int result = am.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
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

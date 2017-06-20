package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener listener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_word_list);

        final AudioManager am = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
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
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo′e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na′aacha", R.drawable.number_ten, R.raw.number_ten));


        /*WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);*/

        //RecyclerView Adapter in use
        // Lookup the recyclerview in activity layout
        RecyclerView rvWords = (RecyclerView) findViewById(R.id.rvWordList);
        rvWords.setHasFixedSize(true);

        // Create adapter passing in the sample user data
        RvWordAdapter adapter = new RvWordAdapter(this, words, R.color.category_numbers, new RvWordAdapter.OnListItemClick() {
            @Override
            public void onClickListener(Word item) {
                // Request audio focus for playback
                int result = am.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN);
                // if audio focus granted
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    releaseMediaPlayer();
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, item.getSound());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(listener);
                    am.abandonAudioFocus(afChangeListener);
                }
            }
        });
        // Attach the adapter to the recyclerview to populate items
        rvWords.setAdapter(adapter);
        // Set layout manager to position the items
        rvWords.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        rvWords.addItemDecoration(itemDecoration);

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
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}

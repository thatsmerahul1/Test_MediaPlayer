package com.example.a20115642.test_mediaplayer;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    float VOLUME = 1.0f;
    MediaPlayer    mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playRingtone(View v) {


        // Honour silent mode

                AudioManager audioManager = (AudioManager)this.getSystemService(getApplicationContext().AUDIO_SERVICE);
                mPlayer = new MediaPlayer();
                mPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
                int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);

                Log.e("Volume", "currVolume::" + currVolume);
                int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION);
                Log.e("Volume", "maxVolume::" + maxVolume);
                audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, maxVolume, AudioManager.FLAG_PLAY_SOUND);
                Uri ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                Log.e("Volume", "ringtone::" + ringtone.getPath());
                try {
                    mPlayer.setDataSource(this, ringtone);
                    mPlayer.prepare();
                } catch (IOException e) {
                    Log.e("Volume", "Could not setup media player for ringtone");
                    mPlayer = null;
                    return;
                }
                mPlayer.setLooping(true);
                mPlayer.setVolume(VOLUME, VOLUME);
                mPlayer.start();

        }

    }



package com.captaindroid.video.downloader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.captaindroid.video.downloader.dto.VideoLink;
import com.captaindroid.video.downloader.events.OnVideoFoundListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            //VideoDownloader.getInstance().getResults(this, "https://www.youtube.com/watch?v=434EuVb1eNQ", new OnVideoFoundListener() {
            VideoDownloader.getInstance().getResults(this, "https://youtube.com/shorts/t-ZCPhewCMk", new OnVideoFoundListener() {
                @Override
                public void onVideo(ArrayList<VideoLink> videos) {
                    for (int i = 0; i < videos.size(); i++) {
                        Log.e("size", videos.get(i).isAudioAvailable() + " " + videos.get(i).getQuality() + " adf " + " " + videos.get(i).getUrl());
                    }

                }

                @Override
                public void onError(String error) {
                    Log.e("size", error);
                }
            });
        } catch (Exception e) {

        }
    }
}
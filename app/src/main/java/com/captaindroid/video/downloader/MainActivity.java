package com.captaindroid.video.downloader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.captaindroid.video.downloader.dto.VideoLink;
import com.captaindroid.video.downloader.events.OnVideoFoundListener;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            VideoDownloader.getInstance().getResults(this, "https://www.youtube.com/shorts/AMGSeQSu5wA", new OnVideoFoundListener() {
                @Override
                public void onVideo(ArrayList<VideoLink> videos) {
                    for (int i = 0; i < videos.size(); i++) {
                        Log.e("size", videos.get(i).isHasAudio() + " " + videos.get(i).getQuality() + " adf " + " " + videos.get(i).getUrl());
                    }

                }

                @Override
                public void onError(String error) {
                    Log.e("size", error);
                }
            });
        } catch (URISyntaxException e) {
            Log.e("size", e.getMessage());
        }
    }
}
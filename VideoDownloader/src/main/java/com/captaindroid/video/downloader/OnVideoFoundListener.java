package com.captaindroid.video.downloader;


import java.util.ArrayList;

public interface OnVideoFoundListener {
    void onVideo(ArrayList<VideoLink> videos);
    void onError(String error);
}

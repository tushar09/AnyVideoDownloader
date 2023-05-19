package com.captaindroid.video.downloader.events;


import com.captaindroid.video.downloader.dto.VideoLink;

import java.util.ArrayList;

public interface OnVideoFoundListener {
    void onVideo(ArrayList<VideoLink> videos);
    void onError(String error);
}

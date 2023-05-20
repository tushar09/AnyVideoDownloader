package com.captaindroid.video.downloader.dto;

public class VideoLink {
    private String url;
    private String quality;
    private String format;
    private String thumbnail;
    private boolean isAudioAvailable;

    public VideoLink(String url, String quality, String format, String thumbnail, boolean isAudioAvailable) {
        this.url = url;
        this.quality = quality;
        this.format = format;
        this.thumbnail = thumbnail;
        this.isAudioAvailable = isAudioAvailable;
    }

    public String getUrl() {
        return url;
    }

    public String getQuality() {
        return quality;
    }

    public String getFormat() {
        return format;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public boolean isAudioAvailable() {
        return isAudioAvailable;
    }
}

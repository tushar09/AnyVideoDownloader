
# Any Video Downloader

**Any Video Downloader** is an Android library that enables users to download videos from various online sources, with a particular emphasis on popular video sharing platforms. This library offers the capability to download videos in different quality options, including 240P, 360P, 480P, 720P, 1080P, and more. It utilizes the Jsoup library and webview technology to facilitate the downloading process. Additionally, the library provides pre-defined proguard rules, ensuring seamless integration and compatibility with other Android projects.
## Installation

Add it in your root build.gradle at the end of repositories:

```bash
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Add the dependency:

```bash
  implementation 'com.github.tushar09:AnyVideoDownloader:1.1.0'
```

    
## Usage/Examples

```java
import com.captaindroid.video.downloader.VideoDownloader;
import com.captaindroid.video.downloader.dto.VideoLink;
import com.captaindroid.video.downloader.events.OnVideoFoundListener;

VideoDownloader.getInstance().getResults(this, "https://youtube.com/shorts/t-ZCPhewCMk", new OnVideoFoundListener() {
    @Override
    public void onVideo(ArrayList<VideoLink> videos) {
        for (int i = 0; i < videos.size(); i++) {
            Log.e("video", videos.get(i).isAudioAvailable() + " " + videos.get(i).getQuality() + " " + videos.get(i).getUrl());
        }

    }

    @Override
    public void onError(String error) {
        Log.e("video error", error);
    }
});
```


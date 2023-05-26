
# Any Video Downloader

**Any Video Downloader** is a library for android which can download any video from internet specially from any tube including all availabe quality like 240P, 360P, 480P, 720P 1080P and so on. It uses **Jsoup** and **webview** behind the scene. This library also comes with pre-defined proguard rules.
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


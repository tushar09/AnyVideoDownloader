package com.captaindroid.video.downloader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AlertDialog;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class VideoDownloader {
    
    private static volatile VideoDownloader INSTANCE = null;

    private boolean stopLoop = false;

    private String url;

    public static VideoDownloader getInstance(){
        if(INSTANCE == null) {
            synchronized (VideoDownloader.class) {
                if (INSTANCE == null) {
                    INSTANCE = new VideoDownloader();
                }
            }
        }
        INSTANCE.stopLoop = false;
        return INSTANCE;
    }

    public ArrayList<String> getResults(Context context, String url, WebView wv, Activity activity, OnVideoFoundListener onVideoFoundListener) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        domain = domain.startsWith("www.") ? domain.substring(4) : domain;
        this.url = url;

        WebSettings settings = wv.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        settings.setUserAgentString("Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        //settings.setAppCachePath(context.getCacheDir().getAbsolutePath() + "/webViewCache");
        settings.setGeolocationDatabasePath(context.getFilesDir().getPath());
        settings.setGeolocationEnabled(true);
        //settings.setAppCacheEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportMultipleWindows(true);

        wv.setInitialScale(1);
        wv.setLongClickable(true);
        wv.addJavascriptInterface(new MyJavaScriptInterface(context, onVideoFoundListener), "HtmlViewer");
        wv.setWebChromeClient(new WebChromeClient());

        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String u) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("ht", url);
                        view.loadUrl("javascript:window.HtmlViewer.showHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
                        view.loadUrl("javascript:var uselessvar = document.getElementById('sf_url').value='" + url + "';");
                        //view.loadUrl("javascript:var uselessvar = document.getElementById('sf_url').value='https://www.facebook.com/watch?v=449171080703999';");
                        //view.loadUrl("javascript:var uselessvar = document.getElementById('sf_url').value='https://vimeo.com/channels/staffpicks/629606430';");
                        view.loadUrl("javascript:var uselessvar = document.getElementById('sf_submit').click();");
                    }
                }, 4000);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (stopLoop == false){
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    wv.loadUrl("javascript:window.HtmlViewer.showHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
                                }
                            });
                            try {
                                Thread.sleep(4000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();



            }


        });

        //wv.loadUrl("https://www.youtube.com/watch?v=dAHqcEnPIXw");
        wv.loadUrl("https://en.savefrom.net/");

        return null;
    }

    class MyJavaScriptInterface {

        private Context ctx;
        private OnVideoFoundListener onVideoFoundListener;

        MyJavaScriptInterface(Context ctx, OnVideoFoundListener onVideoFoundListener) {
            this.ctx = ctx;
            this.onVideoFoundListener = onVideoFoundListener;
        }

        @JavascriptInterface
        public void showHTML(String html) {
            if (html.contains("data-type")) {
                Extractor extractor = new Extractor();

                stopLoop = true;
                try{
                    onVideoFoundListener.onVideo(extractor.extractData(html));
                }catch (Exception e){
                    Log.e("ht", e.getMessage());
                }

            }else if(html.contains("data-hid")){

                stopLoop = true;
                //no video

            }

        }
    }
}

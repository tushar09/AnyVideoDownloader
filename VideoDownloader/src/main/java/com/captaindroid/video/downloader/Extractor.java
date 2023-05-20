package com.captaindroid.video.downloader;

import com.captaindroid.video.downloader.dto.VideoLink;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Extractor {
    public ArrayList<VideoLink> extractData(String html){
        Document doc = Jsoup.parse(html);
        String thumb = "";
        if(doc.getElementsByClass("thumb").first() != null){
            thumb = doc.getElementsByClass("thumb").first().attr("src");
        }

        Elements main = doc.select("a");
        ArrayList<VideoLink> links = new ArrayList<>();
        for (Element el : main){
            if(el.attr("title").startsWith("video format")
                    || el.attr("data-type").equals("mp4")
                    || el.attr("data-type").toLowerCase().contains("webm")
                    || el.attr("data-type").toLowerCase().contains("vid")
            ){
                String quality = el.attr("title").replaceAll("[^0-9]", "").trim();
                if(quality == null || quality.equals("")){
                    quality = el.attr("data-quality");
                }
                VideoLink vl = new VideoLink(el.attr("href"),
                        quality,
                        el.attr("data-type").toUpperCase(),
                        thumb,
                        !el.attr("title").contains("without audio")
                );

                links.add(vl);
            }

        }
        return links;
    }
}

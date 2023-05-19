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
        //Elements main = doc.getElementsByClass("expand");
        //Elements main = doc.select("a");
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
                VideoLink vl = new VideoLink();
                vl.setUrl(el.attr("href"));
                vl.setFormat(el.attr("data-type").toUpperCase());
                vl.setQuality(el.attr("title").replaceAll("[^0-9]", "").trim());
                if(vl.getQuality() == null || vl.getQuality().equals("")){
                    vl.setQuality(el.attr("data-quality"));
                }
                vl.setHasAudio(!el.attr("title").contains("without audio"));
                vl.setThumbnail(thumb);
                links.add(vl);
            }

        }
        return links;
    }
}

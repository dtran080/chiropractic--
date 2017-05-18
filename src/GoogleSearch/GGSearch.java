/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoogleSearch;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.lang.model.util.Elements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

/**
 *
 * @author Duy Anh Tang
 */
public class GGSearch {

    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        String google = "http://www.google.com/search?q=";
        String search = "code of duty";
        String charset = "UTF-8";
        String userAgent = "ExampleBot 1.0 (+http://example.com/bot)"; // Change this to your company's name and bot homepage!

        org.jsoup.select.Elements links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select(".g>.r>a");
        System.out.println("length: "+links.size());
        for (Element link : links) {
            String title = link.text();
            String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
            url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");

            if (!url.startsWith("http")) {
                continue; // Ads/news/etc.
            }

            System.out.println("Title: " + title);
            System.out.println("URL: " + url);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoogleSearch;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Duy Anh Tang
 */
public class ImagesFetch {

    public static void main(String[] args) throws IOException {
        String url="http://dantri.com.vn/";
        String userAgent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) "
                + "Chrome/58.0.3029.96 Safari/537.36";
        Document doc = Jsoup.connect(url).userAgent(userAgent).get();
        Elements imgs = doc.select("img");
        for (Element img : imgs) {
            String link = img.attr("src");
            System.out.println(link);
        }
    }
}

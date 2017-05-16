/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoogleSearch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import urlreader.URLReader;

/**
 *
 * @author Duy Anh Tang
 */
public class URLExtract {
    public static String html;
    public static void main(String[] args) {
        URLReader urlReader = new URLReader("www.bridgeport.edu");
        urlReader.readURL();
       // html = "<p>An <a href='http://example.com/'><b>example</b></a> link.</p>";
        html=urlReader.html;
         System.out.println(html);
        Document doc = Jsoup.parse(html);
        Element link = doc.select("a").first();

        String text = doc.body().text(); // "An example link"
       // String linkHref = link.attr("href"); // "http://example.com/"
       // String linkText = link.text(); // "example""
        System.out.println("\n\n"+text);
      //  String linkOuterH = link.outerHtml();
    // "<a href="http://example.com"><b>example</b></a>"
      //  String linkInnerH = link.html(); // "<b>example</b>"
    }
    public static String wordExtract(String html){
         System.out.println(html);
        Document doc = Jsoup.parse(html);
        return doc.body().text();
    }
}

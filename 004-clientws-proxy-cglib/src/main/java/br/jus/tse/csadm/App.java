package br.jus.tse.csadm;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        String urlbase = "https://www.tse.jus.br/legislacao/compilada/res/2024/resolucao-no-23-738-de-27-de-fevereiro-de-2024";
        
        Path indexPath = Paths.get(System.getProperty("user.home") + "/tmp/lucene-indexes");
        
        System.out.println(indexPath);
        
        
        Document doc = Jsoup.connect(urlbase).get();
        
        Elements newsHeadlines = doc.select("p");
        for (Element headline : newsHeadlines) {
          if (headline.text().toUpperCase().contains("ELEIÇÃO")) {
              System.out.println(headline.text());
          }
       }
    }
}

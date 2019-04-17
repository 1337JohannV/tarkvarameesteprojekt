package ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DocumentManager {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0";
    private static final String REFERRERER = "http://www.google.com";

    public static Document getDocument(String url) {
        try {
            return Jsoup.connect(url)
                    .ignoreContentType(true)
                    .userAgent(USER_AGENT)
                    .referrer(REFERRERER)
                    .timeout(12000)
                    .followRedirects(true)
                    .validateTLSCertificates(false)
                    .execute().parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

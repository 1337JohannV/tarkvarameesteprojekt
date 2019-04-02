package scraper.maxima;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaximaUrlManager {

    public static List<String> getSubCatUrls(Category category) {

        switch (category) {

            case JUUSTUD:
                return Arrays.asList("https://www.barbora.ee/piimatooted-rasv-munad/juustud",
                        "https://www.barbora.ee/piimatooted-rasv-munad/kodujuustud");


            case LEIVAD_SAIAD_KONDIITRITOOTED:
                return Collections.singletonList("https://www.barbora.ee/leivad-saiad-kondiitritooted");

            case JOOGID:
                Arrays.asList("https://www.barbora.ee/joogid-tubakatooted/karastusjoogid",
                        "https://www.barbora.ee/piimatooted-rasv-munad/kodujuustud");
        }

    return null;
    }
}

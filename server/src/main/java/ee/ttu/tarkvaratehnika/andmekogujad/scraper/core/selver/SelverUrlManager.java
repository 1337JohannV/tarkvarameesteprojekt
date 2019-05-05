package ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.selver;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Category;

class SelverUrlManager {

    private static final String BASE_URL = "https://www.selver.ee/";
    private static final String RESULT_LIMIT = "96";

    static String getCategoryName(Category category) {
        switch (category) {
            case PUU_JA_KOOGIVILJAD:
                return "puu-ja-koogiviljad";
            case LIHA_JA_KALA:
                return "liha-ja-kalatooted";
            case PIIMATOOTED_MUNAD_VOID:
                return "piimatooted-munad-void";
            case JUUSTUD:
                return "juustud";
            case LEIVAD_SAIAD_KONDIITRITOOTED:
                return "leivad-saiad-kondiitritooted";
            case KUIVAINED_HOIDISED:
                return "kuivained-hoidised";
            case KASTMED_OLID:
                return "kastmed-olid";
            case MAIUSTUSED_KUPSISED_NAKSID:
                return "maiustused-kupsised-naksid";
            case KULMUTATUD_TOIDUKAUBAD:
                return "kulmutatud-toidukaubad";
            case JOOGID:
                return "joogid";
            default:
                return "";
        }
    }

    static String buildCategoryUrl(Category category) {
        return new StringBuilder()
                .append(BASE_URL)
                .append(getCategoryName(category))
                .append("?limit=")
                .append(RESULT_LIMIT)
                .append(("&p="))
                .toString();
    }

    static String buildCategoryUrl(Category category, int page) {
        return new StringBuilder(buildCategoryUrl(category))
                .append(page)
                .toString();
    }
}
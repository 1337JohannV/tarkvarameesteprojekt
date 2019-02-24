package scraper.selver;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;

public class SelverUrlManager {

    private static final String BASE_URL = "https://www.selver.ee/";
    private static final String RESULT_LIMIT = "96";

    static String getCategoryName(Category category) {
        switch (category) {
            case PUU_JA_KOOGIVILJAD: return "puu-ja-koogiviljad";
            case LIHA_JA_KALA: return "liha-ja-kalatooted";
            case PIIMATOOTED_MUNAD_VOID: return"piimatooted-munad-void";
            case JUUSTUD: return "juustud";
            case LEIVAD_SAIAD_KONDIITRITOOTED: return "leivad-saiad-kondiitritooted";
            case KUIVAINED_HOIDISED: return "kuivained-hoidised";
            case KASTMED_OLID: return "kastmed-olid";
            case MAIUSTUSED_KUPSISED_NAKSID: return "maiustused-kupsised-naksid";
            case KULMUTATUD_TOIDUKAUBAD: return "kulmutatud-toidukaubad";
            case JOOGID: return "joogid";
            default: return "";
        }
    }

    public static String buildCategoryUrl(Category category) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL);
        sb.append(getCategoryName(category));
        sb.append("?limit=");
        sb.append(RESULT_LIMIT);
        sb.append("&p=%d");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(buildCategoryUrl(Category.PUU_JA_KOOGIVILJAD));
    }
}

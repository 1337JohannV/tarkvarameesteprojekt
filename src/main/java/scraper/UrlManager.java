package scraper;

public class UrlManager {

    private static final String BASE_URL = "https://www.selver.ee/";
    private static final String RESULT_LIMIT = "96";

    enum Category {
        PUU_JA_KOOGIVILJAD ("puu-ja-koogiviljad"),
        LIHA_JA_KALA ("liha-ja-kalatooted"),
        PIIMATOOTED_MUNAD_VOID ("piimatooted-munad-void"),
        JUUSTUD ("juustud"),
        LEIVAD_SAIAD_KONDIITRITOOTED ("leivad-saiad-kondiitritooted"),
        //VALMISTOIDUD ("valmistoidud"),
        KUIVAINED_HOIDISED ("kuivained-hoidised"),
        KASTMED_OLID ("kastmed-olid"),
        MAIUSTUSED_KUPSISED_NAKSID ("maiustused-kupsised-naksid"),
        KULMUTATUD_TOIDUKAUBAD ("kulmutatud-toidukaubad"),
        JOOGID ("joogid"),
        KAMPAANIA ("soodushinnaga-tooted");

        private final String name;

        Category(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }
    }

    public static String buildCategoryUrl(Category cat) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL);
        sb.append(cat.toString());
        sb.append("?limit=");
        sb.append(RESULT_LIMIT);
        sb.append("&p=%d");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(buildCategoryUrl(Category.PUU_JA_KOOGIVILJAD));
    }
}

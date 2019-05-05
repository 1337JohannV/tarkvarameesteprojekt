package ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.maxima;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Category;

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
                return Arrays.asList("https://www.barbora.ee/joogid-tubakatooted/karastusjoogid",
                        "https://www.barbora.ee/joogid-tubakatooted/lahja-alkohol", "https://www.barbora.ee/joogid-tubakatooted/veed",
                        "https://www.barbora.ee/joogid-tubakatooted/veinid", "https://www.barbora.ee/joogid-tubakatooted/mahlad-nektarid-ja-mahlajoogid",
                        "https://www.barbora.ee/joogid-tubakatooted/kange-alkohol", "https://www.barbora.ee/joogid-tubakatooted/kohv-tee-ja-kakao",
                        "https://www.barbora.ee/joogid-tubakatooted/alkoholivabad-joogid");
            case LIHA_JA_KALA:
                return Arrays.asList("https://www.barbora.ee/liha-kala-valmistoit/varske-loomaliha",
                        "https://www.barbora.ee/liha-kala-valmistoit/varske-kala-ja-mereannid",
                        "https://www.barbora.ee/liha-kala-valmistoit/sviezia-paukstiena",
                        "https://www.barbora.ee/liha-kala-valmistoit/kalatooted",
                        "https://www.barbora.ee/liha-kala-valmistoit/looma-ja-linnulihast-poolfabrikaadid",
                        "https://www.barbora.ee/liha-kala-valmistoit/looma-ja-linnulihatooted", //looma-ja linnuliha tooted splittida v'iksemateks linkideks
                        "https://www.barbora.ee/liha-kala-valmistoit/valmistoit");
            case PIIMATOOTED_MUNAD_VOID:
                return Arrays.asList("https://www.barbora.ee/piimatooted-rasv-munad/piimad-ja-koored",
                        "https://www.barbora.ee/piimatooted-rasv-munad/hapendatud-piimatooted",
                        "https://www.barbora.ee/piimatooted-rasv-munad/rasvad-ja-voided/voi",
                        "https://www.barbora.ee/piimatooted-rasv-munad/rasvad-ja-voided/margariinid-ja-rasvasegud",
                        "https://www.barbora.ee/piimatooted-rasv-munad/jogurtid-ja-kohupiimatooted",  //split jogurtid
                        "https://www.barbora.ee/piimatooted-rasv-munad/laktoosivabad-tooted",
                        "https://www.barbora.ee/piimatooted-rasv-munad/munad");
            case PUU_JA_KOOGIVILJAD:
                return Collections.singletonList("https://www.barbora.ee/koogiviljad-puuviljad");
            case KULMUTATUD_TOIDUKAUBAD:
                return Arrays.asList("https://www.barbora.ee/kulmutatud-tooted/kulmutatud-koogiviljad-seened-ja-marjad",
                        "https://www.barbora.ee/kulmutatud-tooted/kulmutatud-pooltooted",
                        "https://www.barbora.ee/kulmutatud-tooted/kulmutatud-kondiitritooted",
                        "https://www.barbora.ee/kulmutatud-tooted/jaatised-ja-jaakuubikud",
                        "https://www.barbora.ee/kulmutatud-tooted/kulmutatud-liha-linnuliha-ja-muud-tooted",
                        "https://www.barbora.ee/kulmutatud-tooted/kulmutatud-kala-ja-meredelikatessid");
            case KASTMED_OLID:
                return Arrays.asList("https://www.barbora.ee/kauasailivad-toidukaubad/olid",
                        "https://www.barbora.ee/kauasailivad-toidukaubad/kastmed");
            case KUIVAINED_HOIDISED:
                return Arrays.asList("https://www.barbora.ee/kauasailivad-toidukaubad/makaronid",
                        "https://www.barbora.ee/kauasailivad-toidukaubad/jahud",
                        "https://www.barbora.ee/kauasailivad-toidukaubad/riisid",
                        "https://www.barbora.ee/kauasailivad-toidukaubad/tangained",
                        "https://www.barbora.ee/kauasailivad-toidukaubad/maitseained",
                        "https://www.barbora.ee/kauasailivad-toidukaubad/maailma-koogid",
                        "https://www.barbora.ee/kauasailivad-toidukaubad/hoidised-ja-konservid",
                        "https://www.barbora.ee/kauasailivad-toidukaubad/hommikusoogid-ja-batoonid");
            case MAIUSTUSED_KUPSISED_NAKSID:
                return Arrays.asList("https://www.barbora.ee/kauasailivad-toidukaubad/kommikarbid",
                        "https://www.barbora.ee/kauasailivad-toidukaubad/snakid",
                        "https://www.barbora.ee/kauasailivad-toidukaubad/kompvekid",
                        "https://www.barbora.ee/kauasailivad-toidukaubad/sokolaadid",
                        "https://www.barbora.ee/kauasailivad-toidukaubad/muud-maiustused",
                        "https://www.barbora.ee/kauasailivad-toidukaubad/kupsised",
                        "https://www.barbora.ee/kauasailivad-toidukaubad/kupsised");
            default:
                return null;
        }
    }
}

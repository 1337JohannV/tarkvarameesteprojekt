package scraper.prisma;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Category;

import java.util.Arrays;
import java.util.List;

class PrismaUrlManager {

    static List<String> getSubCatUrls(Category category) {

        switch (category) {

            case PUU_JA_KOOGIVILJAD:

                String[] vegetableArray = {"https://www.prismamarket.ee/products/16972",
                        "https://www.prismamarket.ee/products/16974",
                        "https://www.prismamarket.ee/products/16977", "https://www.prismamarket.ee/products/16973",
                        "https://www.prismamarket.ee/products/16975",
                        "https://www.prismamarket.ee/products/16976", "https://www.prismamarket.ee/products/17336"};

                return Arrays.asList(vegetableArray);

            case LIHA_JA_KALA:
                String[] meatArray = {"https://www.prismamarket.ee/products/19273",
                        "https://www.prismamarket.ee/products/19274", "https://www.prismamarket.ee/products/19275",
                        "https://www.prismamarket.ee/products/19276", "https://www.prismamarket.ee/products/19290",
                        "https://www.prismamarket.ee/products/19280", "https://www.prismamarket.ee/products/19281",
                        "https://www.prismamarket.ee/products/19282", "https://www.prismamarket.ee/products/19283",
                        "https://www.prismamarket.ee/products/19284", "https://www.prismamarket.ee/products/19286",
                        "https://www.prismamarket.ee/products/19287", "https://www.prismamarket.ee/products/19288",
                        "https://www.prismamarket.ee/products/19289", "https://www.prismamarket.ee/products/19291",
                        "https://www.prismamarket.ee/products/17152", "https://www.prismamarket.ee/products/17156",
                        "https://www.prismamarket.ee/products/17158", "https://www.prismamarket.ee/products/17159",
                        "https://www.prismamarket.ee/products/17160", "https://www.prismamarket.ee/products/17161",
                        "https://www.prismamarket.ee/products/17162", "https://www.prismamarket.ee/products/17163",
                        "https://www.prismamarket.ee/products/17164", "https://www.prismamarket.ee/products/17157"
                };
                return Arrays.asList(meatArray);

            case PIIMATOOTED_MUNAD_VOID:
                String[] milkArray = {"https://www.prismamarket.ee/products/17097",
                        "https://www.prismamarket.ee/products/17099", "https://www.prismamarket.ee/products/17100",
                        "https://www.prismamarket.ee/products/17101", "https://www.prismamarket.ee/products/17102",
                        "https://www.prismamarket.ee/products/17103", "https://www.prismamarket.ee/products/17104",
                        "https://www.prismamarket.ee/products/17105", "https://www.prismamarket.ee/products/17106",
                        "https://www.prismamarket.ee/products/17107", "https://www.prismamarket.ee/products/17108",
                        "https://www.prismamarket.ee/products/17109", "https://www.prismamarket.ee/products/17117",
                        "https://www.prismamarket.ee/products/17118", "https://www.prismamarket.ee/products/17119",
                        "https://www.prismamarket.ee/products/17115", "https://www.prismamarket.ee/products/17178",
                        "https://www.prismamarket.ee/products/17176", "https://www.prismamarket.ee/products/17175",
                        "https://www.prismamarket.ee/products/17177"
                };
                return Arrays.asList(milkArray);

            case JUUSTUD:
                String[] cheeseArray = {"https://www.prismamarket.ee/products/17147",
                        "https://www.prismamarket.ee/products/17148", "https://www.prismamarket.ee/products/17149",
                        "https://www.prismamarket.ee/products/17150"
                };
                return Arrays.asList(cheeseArray);

            case LEIVAD_SAIAD_KONDIITRITOOTED:
                String[] breadArray = {"https://www.prismamarket.ee/products/17205",
                        "https://www.prismamarket.ee/products/17206", "https://www.prismamarket.ee/products/17207",
                        "https://www.prismamarket.ee/products/17208", "https://www.prismamarket.ee/products/17209",
                        "https://www.prismamarket.ee/products/17210", "https://www.prismamarket.ee/products/17211",
                        "https://www.prismamarket.ee/products/17213", "https://www.prismamarket.ee/products/17214",
                        "https://www.prismamarket.ee/products/17216", "https://www.prismamarket.ee/products/17217",
                        "https://www.prismamarket.ee/products/17218", "https://www.prismamarket.ee/products/17219"
                };
                return Arrays.asList(breadArray);

            case JOOGID:
                String[] drinksArray = {"https://www.prismamarket.ee/products/17252",
                        "https://www.prismamarket.ee/products/17694", "https://www.prismamarket.ee/products/17695",
                        "https://www.prismamarket.ee/products/17696", "https://www.prismamarket.ee/products/17254",
                        "https://www.prismamarket.ee/products/17255", "https://www.prismamarket.ee/products/17256",
                        "https://www.prismamarket.ee/products/17258", "https://www.prismamarket.ee/products/17260",
                        "https://www.prismamarket.ee/products/17261", "https://www.prismamarket.ee/products/17262",
                        "https://www.prismamarket.ee/products/17263", "https://www.prismamarket.ee/products/17264",
                        "https://www.prismamarket.ee/products/19211", "https://www.prismamarket.ee/products/19210",
                        "https://www.prismamarket.ee/products/19213", "https://www.prismamarket.ee/products/19208"
                };
                return Arrays.asList(drinksArray);

            case KASTMED_OLID:
                String[] oilArray = {"https://www.prismamarket.ee/products/17179",
                        "https://www.prismamarket.ee/products/17180", "https://www.prismamarket.ee/products/17181",
                        "https://www.prismamarket.ee/products/17182", "https://www.prismamarket.ee/products/17183",
                        "https://www.prismamarket.ee/products/17320", "https://www.prismamarket.ee/products/17562"
                };
                return Arrays.asList(oilArray);

            case MAIUSTUSED_KUPSISED_NAKSID:
                String[] snacksArray = {"https://www.prismamarket.ee/products/18051",
                        "https://www.prismamarket.ee/products/18052", "https://www.prismamarket.ee/products/18056",
                        "https://www.prismamarket.ee/products/18057", "https://www.prismamarket.ee/products/18059",
                        "https://www.prismamarket.ee/products/18060", "https://www.prismamarket.ee/products/18061",
                        "https://www.prismamarket.ee/products/19448", "https://www.prismamarket.ee/products/19449",
                        "https://www.prismamarket.ee/products/19450", "https://www.prismamarket.ee/products/19451",
                        "https://www.prismamarket.ee/products/18064", "https://www.prismamarket.ee/products/18065",
                        "https://www.prismamarket.ee/products/18066", "https://www.prismamarket.ee/products/17567"
                };

                return Arrays.asList(snacksArray);

            case KULMUTATUD_TOIDUKAUBAD:
                String[] frozenArray = {"https://www.prismamarket.ee/products/17833",
                        "https://www.prismamarket.ee/products/17834", "https://www.prismamarket.ee/products/17835",
                        "https://www.prismamarket.ee/products/17836", "https://www.prismamarket.ee/products/17837",
                        "https://www.prismamarket.ee/products/17838",
                };
                return Arrays.asList(frozenArray);

            case KUIVAINED_HOIDISED:
                String[] dryArray = {"https://www.prismamarket.ee/products/17557",
                        "https://www.prismamarket.ee/products/17560", "https://www.prismamarket.ee/products/17561",
                        "https://www.prismamarket.ee/products/17563", "https://www.prismamarket.ee/products/17564",
                        "https://www.prismamarket.ee/products/17565", "https://www.prismamarket.ee/products/17566",
                        "https://www.prismamarket.ee/products/17568", "https://www.prismamarket.ee/products/17569",
                        "https://www.prismamarket.ee/products/17570", "https://www.prismamarket.ee/products/17656",
                        "https://www.prismamarket.ee/products/17657", "https://www.prismamarket.ee/products/17658",
                        "https://www.prismamarket.ee/products/17573", "https://www.prismamarket.ee/products/17665",
                        "https://www.prismamarket.ee/products/17668", "https://www.prismamarket.ee/products/17669",
                        "https://www.prismamarket.ee/products/17670", "https://www.prismamarket.ee/products/17671",
                        "https://www.prismamarket.ee/products/17672"


                };
                return Arrays.asList(dryArray);
        }


        return null;
    }


}

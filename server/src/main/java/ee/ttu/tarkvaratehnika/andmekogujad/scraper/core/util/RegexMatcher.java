package ee.ttu.tarkvaratehnika.andmekogujad.scraper.core.util;

import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Currency;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.enums.Unit;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product.Price;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product.Quantity;
import ee.ttu.tarkvaratehnika.andmekogujad.spring.data.entity.product.UnitPrice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {

    public static final String QUANTITY_PATTERN_MAXIMA = "(\\d+(.\\d+)?)?(kg|g|ml|l|tk|cl)\\b";
    private static final String QUANTITY_PATTERN = "(\\d+(.\\d+)?)?\\s?(kg|g|ml|l|tk|cl)\\b";
    private static final String PRICE_PATTERN = "(\\d+(.\\d+)?)?\\s?(€|eur|\\$|usd)";
    private static final String UNIT_PRICE_PATTERN = "(\\d+(.\\d+)?)?\\s?(€/kg|€/l|€/tk)\\b";

    public static Quantity extractQuantity(String s) {
        Matcher m = Pattern.compile(QUANTITY_PATTERN, Pattern.CASE_INSENSITIVE).matcher(s);
        try {
            if (m.find()) {
                Quantity quantity = new Quantity();
                if (m.group(1) != null) {
                    if (m.group(1).toLowerCase().contains("x")) {
                        String[] splitString = m.group(1).toLowerCase().split("x");
                        quantity.setValue(
                                Double.parseDouble(splitString[0]) * Double.parseDouble(splitString[1])
                        );
                    } else if (m.group(1).contains("-")) {
                        String[] splitString = m.group(1).split("-");
                        quantity.setValue(
                                (Double.parseDouble(splitString[0]) + Double.parseDouble(splitString[1])) / 2
                        );
                    } else {
                        //for maxima
                        try {
                            quantity.setValue(
                                    Double.parseDouble(m.group(1).replace(",", "."))
                            );
                        } catch (NumberFormatException e) {
                            quantity.setValue(500d);
                        }
                    }
                } else {
                    quantity.setValue(1.0);
                }
                if (m.group(3) != null) {
                    quantity.setUnit(matchUnit(m.group(3)));
                }
                return quantity;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Price extractPrice(String s) {
        Matcher m = Pattern.compile(PRICE_PATTERN, Pattern.CASE_INSENSITIVE).matcher(s);
        try {
            if (m.find()) {
                Price price = new Price();
                if (m.group(1) != null) {
                    price.setAmount(
                            Double.parseDouble(m.group(1).replace(",", "."))
                    );
                } else {
                    price.setAmount(1.0);
                }
                if (m.group(3) != null) {
                    price.setCurrency(matchCurrency(m.group(3)));
                }
                return price;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static UnitPrice extractUnitPrice(String s) {
        Matcher m = Pattern.compile(UNIT_PRICE_PATTERN, Pattern.CASE_INSENSITIVE).matcher(s);
        try {
            if (m.find()) {
                UnitPrice unitPrice = new UnitPrice();
                if (m.group(1) != null) {
                    unitPrice.setAmount(Double.parseDouble(m.group(1).replace(",", ".")));
                } else {
                    unitPrice.setAmount(1.0);
                }
                if (m.group(3) != null) {
                    String[] splitString = m.group(3).split("/");
                    unitPrice.setCurrency(matchCurrency(splitString[0]));
                    unitPrice.setPerUnit(matchUnit(splitString[1]));
                }
                return unitPrice;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Currency matchCurrency(String s) {
        switch (s.toLowerCase()) {
            case "€":
                return Currency.EUR;
            case "eur":
                return Currency.EUR;
            case "$":
                return Currency.USD;
            case "usd":
                return Currency.USD;
            default:
                return null;
        }
    }

    public static Unit matchUnit(String s) {
        switch (s.toLowerCase()) {
            case "kg":
                return Unit.KG;
            case "g":
                return Unit.G;
            case "l":
                return Unit.L;
            case "ml":
                return Unit.ML;
            case "tk":
                return Unit.TK;
            case "cl":
                return Unit.CL;
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        Price price = extractPrice("3,59 €");
        UnitPrice unitPrice = extractUnitPrice("0.120 kg 12,92 €/kg");
        Quantity quantity = extractQuantity("Liviko Laua viin 50 cl");

        System.out.println(price);
        System.out.println(unitPrice);
        System.out.println(quantity);
    }

}

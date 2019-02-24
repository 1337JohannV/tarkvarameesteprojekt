package scraper;

import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Price;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.Quantity;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.UnitPrice;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Currency;
import com.tarkvaramehed.projekt.tarkvarameesteprojekt.model.enums.Unit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {


    public static Quantity extractQuantity(String s) {
        String pattern = "(\\d+(.\\d+)?)?\\s(kg|g|ml|l|tk)\\b";
        Matcher m = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(s);
        while (m.find()) {
            Quantity quantity = new Quantity();
            if (m.group(1) != null) {
                quantity.setValue(Double.parseDouble(m.group(1).replace(",", ".")));
            } else {
                quantity.setValue(1f);
            }
            if (m.group(3) != null){
                quantity.setUnit(matchUnit(m.group(3)));
            } else {
                quantity.setUnit(null);
            }

            quantity.setUnit(matchUnit(m.group(3)));
            return quantity;
        }
        return null;
    }

    public static Price extractPrice(String s) {
        String pattern = "(\\d+(.\\d+)?)?\\s*(€|eur)";
        Matcher m = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(s);
        while (m.find()) {
            Price price = new Price();
            if (m.group(1) != null) {
                price.setAmount(Double.parseDouble(m.group(1).replace(",", ".")));
            } else {
                price.setAmount(1f);
            }
            if (m.group(3) != null){
                price.setCurrency(matchCurrency(m.group(3)));
            } else {
                price.setCurrency(null);
            }
            return price;
        }
        return null;
    }

    public static UnitPrice extractUnitPrice(String s) {
        String pattern = "(\\d+(.\\d+)?)?\\s*(€/kg|€/l|€/tk)\\b";
        Matcher m = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(s);
        while (m.find()) {
            UnitPrice unitPrice = new UnitPrice();
            if (m.group(1) != null) {
                unitPrice.setAmount(Double.parseDouble(m.group(1).replace(",", ".")));
            } else {
                unitPrice.setAmount(1f);
            }
            if (m.group(3) != null){
                String[] splitString = m.group(3).split("/");
                unitPrice.setCurrency(matchCurrency(splitString[0]));
                unitPrice.setPerUnit(matchUnit(splitString[1]));
            } else {
                unitPrice.setCurrency(null);
                unitPrice.setPerUnit(null);
            }
            return unitPrice;
        }
        return null;
    }

    public static Currency matchCurrency(String s) {
        switch (s.toLowerCase()) {
            case "€": return Currency.EUR;
            case "eur": return Currency.EUR;
            case "$": return Currency.USD;
            case "usd": return Currency.USD;
            default: return null;
        }
    }

    public static Unit matchUnit(String s) {
        switch(s.toLowerCase()) {
            case "kg": return Unit.KG;
            case "g": return Unit.G;
            case "l": return Unit.L;
            case "ml": return Unit.ML;
            case "tk": return Unit.TK;
            default: return null;
        }
    }

    public static void main(String[] args) {
        Price price = extractPrice("3,59 €");
        UnitPrice unitPrice = extractUnitPrice("7,29 €/tk");
        Quantity quantity = extractQuantity("Röstitud maapähkel sinihallitusjuustumaitselises koores, TAFFEL, 150 g");

        System.out.println("price:");
        System.out.println(price.getAmount());
        System.out.println(price.getCurrency());

        System.out.println("unitPrice:");
        System.out.println(unitPrice.getAmount());
        System.out.println(unitPrice.getCurrency());
        System.out.println(unitPrice.getPerUnit());


        System.out.println("quantity:");
        System.out.println(quantity.getValue());
        System.out.println(quantity.getUnit());
    }

}

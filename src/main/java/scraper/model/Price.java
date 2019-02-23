package scraper.model;

public class Price {

    private String store;
    private String currency;
    private String specialPrice;
    private double amount;
    private String unitPrice;
    private String quantity;

    public Price(String store, String currency, String unitPrice, double amount, String specialPrice) {
        this.store = store;
        this.currency = currency;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.specialPrice = specialPrice;
    }

    public Price() {
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(String specialPrice) {
        this.specialPrice = specialPrice;
    }

    @Override
    public String toString() {
        if (specialPrice != null) {
            return String.format("<price> %f %s, unitPrice: %s, specialPrice: %s @ %s", amount, currency, unitPrice, specialPrice, store);
        }
        return String.format("<price> %f %s, unitPrice: %s @ %s", amount, currency, unitPrice, store);
    }
}

package Helper;

import lombok.Getter;

@Getter
public class Price {
    private String currency;
    private Double amount;

    public Price(String currency, Double amount) {
        this.currency = currency;
        this.amount = amount;
    }
}

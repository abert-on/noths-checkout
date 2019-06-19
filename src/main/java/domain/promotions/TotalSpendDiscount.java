package domain.promotions;

import domain.Basket;
import domain.Product;
import interfaces.IProduct;
import interfaces.IPromotion;

import java.math.BigDecimal;

public class TotalSpendDiscount implements IPromotion {

    private Double threshold;
    private double discount;

    public TotalSpendDiscount(Double threshold, double discount) {
        this.threshold = threshold;
        this.discount = discount;
    }

    @Override
    public boolean isApplicable(Basket basket) {
        return basket.getTotal() > this.threshold;
    }

    @Override
    public Basket apply(Basket basket) {
        Basket updated = new Basket();
        for (IProduct product : basket) {
            updated.add(new Product(product.productCode(), product.name(), calculateNewPrice(product)));
        }
        return updated;
    }

    private BigDecimal calculateNewPrice(IProduct product) {
        return product.price().multiply(new BigDecimal(1 - discount));
    }
}

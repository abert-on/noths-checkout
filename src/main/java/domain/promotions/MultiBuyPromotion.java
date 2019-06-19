package domain.promotions;

import domain.Basket;
import domain.Product;
import interfaces.IProduct;
import interfaces.IPromotion;

import java.math.BigDecimal;

public class MultiBuyPromotion implements IPromotion {

    private String productCode;
    private int threshold;
    private BigDecimal newPrice;

    public MultiBuyPromotion(String productCode, int threshold, BigDecimal newPrice) {
        this.productCode = productCode;
        this.threshold = threshold;
        this.newPrice = newPrice;
    }

    @Override
    public boolean isApplicable(Basket basket) {
        return basket.countProduct(this.productCode) >= threshold;
    }

    @Override
    public Basket apply(Basket basket) {
        Basket updated = new Basket();
        for (IProduct product : basket) {
            updated.add(updateProductListing(product));
        }
        return updated;
    }

    private IProduct updateProductListing(IProduct product) {
        BigDecimal price = product.price();
        if (product.productCode().equals(this.productCode)) {
            price = this.newPrice;
        }
        return new Product(product.productCode(), product.name(), price);
    }
}

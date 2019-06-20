package domain;

import interfaces.IProduct;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Basket extends ArrayList<IProduct> {

    public double getTotal() {
        BigDecimal total = stream()
            .map(IProduct::price)
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO);
        
        BigDecimal rounded = total.setScale(2, BigDecimal.ROUND_HALF_UP);
        return rounded.doubleValue();
    }

    public long countProduct(String productCode) {
        return stream()
            .filter(p -> p.productCode().equals(productCode))
            .count();
    }
}

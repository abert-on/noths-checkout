package domain;

import interfaces.IProduct;

import java.math.BigDecimal;

public class Product implements IProduct {

    private String productCode;
    private String name;
    private BigDecimal price;

    public Product(String productCode, String name, BigDecimal price) {
        this.productCode = productCode;
        this.name = name;
        this.price = price;
    }

    @Override
    public String productCode() {
        return productCode;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public BigDecimal price() {
        return price;
    }
}

package domain.promotions;

import domain.Basket;
import domain.Product;
import interfaces.IPromotion;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class TotalSpendDiscountTest {

    @Test
    public void promotionNotApplicableOnEmptyBasket() {
        // setup
        IPromotion sut = new TotalSpendDiscount(1.0, 0.1);

        // test
        boolean result = sut.isApplicable(new Basket());

        // verify
        assertThat(result).isFalse();
    }

    @Test
    public void promotionNotApplicableWhenBasketTotalLowerThanThreshold() {
        // setup
        IPromotion sut = new TotalSpendDiscount(10.0, 0.1);
        Basket basket = new Basket();
        basket.add(new Product("001", "Test", BigDecimal.ONE));

        // test
        boolean result = sut.isApplicable(basket);

        // verify
        assertThat(result).isFalse();
    }

    @Test
    public void promotionNotApplicableWhenBasketTotalEqualToThreshold() {
        // setup
        IPromotion sut = new TotalSpendDiscount(10.0, 0.1);
        Basket basket = new Basket();
        basket.add(new Product("001", "Test", BigDecimal.TEN));

        // test
        boolean result = sut.isApplicable(basket);

        // verify
        assertThat(result).isFalse();
    }

    @Test
    public void promotionApplicableWhenBasketTotalHigherThanThreshold() {
        // setup
        IPromotion sut = new TotalSpendDiscount(1.0, 0.1);
        Basket basket = new Basket();
        basket.add(new Product("001", "Test", BigDecimal.TEN));

        // test
        boolean result = sut.isApplicable(basket);

        // verify
        assertThat(result).isTrue();
    }

    @Test
    public void promotionApplied() {
        // setup
        IPromotion sut = new TotalSpendDiscount(1.0, 0.1);
        Basket basket = new Basket();
        basket.add(new Product("001", "Test", BigDecimal.TEN));

        // test
        Basket result = sut.apply(basket);

        // verify
        assertThat(result.getTotal()).isEqualTo(9.0);
    }

}
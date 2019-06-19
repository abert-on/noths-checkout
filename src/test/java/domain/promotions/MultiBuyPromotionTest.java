package domain.promotions;

import domain.Basket;
import domain.Product;
import interfaces.IPromotion;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiBuyPromotionTest {

    @Test
    public void promotionNotApplicableOnEmptyBasket() {
        // setup
        IPromotion sut = new MultiBuyPromotion("001", 10, new BigDecimal(12.50));

        // test
        boolean result = sut.isApplicable(new Basket());

        // verify
        assertThat(result).isFalse();
    }

    @Test
    public void promotionNotApplicableWhenThresholdNotMet() {
        // setup
        IPromotion sut = new MultiBuyPromotion("001", 10, BigDecimal.ONE);
        Basket basket = new Basket();
        basket.add(new Product("001", "Test", BigDecimal.TEN));

        // test
        boolean result = sut.isApplicable(basket);

        // verify
        assertThat(result).isFalse();
    }

    @Test
    public void promotionApplicableWhenThresholdMet() {
        // setup
        IPromotion sut = new MultiBuyPromotion("001", 2, BigDecimal.ONE);
        Basket basket = new Basket();
        basket.add(new Product("001", "Test", BigDecimal.TEN));
        basket.add(new Product("001", "Test", BigDecimal.TEN));

        // test
        boolean result = sut.isApplicable(basket);

        // verify
        assertThat(result).isTrue();
    }

    @Test
    public void promotionAppliedWhenProductCodeMatches() {
        // setup
        IPromotion sut = new MultiBuyPromotion("001", 2, BigDecimal.ONE);
        Basket basket = new Basket();
        basket.add(new Product("001", "Test", BigDecimal.TEN));

        // test
        Basket result = sut.apply(basket);

        // verify
        assertThat(result.getTotal()).isEqualTo(BigDecimal.ONE.doubleValue());
    }

    @Test
    public void promotionNotAppliedWhenProductCodeDifferent() {
        // setup
        IPromotion sut = new MultiBuyPromotion("001", 2, BigDecimal.ONE);
        Basket basket = new Basket();
        basket.add(new Product("002", "Test", BigDecimal.TEN));

        // test
        Basket result = sut.apply(basket);

        // verify
        assertThat(result.getTotal()).isEqualTo(BigDecimal.TEN.doubleValue());
    }

}
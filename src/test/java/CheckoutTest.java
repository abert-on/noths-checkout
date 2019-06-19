import domain.Product;
import domain.promotions.MultiBuyPromotion;
import domain.promotions.TotalSpendDiscount;
import interfaces.IProduct;
import interfaces.IPromotion;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutTest {

    private IProduct travelCardHolder = new Product("001", "Travel Card Holder", new BigDecimal(9.25));
    private IProduct cufflinks = new Product("002", "Personalised Cufflinks", new BigDecimal(45.00));
    private IProduct tShirt = new Product("003", "Kids T-shirt", new BigDecimal(19.95));
    private IPromotion over60 = new TotalSpendDiscount(60.0, 0.1);
    private IPromotion travelCardMultiBuy = new MultiBuyPromotion("001", 2, new BigDecimal(8.5));

    @Test
    public void checkoutWithoutPromotion() {
        // setup
        Checkout sut = new Checkout(Collections.emptyList());
        sut.scan(travelCardHolder);
        sut.scan(cufflinks);

        // test
        Double result = sut.total();

        // verify
        assertThat(result).isEqualTo(54.25);
    }

    @Test
    public void checkoutWithSpendOver60Promotion() {
        // setup
        Checkout sut = new Checkout(Collections.singletonList(over60));
        sut.scan(travelCardHolder);
        sut.scan(cufflinks);
        sut.scan(tShirt);

        // test
        Double result = sut.total();

        // verify
        assertThat(result).isEqualTo(66.78);
    }

    @Test
    public void checkoutWithTravelCardMultiBuyPromotion() {
        // setup
        Checkout sut = new Checkout(Collections.singletonList(travelCardMultiBuy));
        sut.scan(travelCardHolder);
        sut.scan(tShirt);
        sut.scan(travelCardHolder);

        // test
        Double result = sut.total();

        // verify
        assertThat(result).isEqualTo(36.95);
    }

    @Test
    public void checkoutWithMultiplePromotions() {
        // setup
        Checkout sut = new Checkout(Arrays.asList(travelCardMultiBuy, over60));
        sut.scan(travelCardHolder);
        sut.scan(cufflinks);
        sut.scan(travelCardHolder);
        sut.scan(tShirt);

        // test
        Double result = sut.total();

        // verify
        assertThat(result).isEqualTo(73.76);
    }

    @Test
    public void checkoutWithMultipleNonApplicablePromotions() {
        // setup
        Checkout sut = new Checkout(Arrays.asList(travelCardMultiBuy, over60));
        sut.scan(tShirt);

        // test
        Double result = sut.total();

        // verify
        assertThat(result).isEqualTo(19.95);
    }
}

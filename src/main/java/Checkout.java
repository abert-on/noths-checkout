import domain.Basket;
import interfaces.IProduct;
import interfaces.IPromotion;

import java.util.List;

public class Checkout {

    private final List<IPromotion> promotions;
    private Basket basket = new Basket();

    public Checkout(List<IPromotion> promotions) {
        this.promotions = promotions;
    }

    /**
     * Adds a product to the checkout.
     * @param product to add.
     */
    public void scan(IProduct product) {
        this.basket.add(product);
    }

    /**
     * @return the total amount billable at checkout with any promotions applied.
     */
    public Double total() {
        Basket updated = applyPromotions(this.basket);
        return updated.getTotal();
    }

    private Basket applyPromotions(Basket basket) {
        Basket updated = (Basket) basket.clone();

        for (IPromotion promotion : this.promotions) {
            if (promotion.isApplicable(updated)) {
                updated = promotion.apply(updated);
            }
        }

        return updated;
    }
}

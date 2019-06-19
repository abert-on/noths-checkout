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

    public void scan(IProduct product) {
        this.basket.add(product);
    }

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

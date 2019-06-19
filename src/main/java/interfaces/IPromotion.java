package interfaces;

import domain.Basket;

public interface IPromotion {

    /**
     * Determines if the current promotion should be applied to a given basket.
     * @param basket the current basket being evaluated at checkout.
     * @return true if the basket is eligible for this promotion.
     */
    boolean isApplicable(Basket basket);

    /**
     * Applies the current promotion to a given basket.
     * @param basket the basket of products to apply the promotion to.
     * @return an updated basket with promotion applied.
     */
    Basket apply(Basket basket);
}

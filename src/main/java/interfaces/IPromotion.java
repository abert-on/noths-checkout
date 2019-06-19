package interfaces;

import domain.Basket;

public interface IPromotion {

    boolean isApplicable(Basket basket);

    Basket apply(Basket basket);
}

package ra.model.service.cart;

import ra.model.entity.Cart;
import ra.model.service.IGeneric;

public interface ICartService extends IGeneric<Cart> {
    boolean create(int userId);
}

package ra.model.service.shoppingCart;

import ra.model.entity.ShoppingCart;
import ra.model.service.IGeneric;

import java.util.List;

public interface IShoppingCartService extends IGeneric<ShoppingCart> {
    List<ShoppingCart> showAll(int id);
}

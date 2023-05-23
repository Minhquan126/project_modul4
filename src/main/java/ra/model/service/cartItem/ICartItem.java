package ra.model.service.cartItem;

import ra.model.entity.CartItem;
import ra.model.service.IGeneric;

import java.util.List;

public interface ICartItem extends IGeneric<CartItem> {
    boolean delete(int cartId,int productId);
    List<CartItem> showAll(int id);
    boolean checkout(int id);
    boolean productExist(int userId,int productId);
    boolean setQuantity(int userId, int productId,int quantity);
    float total(int userId);
}

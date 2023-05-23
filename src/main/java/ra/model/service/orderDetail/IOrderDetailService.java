package ra.model.service.orderDetail;

import ra.model.entity.CartItem;
import ra.model.entity.OrderDetail;
import ra.model.service.IGeneric;

public interface IOrderDetailService extends IGeneric<OrderDetail> {
    boolean create(int id, CartItem cartItem);
}

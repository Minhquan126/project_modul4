package ra.model.service.order;

import com.sun.org.apache.xpath.internal.operations.Or;
import ra.model.entity.Order;
import ra.model.entity.OrderShow;
import ra.model.service.IGeneric;

import java.util.List;

public interface IOrderService extends IGeneric<Order> {
    int create(int userId);
    float getTotal();
    float getTotalInMonth();
    int getCountTrue();
    List<Order> getOrderFalse();
    boolean setStatus(int id);
    boolean setTotal(int id,float total);
}

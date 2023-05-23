package ra.model.service;

import com.sun.org.apache.xpath.internal.operations.Or;
import ra.model.entity.OrderShow;

import java.util.List;

public interface IShowOrders extends IGeneric<OrderShow> {
    List<OrderShow> findByUserId(int id);
    List<OrderShow> findByOrderId(int id);
}

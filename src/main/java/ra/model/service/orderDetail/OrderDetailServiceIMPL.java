package ra.model.service.orderDetail;

import ra.model.entity.CartItem;
import ra.model.entity.OrderDetail;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class OrderDetailServiceIMPL implements IOrderDetailService{
    @Override
    public List<OrderDetail> findAll() {
        return null;
    }

    @Override
    public boolean save(OrderDetail orderDetail) {
        return false;
    }

    @Override
    public boolean update(OrderDetail orderDetail) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public OrderDetail findById(int id) {
        return null;
    }

    @Override
    public boolean create(int id, CartItem cartItem) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.getConnection();
            call = conn.prepareCall("{call proc_createOrderDetail(?,?,?,?,?)}");
            call.setInt(1,id);
            call.setInt(2,cartItem.getProductId());
            call.setString(3,cartItem.getProductName());
            call.setFloat(4,cartItem.getPrice());
            call.setInt(5,cartItem.getQuantity());
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return true;
    }
}

package ra.model.service.order;

import ra.model.entity.Order;
import ra.model.entity.OrderShow;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceIMPL implements IOrderService {
    @Override
    public List<Order> findAll() {
        Connection conn = null;
        CallableStatement call = null;
        List<Order> list = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            call = conn.prepareCall("{call proc_getAllOrders()}");
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setUserId(resultSet.getInt("userId"));
                order.setTotal(resultSet.getFloat("total"));
                order.setDate(resultSet.getDate("dateBuy"));
                order.setStatus(resultSet.getBoolean("status"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(Order order) {
        return false;
    }

    @Override
    public boolean update(Order order) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public Order findById(int id) {
        Connection conn = null;
        CallableStatement call = null;
        Order order = new Order();
        try {
            conn = ConnectionDB.getConnection();
            call = conn.prepareCall("{call proc_getAllOrders()}");
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()) {
                order.setOrderId(resultSet.getInt("orderId"));
                order.setUserId(resultSet.getInt("userId"));
                order.setTotal(resultSet.getFloat("total"));
                order.setDate(resultSet.getDate("dateBuy"));
                order.setStatus(resultSet.getBoolean("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;

    }

    @Override
    public int create(int userId) {
        Connection conn = null;
        CallableStatement call = null;
        int orderId = -1;
        try {
            conn = ConnectionDB.getConnection();
            call = conn.prepareCall("{call proc_createOrders(?,?)}");
            call.setInt(1, userId);
            call.registerOutParameter(2, Types.INTEGER);
//            call.setInt(3,Types.INTEGER);
            call.execute();
            orderId = call.getInt(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return orderId;
    }

    @Override
    public float getTotal() {
        Connection conn = null;
        CallableStatement call = null;
        float sum = 0;
        try {
            conn = ConnectionDB.getConnection();
            call = conn.prepareCall("{call proc_getTotal()}");
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getFloat("sum");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return sum;
    }

    @Override
    public float getTotalInMonth() {
        Connection conn = null;
        CallableStatement call = null;
        float sum = 0;
        try {
            conn = ConnectionDB.getConnection();
            call = conn.prepareCall("{call proc_getTotalInMonth()}");
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getFloat("sumMonth");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return sum;
    }

    @Override
    public int getCountTrue() {
        int countTrue = 0;
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.getConnection();
            call = conn.prepareCall("{call proc_getCountOrders()}");
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()){
                countTrue = resultSet.getInt("count");}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return countTrue;
    }

    @Override
    public List<Order> getOrderFalse() {
        Connection conn = null;
        CallableStatement call = null;
        List<Order> list = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            call = conn.prepareCall("{call getAllOrders()}");
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setUserId(resultSet.getInt("userId"));
                order.setTotal(resultSet.getFloat("total"));
                order.setDate(resultSet.getDate("dateBuy"));
                order.setStatus(resultSet.getBoolean("status"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean setStatus(int id) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.getConnection();
            call = conn.prepareCall("{call setOrderStatus(?)}");
            call.setInt(1, id);
            call.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return true;
    }

    @Override
    public boolean setTotal(int id,float total) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.getConnection();
            call = conn.prepareCall("{call setTotal(?,?)}");
            call.setInt(1,id);
            call.setFloat(2,total);
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return false;
    }

}

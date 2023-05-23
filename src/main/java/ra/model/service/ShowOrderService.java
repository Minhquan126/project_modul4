package ra.model.service;

import com.sun.org.apache.xpath.internal.operations.Or;
import ra.model.entity.OrderShow;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShowOrderService implements IShowOrders{

    @Override
    public List<OrderShow> findAll() {
        Connection conn = null;
        CallableStatement call = null;
        List<OrderShow> list = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            call = conn.prepareCall("{call proc_showAllOrders()}");
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()){
                OrderShow orderShow = new OrderShow();
                orderShow.setOrderId(resultSet.getInt("orderId"));
                orderShow.setUserId(resultSet.getInt("userId"));
                orderShow.setProductId(resultSet.getInt("productId"));
                orderShow.setProductName(resultSet.getString("productName"));
                orderShow.setPrice(resultSet.getFloat("price"));
                orderShow.setQuantity(resultSet.getInt("quantity"));
                orderShow.setDateBuy(resultSet.getDate("dateBuy"));
                list.add(orderShow);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public boolean save(OrderShow orderShow) {
        return false;
    }

    @Override
    public boolean update(OrderShow orderShow) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public OrderShow findById(int id) {

        return null;
    }

    @Override
    public List<OrderShow> findByUserId(int id) {
        Connection conn = null;
        CallableStatement call = null;
        List<OrderShow> list = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            call = conn.prepareCall("{call proc_showHistoryOrderByUser(?)}");
            call.setInt(1,id);
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()){
                OrderShow orderShow = new OrderShow();
                orderShow.setOrderId(resultSet.getInt("orderId"));
                orderShow.setUserId(resultSet.getInt("userId"));
                orderShow.setProductId(resultSet.getInt("productId"));
                orderShow.setProductName(resultSet.getString("productName"));
                orderShow.setPrice(resultSet.getFloat("price"));
                orderShow.setQuantity(resultSet.getInt("quantity"));
                orderShow.setDateBuy(resultSet.getDate("date"));
list.add(orderShow);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<OrderShow> findByOrderId(int id) {
        Connection conn = null;
        CallableStatement call = null;
        List<OrderShow> list = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            call = conn.prepareCall("{call showOrder(?)}");
            call.setInt(1,id);
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()){
                OrderShow orderShow = new OrderShow();
                orderShow.setOrderId(resultSet.getInt("orderId"));
                orderShow.setUserId(resultSet.getInt("userId"));
                orderShow.setProductId(resultSet.getInt("productId"));
                orderShow.setProductName(resultSet.getString("productName"));
                orderShow.setPrice(resultSet.getFloat("price"));
                orderShow.setQuantity(resultSet.getInt("quantity"));
                orderShow.setDateBuy(resultSet.getDate("dateBuy"));
                list.add(orderShow);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}

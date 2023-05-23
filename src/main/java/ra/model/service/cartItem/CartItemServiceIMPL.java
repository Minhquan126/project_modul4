package ra.model.service.cartItem;

import ra.model.entity.CartItem;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartItemServiceIMPL implements ICartItem{
    @Override
    public List<CartItem> findAll() {
        return null;
    }

    @Override
    public boolean save(CartItem cartItem) {
        List<CartItem> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call create_CartItem(?,?,?,?,?,?)}");
            call.setInt(1,cartItem.getUserId());
            call.setInt(2,cartItem.getProductId());
            call.setString(3,cartItem.getImage());
            call.setString(4,cartItem.getProductName());
            call.setFloat(5,cartItem.getPrice());
            call.setInt(6,cartItem.getQuantity());
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(CartItem cartItem) {
return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public CartItem findById(int id) {
        return null;
    }

    @Override
    public boolean delete(int userId, int productId) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_deleteCartItem(?,?)}");
            call.setInt(1,userId);
            call.setInt(2,productId);
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<CartItem> showAll(int id) {
        List<CartItem> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_getAllCartItem(?)}");
            call.setInt(1,id);
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()){
                CartItem cartItem = new CartItem();
                cartItem.setUserId(resultSet.getInt("userId"));
                cartItem.setProductId(resultSet.getInt("productId"));
                cartItem.setImage(resultSet.getString("image"));
                cartItem.setProductName(resultSet.getString("productName"));
                cartItem.setPrice(resultSet.getFloat("price"));
                cartItem.setQuantity(resultSet.getInt("quantity"));
                cartItem.setTotal(resultSet.getFloat("total"));
                list.add(cartItem);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean checkout(int id) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_deleteCart(?)}");
            call.setInt(1,id);
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean productExist(int userId,int productId) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call proc_productExist(?,?)}");
            call.setInt(1,userId);
            call.setInt(2,productId);
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean setQuantity(int userId, int productId, int quantity) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call proc_setQuantity(?,?,?)}");
            call.setInt(1,userId);
            call.setInt(2,productId);
            call.setInt(3,quantity);
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public float total(int userId) {
        Connection conn = null;
        float sum = 0;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_totalCart(?)}");
            call.setInt(1,userId);
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()){
sum = resultSet.getFloat("total");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return sum;
    }
}

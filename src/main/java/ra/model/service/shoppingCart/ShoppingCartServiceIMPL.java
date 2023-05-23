package ra.model.service.shoppingCart;

import ra.model.entity.ShoppingCart;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartServiceIMPL implements IShoppingCartService{
    @Override
    public List<ShoppingCart> findAll() {
        return null;
    }

    @Override
    public boolean save(ShoppingCart shoppingCart) {
        return false;
    }

    @Override
    public boolean update(ShoppingCart shoppingCart) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public ShoppingCart findById(int id) {
        return null;
    }

    @Override
    public List<ShoppingCart> showAll(int id) {
        List<ShoppingCart> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_showCart(?)}");
            call.setInt(1,id);
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()){
                ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCart.setImage(resultSet.getString("image"));
                shoppingCart.setProductName(resultSet.getString("productName"));
                shoppingCart.setPrice(resultSet.getFloat("price"));
                shoppingCart.setQuantity(resultSet.getInt("quantity"));
                shoppingCart.setTotal(resultSet.getFloat("total"));
                list.add(shoppingCart);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}

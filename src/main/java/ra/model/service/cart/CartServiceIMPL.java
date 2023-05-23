package ra.model.service.cart;

import ra.model.entity.Cart;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

public class CartServiceIMPL implements ICartService{
    @Override
    public List<Cart> findAll() {

        return null;
    }

    @Override
    public boolean save(Cart cart) {
        Connection conn = null;
                try {
                    conn = ConnectionDB.getConnection();
                    CallableStatement call = conn.prepareCall("{call PROC_createCart(?)}");
                    call.setInt(1,cart.getUserId());
                    call.executeUpdate();
                } catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
        return true;
    }

    @Override
    public boolean update(Cart cart) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
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
    public Cart findById(int id) {
        return null;
    }

    @Override
    public boolean create(int userId) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_createCart(?)}");
            call.setInt(1,userId);
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

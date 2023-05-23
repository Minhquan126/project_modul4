package ra.model.service.product;

import ra.model.entity.Product;
import ra.model.service.catalog.CatalogServiceIMPL;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceIMPL implements IProductService {
    CatalogServiceIMPL catalogServiceIMPL = new CatalogServiceIMPL();
    @Override
    public List findAll() {

        List<Product> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_getAllProduct()}");
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getInt("productId"));
                product.setProductName(resultSet.getString("productName"));
                product.setPrice(resultSet.getFloat("price"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setDescription(resultSet.getString("description"));
                product.setImage(resultSet.getString("image"));
                product.setCatalogId(resultSet.getInt("catalogId"));
                product.setStatus(resultSet.getBoolean("status"));
                product.setHeart(resultSet.getInt("heart"));
                list.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(Product product) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_createProduct(?,?,?,?,?,?,?)}");
            call.setString(1,product.getProductName());
            call.setFloat(2,product.getPrice());
            call.setInt(3,product.getQuantity());
            call.setString(4,product.getDescription());
            call.setString(5,product.getImage());
            call.setInt(6,product.getCatalogId());
            call.setBoolean(7,product.isStatus());
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public boolean update(Product product) {

        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_updateProduct(?,?,?,?,?,?,?,?)}");
            call.setInt(1,product.getProductId());
            call.setString(2,product.getProductName());
            call.setFloat(3,product.getPrice());
            call.setInt(4,product.getQuantity());
            call.setString(5,product.getDescription());
            call.setString(6,product.getImage());
            call.setInt(7,product.getCatalogId());
            call.setBoolean(8,product.isStatus());
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteById(int id) {

        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_deleteProduct(?)}");
            call.setInt(1,id);
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Product findById(int id) {
        Product product = new Product();
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_findByIdProduct(?)}");
            call.setInt(1, id);
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()) {
                product.setProductId(id);
                product.setProductName(resultSet.getString("productName"));
                product.setPrice(resultSet.getFloat("price"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setDescription(resultSet.getString("description"));
                product.setImage(resultSet.getString("image"));
                product.setCatalogId(resultSet.getInt("catalogId"));
                product.setStatus(resultSet.getBoolean("status"));
                product.setHeart(resultSet.getInt("heart"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> searchByCatalogId(int id) {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.getConnection();
            call = conn.prepareCall("{call searchProductByCatalogId(?)}");
            call.setInt(1,id);
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getInt("productId"));
                product.setProductName(resultSet.getString("productName"));
                product.setPrice(resultSet.getFloat("price"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setDescription(resultSet.getString("description"));
                product.setImage(resultSet.getString("image"));
                product.setCatalogId(id);
                product.setStatus(resultSet.getBoolean("status"));
                product.setHeart(resultSet.getInt("heart"));
                list.add(product);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public boolean setHeart(int id) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.getConnection();
            call = conn.prepareCall("{call setHeartProduct(?)}");
            call.setInt(1,id);
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Product> showList() {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call getProduct()}");
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getInt("productId"));
                product.setProductName(resultSet.getString("productName"));
                product.setPrice(resultSet.getFloat("price"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setDescription(resultSet.getString("description"));
                product.setImage(resultSet.getString("image"));
                product.setCatalogId(resultSet.getInt("catalogId"));
                product.setStatus(resultSet.getBoolean("status"));
                product.setHeart(resultSet.getInt("heart"));
                list.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

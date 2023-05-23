package ra.model.service.catalog;

import ra.model.entity.Catalog;
import ra.model.service.catalog.ICatalogService;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CatalogServiceIMPL implements ICatalogService {
    @Override
    public List<Catalog> findAll() {
            List<Catalog> list = new ArrayList<>();
            Connection conn = null;
            try {
                conn = ConnectionDB.getConnection();
                CallableStatement call = conn.prepareCall("{call PROC_getAllCatalog()}");
                ResultSet resultSet = call.executeQuery();
                while (resultSet.next()){
                    Catalog catalog = new Catalog();
                    catalog.setCatalogId(resultSet.getInt("catalogId"));
                    catalog.setCatalogName(resultSet.getString("catalogName"));
                    catalog.setDescription(resultSet.getString("description"));
                    catalog.setCountry(resultSet.getString("country"));
                    catalog.setImage(resultSet.getString("image"));
                    list.add(catalog);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
    }

    @Override
    public boolean save(Catalog catalog) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_createCatalog(?,?,?,?)}");
            call.setString(1,catalog.getCatalogName());
            call.setString(2,catalog.getDescription());
            call.setString(3,catalog.getCountry());
            call.setString(4,catalog.getImage());
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Catalog catalog) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_updateCatalog(?,?,?,?,?)}");
            call.setInt(1,catalog.getCatalogId());
            call.setString(2,catalog.getCatalogName());
            call.setString(3,catalog.getDescription());
            call.setString(4,catalog.getCountry());
            call.setString(5,catalog.getImage());
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
            CallableStatement call = conn.prepareCall("{call PROC_deleteCatalog(?)}");
            call.setInt(1,id);
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Catalog findById(int id) {
        Catalog catalog = new Catalog();
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_findByIdCatalog(?)}");
            call.setInt(1, id);
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()) {
                catalog.setCatalogId(id);
                catalog.setCatalogName(resultSet.getString("catalogName"));
                catalog.setDescription(resultSet.getString("description"));
                catalog.setCountry(resultSet.getString("country"));
                catalog.setImage(resultSet.getString("image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return catalog;
    }

}

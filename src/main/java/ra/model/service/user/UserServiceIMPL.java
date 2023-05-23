package ra.model.service.user;

import ra.model.entity.User;
import ra.model.service.user.IUserService;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserServiceIMPL implements IUserService {
    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_getAllUser()}");
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUserName(resultSet.getString("userName"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setAddress(resultSet.getString("address"));
                user.setUserStatus(resultSet.getBoolean("status"));
                user.setRole(resultSet.getBoolean("role"));
                list.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(User user) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_register(?,?,?)}");
            call.setString(1, user.getUserName());
            call.setString(2, user.getEmail());
            call.setString(3,user.getPassword());
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(User user) {
Connection conn = null;
CallableStatement call = null;
try {
    conn = ConnectionDB.getConnection();
    call = conn.prepareCall("{call proc_updateUser(?,?,?,?)}");
call.setInt(1,user.getUserId());
call.setDate(2, (new Date(user.getBirthday().getTime())));
call.setString(3, user.getPhoneNumber());
call.setString(4, user.getAddress());
call.executeUpdate();
} catch (Exception e){
    e.printStackTrace();
    return false;
} finally {
    ConnectionDB.closeConnection(conn);
}
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_deleteUser(?)}");
            call.setInt(1,id);
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public User findById(int id) {
        User user = new User();
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_findByIdUser(?)}");
            call.setInt(1, id);
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()) {
                user.setUserId(id);
                user.setUserName(resultSet.getString("userName"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setAddress(resultSet.getString("address"));
                user.setUserStatus(resultSet.getBoolean("status"));
                user.setRole(resultSet.getBoolean("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User login(User user) {
        Connection conn = null;
        User userLogin = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_login(?,?)}");
            call.setString(1, user.getUserName());
            call.setString(2, user.getPassword());
            ResultSet resultSet = call.executeQuery();
            while (resultSet.next()){
                userLogin=new User();
                userLogin.setUserId(resultSet.getInt("userId"));
                userLogin.setUserName(resultSet.getString("userName"));
                userLogin.setEmail(resultSet.getString("email"));
                userLogin.setPassword(resultSet.getString("password"));
                userLogin.setBirthday(resultSet.getDate("birthday"));
                userLogin.setPhoneNumber(resultSet.getString("phoneNumber"));
                userLogin.setAddress(resultSet.getString("address"));
                userLogin.setUserStatus(resultSet.getBoolean("status"));
                userLogin.setRole(resultSet.getBoolean("role"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return userLogin;
    }

    @Override
    public boolean checkExistName(String userName) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_checkUserNameExits(?)}");
            call.setString(1,userName);
            ResultSet resultSet = call.executeQuery();
            if (resultSet.next()){
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean checkExistEmail(String email) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_checkEmailExits(?)}");
            call.setString(1,email);
            ResultSet resultSet = call.executeQuery();
            if (resultSet.next()){
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean blockUser(int id) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            CallableStatement call = conn.prepareCall("{call PROC_blockUser(?)}");
            call.setInt(1,id);
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

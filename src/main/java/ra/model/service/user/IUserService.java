package ra.model.service.user;

import ra.model.entity.User;
import ra.model.service.IGeneric;

public interface IUserService extends IGeneric<User> {
    User login(User user);
    boolean checkExistName(String userName);
    boolean checkExistEmail(String email);
    boolean blockUser(int id);
}

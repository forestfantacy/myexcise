package powermock.service;

import powermock.dao.UserDao;
import powermock.pojo.User;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-04-28 09:10
 */
public class UserService {


    private UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public int getUserCount() {
        return userDao.getCount();
    }

    public void saveUser(User user) {
        this.userDao.insertUser(user);
    }
}

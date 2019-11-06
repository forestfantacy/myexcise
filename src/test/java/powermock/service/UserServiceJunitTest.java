package powermock.service;

import org.junit.Before;
import org.junit.Test;
import powermock.dao.UserDao;
import powermock.pojo.User;

import static junit.framework.Assert.assertEquals;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-04-28 09:14
 */
public class UserServiceJunitTest {

    private UserService userService;

    @Before
    public void setup(){
        userService = new UserService(new UserDao());
    }

    @Test
    public void getUserCountWithJunit() {
        int userCount = userService.getUserCount();
        assertEquals(0, userCount);
    }

    @Test
    public void saveUserWithJunit() {
        userService.saveUser(new User());
    }
}
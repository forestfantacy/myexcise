package powermock.service;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import powermock.dao.UserDao;

import static junit.framework.Assert.assertEquals;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-04-28 09:34
 */
public class UserServicePowerTest {

    @Test
    public void getUserCount() {

        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.doReturn(10).when(userDao).getCount();
        //PowerMockito.when(userDao.getCount()).thenReturn(10);
        UserService userService = new UserService(userDao);
        int userCount = userService.getUserCount();
        assertEquals(userCount, 10);
    }
}
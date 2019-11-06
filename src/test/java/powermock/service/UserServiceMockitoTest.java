package powermock.service;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import powermock.dao.UserDao;

import static junit.framework.Assert.assertEquals;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-04-28 09:24
 */
public class UserServiceMockitoTest {

    @Mock
    private UserDao userdao;

    @Test
    public void getUserCount() {
        MockitoAnnotations.initMocks(this);
        UserService userService = new UserService(userdao);
        Mockito.when(userdao.getCount()).thenReturn(10);

        int userCount = userService.getUserCount();
        assertEquals(userCount, 10);
    }

    //@CacheLoaderTest
    //public void saveUser() {
    //    MockitoAnnotations.initMocks(this);
    //    UserService userService = new UserService(userdao);
    //    Mockito.when(userdao.insertUser(new User())).
    //
    //}
}
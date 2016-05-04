package by.bsuir.busstation.command;

import by.bsuir.busstation.dao.IUserDao;
import by.bsuir.busstation.entity.User;
import by.bsuir.busstation.service.IUserService;
import by.bsuir.busstation.service.impl.IBusServiceImpl;
import by.bsuir.busstation.service.impl.IUserServiceImpl;
import org.hibernate.mapping.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
;
import org.springframework.context.annotation.Role;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;

/**
 * Created by Виктория on 03.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")

public class UserCommandTest {

    @Autowired(required = true)
    public IUserService userService;
    @Test
    public void testCheckAdmin() throws Exception
    {
        UserCommand usercommad= new UserCommand();
        User user = userService.loadUserByUsername("TestAdmin");
        usercommad.checkAdmin(user,"ROLE_ADMIN");
    }
    @Test
    public void testCheckUser() throws Exception
    {
        UserCommand usercommad= new UserCommand();
        User user = userService.loadUserByUsername("TestUser");
        usercommad.checkAdmin(user,"ROLE_USER");
    }
    @Test
    public void testValid() throws Exception {
        UserCommand usercommad= new UserCommand();
        String login,email;
        login="Vika";
        email="vika@mail.ru";
        assertTrue(usercommad.Valid(login,email));
    }

    @Test
    public void testNoValid() throws Exception {
        UserCommand usercommad= new UserCommand();
        String login,email;
        login="Vika";
        email="vikamailru";
        assertFalse(usercommad.Valid(login,email));
    }
    @Test
    public void testCountAdmin() throws Exception {
        UserCommand usercommad= new UserCommand();
        assertTrue(usercommad.CountAdmin("ROLE_ADMIN", userService)>=1);
    }
}
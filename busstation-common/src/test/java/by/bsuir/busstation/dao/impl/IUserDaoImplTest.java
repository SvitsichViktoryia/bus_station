package by.bsuir.busstation.dao.impl;

import by.bsuir.busstation.dao.IUserDao;
import by.bsuir.busstation.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Виктория on 05.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class IUserDaoImplTest {
    @Autowired(required = true)
    IUserDao userDao;
    @Test
    public void testLoadUserByUsername() throws Exception {
        User user=new User();
        user = userDao.loadUserByUsername("TestUser");
        assertTrue(user.getPassword().equals("TestUser"));
    }


    @Test
    public void testRead() throws Exception {
        User user = userDao.read(new Long(1));
        assertTrue(user.getLogin().equals("TestAdmin"));
    }

    @Test
    public void testReadAll() throws Exception {
        List<User> users = userDao.readAll();
        assertNotNull(users.size());
    }


    @Test
    public void testUpdate() throws Exception {
        User user = userDao.read(new Long(1));
        user.setEmail(user.getEmail()+"Up");
        userDao.update(user);
        assertTrue(userDao.read(new Long(1)).getEmail().equals(user.getEmail()));
    }
}
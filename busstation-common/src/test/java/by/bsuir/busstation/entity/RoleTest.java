package by.bsuir.busstation.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Виктория on 04.05.2016.
 */
public class RoleTest {
    Role role = new Role();

    @Test
    public void testGetId() throws Exception {
       role.setId(new Long(13));
       assertTrue(role.getId()==13);
    }

    @Test
    public void testGetName() throws Exception {
        role.setName("ROLE_USER");
        String str="ROLE_USER";
        assertEquals(str,role.getName());
    }

    @Test
    public void testGetUser() throws Exception {
        User user=new User();
        user.setId(new Long(111));
        user.setEmail("email");
        user.setLogin("login");
        role.setUser(user);
        User TestUser=new User();
        assertFalse(role.getUser().equals(TestUser));
    }

    @Test
    public void testSetName() throws Exception {
        role.setName("ROLE_ADMIN");
        String str="ROLE_ADMIN";
        assertEquals(str,role.getName());
    }

    @Test
    public void testSetUser() throws Exception {
        User user=new User();
        user.setId(new Long(11));
        user.setEmail("email");
        user.setLogin("login");
        role.setUser(user);
        assertEquals(role.getUser(),user);
    }

    @Test
    public void testSetId() throws Exception {
        role.setId(new Long(13));
        assertTrue(role.getId()==13);
    }

    @Test
    public void testEquals() throws Exception {
        role.setId(new Long(13));
        role.setName("OFF");
        Role test=new Role();
        test.setId(new Long(13));
        test.setName("OFF");
        assertTrue(role.equals(test));
    }

    @Test
    public void testToString() throws Exception {
        role.setId(new Long(13));
        role.setName("OFF");
        String str=role.toString();
        String strr="Role [id=" + role.getId() + ", name=" + role.getName() + "]";
        assertTrue(str.equals(strr));
    }
}
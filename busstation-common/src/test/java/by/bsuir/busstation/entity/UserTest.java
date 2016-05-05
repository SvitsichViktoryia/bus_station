package by.bsuir.busstation.entity;

import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Виктория on 05.05.2016.
 */
public class UserTest {
User user= new User();
    @Test
    public void testGetId() throws Exception {
        user.setId(new Long(12));
        assertTrue(user.getId()==12);
    }

    @Test
    public void testGetLogin() throws Exception {
        user.setLogin("amma");
        assertEquals(user.getLogin(),"amma");
    }

    @Test
    public void testGetPassword() throws Exception {
        user.setPassword("password");
        assertEquals(user.getPassword(),"password");
    }

    @Test
    public void testGetEmail() throws Exception {
        user.setEmail("email");
        assertEquals(user.getEmail(),"email");
    }

    @Test
    public void testGetRoles() throws Exception {
        Set<Role> role=new Set<Role>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Role> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Role role) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Role> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
        Role add=new Role();
        add.setName("ROLE_ADMIN");
        role.add(add);
        user.setRoles(role);
        assertEquals(role,user.getRoles());
    }

    @Test
    public void testGetTickets() throws Exception {
        Set <Ticket> tickets= new Set<Ticket>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Ticket> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Ticket ticket) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Ticket> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
        Ticket ticket = new Ticket();
        ticket.setId(new Long(12));
        tickets.add(ticket);
        user.setTickets(tickets);
        assertEquals(tickets, user.getTickets());
    }
}
package by.bsuir.busstation.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Виктория on 04.05.2016.
 */
public class TicketTest {
    Ticket ticket= new Ticket();
    @Test
    public void testGetId() throws Exception {
        ticket.setId(new Long(14));
        assertTrue(ticket.getId()==14);
    }

    @Test
    public void testGetUser() throws Exception {
        User user= new User();
        user.setLogin("milo");
        ticket.setUser(user);
        assertEquals(ticket.getUser(),user);
    }

    @Test
    public void testGetRoute() throws Exception {
        Route route=new            Route();
        route.setId(new Long(11));
        ticket.setRoute(route);
        assertEquals(ticket.getRoute(),route);
    }

    @Test
    public void testGetStatus() throws Exception {
        ticket.setStatus(true);
        assertTrue(ticket.getStatus());
    }
}
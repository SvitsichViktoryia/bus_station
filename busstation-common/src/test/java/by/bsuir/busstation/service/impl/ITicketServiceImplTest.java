package by.bsuir.busstation.service.impl;

import by.bsuir.busstation.entity.Route;
import by.bsuir.busstation.entity.Ticket;
import by.bsuir.busstation.entity.User;
import by.bsuir.busstation.service.ITicketService;
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
public class ITicketServiceImplTest {
    @Autowired(required = true)
    ITicketService ticketService;
    @Test
    public void testCreate() throws Exception {
        Ticket ticket = new Ticket();
        Route route=new Route();
        route.setId(new Long(20));
        ticket.setRoute(route);
        User user=new User();
        user.setId(new Long(23));
        ticket.setUser(user);
        Long id =ticketService.create(ticket);
        ticketService.delete(id);
    }

    @Test
    public void testRead() throws Exception {
       Ticket ticket = ticketService.read(new Long(94));
        assertTrue(ticket.getId()==94);
    }

    @Test
    public void testReadAll() throws Exception {
        List<Ticket> tickets = ticketService.readAll();
        assertNotNull(tickets);
    }

    @Test
    public void testReadByUser() throws Exception {
        User user = new User();
        user.setId(new Long(24));
        List <Ticket> tickets = ticketService.readByUser(user.getId());
                assertTrue(tickets.size()==2);
    }
}
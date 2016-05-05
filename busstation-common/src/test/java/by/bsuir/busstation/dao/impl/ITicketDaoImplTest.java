package by.bsuir.busstation.dao.impl;

import by.bsuir.busstation.dao.ITicketDao;
import by.bsuir.busstation.entity.Route;
import by.bsuir.busstation.entity.Ticket;
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
public class ITicketDaoImplTest {
    @Autowired(required = true)
    ITicketDao ticketDao;
    @Test
    public void testCreate() throws Exception {
        Long i;
        Ticket ticket = new Ticket();
        Route route=new Route();
        i=new Long(20);
        route.setId(i);
        ticket.setRoute(route);
        User user=new User();
        i=new Long(23);
        user.setId(i);
        ticket.setUser(user);
        Long id =ticketDao.create(ticket);
        ticketDao.delete(id);
    }

    @Test
    public void testRead() throws Exception {
        Ticket ticket = ticketDao.read(new Long(94));
        assertTrue(ticket.getId()==94);
    }

    @Test
    public void testReadAll() throws Exception {
        List<Ticket> tickets = ticketDao.readAll();
        assertNotNull(tickets);
    }

    @Test
    public void testReadByUser() throws Exception {
        User user = new User();
        user.setId(new Long(24));
        List <Ticket> tickets = ticketDao.readByUser(user.getId());
        assertTrue(tickets.size()==2);
    }
}
package by.bsuir.busstation.dao.impl;

import by.bsuir.busstation.dao.IBusDao;
import by.bsuir.busstation.entity.Bus;
import by.bsuir.busstation.service.IBusService;
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
public class IBusDaoImplTest {
    @Autowired(required = true)
    IBusDao busDao;

    @Test
    public void testRead() throws Exception {
        long id=4;
        Bus bus = busDao.read(id);
        assertTrue(bus.getRegNumber().equals("3419 IH-4"));
    }

    @Test
    public void testReadAll() throws Exception {
        List<Bus> buses = busDao.readAll();
        assertNotNull(buses);
    }

    @Test
    public void testUpdate() throws Exception {
        long id=8;
        Bus bus = busDao.read(id);
        bus.setRegNumber("3542 AT-94");
        bus.setSeats(15);
        busDao.update(bus);
        assertTrue(busDao.read(id).getRegNumber().equals("3542 AT-94"));
    }

}
package by.bsuir.busstation.service.impl;

import by.bsuir.busstation.entity.Bus;
import by.bsuir.busstation.service.IBusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class IBusServiceImplTest {

    @Autowired(required = true)
    IBusService busService;

    @Test
    public void testRead() throws Exception {
        long id=4;
        Bus bus = busService.read(id);
        assertTrue(bus.getRegNumber().equals("3419 IH-4"));
    }
    @Test
    public void testReadAll() throws Exception {
       List <Bus> buses = busService.readAll();
        assertNotNull(buses);
    }
    @Test
    public void testUpdate() throws Exception {
        long id=4;
        Bus bus = busService.read(id);
        bus.setRegNumber("3419 IH-4");
        bus.setSeats(15);
        busService.update(bus);
        assertTrue(busService.read(id).getRegNumber().equals("3419 IH-4"));

    }

}
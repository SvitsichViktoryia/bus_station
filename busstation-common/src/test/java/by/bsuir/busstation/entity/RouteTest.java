package by.bsuir.busstation.entity;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Виктория on 04.05.2016.
 */
public class RouteTest {
    Route route=new Route();
    @Test
    public void testSetId() throws Exception {
        route.setId(new Long(133));
        assertTrue(route.getId()==133);
    }

    @Test
    public void testSetDestinationDate() throws Exception {
        Date date=new Date();
        route.setDestinationDate(date);
        assertEquals(date,route.getDestinationDate());
    }

    @Test
    public void testSetDepartureDate() throws Exception {
        Date date=new Date();
        route.setDepartureDate(date);
        assertEquals(date,route.getDepartureDate());
    }

    @Test
    public void testSetCost() throws Exception {
        route.setCost(new Long(30000));
        assertTrue(30000==route.getCost());
    }

    @Test
    public void testSetDestination() throws Exception {
        Place place=new Place();
        place.setName("Add");
        route.setDestination(place);
        assertEquals(place,route.getDestination());
    }

    @Test
    public void testSetDeparture() throws Exception {
        Place place=new Place();
        place.setName("Add");
        route.setDeparture(place);
        assertEquals(place,route.getDeparture());
    }

    @Test
    public void testSetBus() throws Exception {
        Bus bus=new Bus();
        bus.setRegNumber("EEW-00");
        route.setBus(bus);
        assertEquals(bus,route.getBus());
    }

    @Test
    public void testSetDriver() throws Exception {
        Driver driver=new Driver();
        driver.setName("Oleg");
        route.setDriver(driver);
        assertEquals(driver,route.getDriver());
    }
}
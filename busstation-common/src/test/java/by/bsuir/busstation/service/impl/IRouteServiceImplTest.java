package by.bsuir.busstation.service.impl;

import by.bsuir.busstation.entity.Bus;
import by.bsuir.busstation.entity.Driver;
import by.bsuir.busstation.entity.Place;
import by.bsuir.busstation.entity.Route;
import by.bsuir.busstation.service.IBusService;
import by.bsuir.busstation.service.IDriverService;
import by.bsuir.busstation.service.IPlaceService;
import by.bsuir.busstation.service.IRouteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Виктория on 05.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class IRouteServiceImplTest {
    @Autowired(required = true)
    IRouteService routeService;
    @Autowired(required = true)
    IPlaceService placeService;
    @Autowired(required = true)
    IDriverService driverService;
    @Autowired(required = true)
    IBusService busService;
    @Test
    public void testCreate() throws Exception {
        Long i =new Long(4);
        Bus bus = busService.read(i);
        Driver driver = driverService.read(i);
        i =new Long(2);
        Place destination = placeService.read(i);
        i =new Long(5);
        Place departure = placeService.read(i);
        Route route = new Route();
        route.setBus(bus);
        route.setCost(new Long(30000));
        route.setDeparture(departure);
        Date departureDate=new Date();
        route.setDepartureDate(departureDate);
        route.setDestination(destination);
        Date destinationDate=new Date();
        route.setDestinationDate(destinationDate);
        route.setDriver(driver);
        Long id = routeService.create(route);
        routeService.delete(id);
    }

    @Test
    public void testRead() throws Exception {
        Route route=routeService.read(new Long(33));
        assertNotNull(route);
    }

    @Test
    public void testReadAll() throws Exception {
        List<Route> routes = routeService.readAll();
        assertNotNull(routes.size());
    }

    @Test
    public void testUpdate() throws Exception {
        Long id = Long.valueOf(new Long(33));
        Route route = routeService.read(id);
        Long i =new Long(4);
        Bus bus = busService.read(i);
        Driver driver = driverService.read(i);
        i =new Long(2);
        Place destination = placeService.read(i);
        i =new Long(5);
        Place departure = placeService.read(i);
        route.setBus(bus);
        route.setCost(new Long(30000));
        route.setDeparture(departure);
        Date departureDate=new Date();
        route.setDepartureDate(departureDate);
        route.setDestination(destination);
        Date destinationDate=new Date();
        route.setDestinationDate(destinationDate);
        route.setDriver(driver);
        routeService.update(route);
    }
}
package by.bsuir.busstation.dao.impl;

import by.bsuir.busstation.dao.IBusDao;
import by.bsuir.busstation.dao.IDriverDao;
import by.bsuir.busstation.dao.IPlaceDao;
import by.bsuir.busstation.dao.IRouteDao;
import by.bsuir.busstation.entity.Bus;
import by.bsuir.busstation.entity.Driver;
import by.bsuir.busstation.entity.Place;
import by.bsuir.busstation.entity.Route;
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
public class IRouteDaoImplTest {
    @Autowired(required = true)
    IRouteDao routeDao;
    @Autowired(required = true)
    IPlaceDao placeDao;
    @Autowired(required = true)
    IDriverDao driverDao;
    @Autowired(required = true)
    IBusDao busDao;
    @Test
    public void testCreate() throws Exception {
        Bus bus = busDao.read(new Long(4));
        Driver driver = driverDao.read(new Long(4));
        Place destination = placeDao.read(new Long(2));
        Place departure = placeDao.read(new Long(5));
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
        Long id = routeDao.create(route);
        routeDao.delete(id);
    }

    @Test
    public void testRead() throws Exception {
        Route route=routeDao.read(new Long(33));
        assertNotNull(route);
    }

    @Test
    public void testReadAll() throws Exception {
        List <Route> routes = routeDao.readAll();
        assertNotNull(routes.size());
    }

    @Test
    public void testUpdate() throws Exception {
        Long id = Long.valueOf(new Long(33));
        Route route = routeDao.read(id);
        Bus bus = busDao.read(new Long(4));
        Driver driver = driverDao.read(new Long(4));
        Place destination = placeDao.read(new Long(2));
        Place departure = placeDao.read(new Long(5));
        route.setBus(bus);
        route.setCost(new Long(30000));
        route.setDeparture(departure);
        Date departureDate=new Date();
        route.setDepartureDate(departureDate);
        route.setDestination(destination);
        Date destinationDate=new Date();
        route.setDestinationDate(destinationDate);
        route.setDriver(driver);
        routeDao.update(route);
    }

}
package by.bsuir.busstation.dao.impl;

import by.bsuir.busstation.dao.IPlaceDao;
import by.bsuir.busstation.entity.Place;
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
public class IPlaceDaoImplTest {
    @Autowired(required = true)
    IPlaceDao placeDao;
    Long id;
    String name="TestPlace";
    @Test
    public void testCreate() throws Exception {
        Place place=new Place();
        place.setName(name);
        id=placeDao.create(place);
        placeDao.delete(id);
    }

    @Test
    public void testRead() throws Exception {
        long id=2;
        Place place = placeDao.read(id);
        assertTrue(place.getName().equals("Витебск"));
    }

    @Test
    public void testReadAll() throws Exception {
        List<Place> places = placeDao.readAll();
        assertNotNull(places);
    }

    @Test
    public void testUpdate() throws Exception {
        Place place = placeDao.read(new Long(5));
        place.setName("Минск");
        placeDao.update(place);
        assertTrue(placeDao.read(new Long(5)).getName().equals("Минск"));
    }
}
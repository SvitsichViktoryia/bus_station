package by.bsuir.busstation.service.impl;

import by.bsuir.busstation.entity.Place;
import by.bsuir.busstation.service.IPlaceService;
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
public class IPlaceServiceImplTest {
    @Autowired(required = true)
    IPlaceService placeService;
    Long id;
    String name="TestPlace";
    @Test
    public void testCreate() throws Exception {
        Place place=new Place();
        place.setName(name);
        id=placeService.create(place);
        placeService.delete(id);
    }

    @Test
    public void testRead() throws Exception {
        long id=2;
        Place place = placeService.read(id);
        assertTrue(place.getName().equals("Витебск"));
    }

    @Test
    public void testReadAll() throws Exception {
        List<Place> places = placeService.readAll();
        assertNotNull(places);
    }

    @Test
    public void testUpdate() throws Exception {
        Place place = placeService.read(new Long(5));
        place.setName("Минск");
        placeService.update(place);
        assertTrue(placeService.read(new Long(5)).getName().equals("Минск"));
    }
}
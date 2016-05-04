package by.bsuir.busstation.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Виктория on 04.05.2016.
 */
public class PlaceTest {
    Place place= new Place();
    @Test
    public void testGetId() throws Exception {

        place.setId(new Long(8));
        long id=place.getId()*10, i=80;
        assertTrue(id==i);
    }

    @Test
    public void testSetId() throws Exception {
        long id=1;
        place.setId(id);
        assertTrue( place.getId()==id);
    }

    @Test
    public void testGetName() throws Exception {
        place.setName("Indi");
        String name=place.getName();
        assertEquals(name,place.getName());
    }

    @Test
    public void testSetName() throws Exception {
        place.setName("Indi");
        String name=place.getName();
        assertEquals(name,place.getName());
    }


    @Test
    public void testEquals() throws Exception {
        place.setId(new Long(11));
        place.setName("Anna");
        Place test = new Place();
        test.setId(new Long(11));
        test.setName("Anna");
        assertTrue(place.equals(test));

    }

    @Test
    public void testToString() throws Exception {
        Place place= new Place();
        long id=100;
        place.setId(id);
        String name="Name";
        place.setName(name);
        String str=place.toString();
        String strr="Places [id=" + id + ", name=" + name + "]";
        assertTrue(str.equals(strr));
    }
}
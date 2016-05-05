package by.bsuir.busstation.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Виктория on 04.05.2016.
 */
public class DriverTest {
    Driver driver=new Driver();

    @Test
    public void testGetId() throws Exception {
        long id=13;
        driver.setId(new Long(13));
        assertTrue(id==driver.getId());
    }

    @Test
    public void testSetId() throws Exception {
        Driver driverr=new Driver();
        driver.setId(new Long(13));
        driverr.setId(new Long(133));
        assertFalse( driverr.getId()==driver.getId());
    }

    @Test
    public void testGetName() throws Exception {
        driver.setName("TestTest");
        assertTrue(driver.getName().equals("TestTest"));
    }

    @Test
    public void testSetName() throws Exception {
        driver.setName("Vera");
        assertTrue(driver.getName().equals("Vera"));
    }

    @Test
    public void testGetExperience() throws Exception {
        driver.setExperience(14);
        assertTrue(driver.getExperience()==14);
    }

    @Test
    public void testSetExperience() throws Exception {
        driver.setExperience(144);
        driver.setExperience(driver.getExperience());
        assertTrue(driver.getExperience()==144);
    }

    @Test
    public void testEquals() throws Exception {
        Driver driver1= new Driver();
        driver1.setExperience(12);
        driver1.setName("Dima");
        Driver driver2= new Driver();
        driver2.setExperience(12);
        driver2.setName("Dima");
        assertTrue(driver1.equals(driver2));
    }

    @Test
    public void testToString() throws Exception {
        Driver driver= new Driver();
        long id=100;
        driver.setId(id);
        String name="Name";
        driver.setName(name);
        int experience=13;
        driver.setExperience(experience);
        String str=driver.toString();
        String strr="Driver [id=" + id + ", name=" + name + ", experience=" + experience + "]";
        assertTrue(str.equals(strr));
    }
}
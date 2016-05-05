package by.bsuir.busstation.dao.impl;

import by.bsuir.busstation.dao.IDriverDao;
import by.bsuir.busstation.entity.Driver;
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
public class IDriverDaoImplTest {
    @Autowired(required = true)
    IDriverDao driverDao;
    String name="TestDriverDao";
    Integer experience=10;
    Long id;
    @Test
    public void testCreate() throws Exception {
        Driver driver = new Driver();
        driver.setName("TestDriverDao");
        driver.setExperience(experience);
        id = driverDao.create(driver);
        driverDao.delete(id);
    }

    @Test
    public void testRead() throws Exception {
        long idr=4;
        Driver driver = driverDao.read(idr);
        assertTrue(driver.getName().equals("Орлов Алексей Андреевич"));
    }

    @Test
    public void testReadAll() throws Exception {
        List<Driver> drivers = driverDao.readAll();
        assertNotNull(drivers);
    }

    @Test
    public void testUpdate() throws Exception {
        Driver driver = driverDao.read(new Long(4));
        driver.setExperience(16);
        driverDao.update(driver);
        assertTrue(driverDao.read(new Long(4)).getExperience()==16);
    }
}
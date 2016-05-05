package by.bsuir.busstation.service.impl;

import by.bsuir.busstation.entity.Bus;
import by.bsuir.busstation.entity.Driver;
import by.bsuir.busstation.service.IDriverService;
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
public class IDriverServiceImplTest {
    @Autowired(required = true)
    IDriverService driverService;
    String name="TestDriver";
    Integer experience=13;
    Long id;
    @Test
    public void testCreateDelete() throws Exception {
        Driver driver = new Driver();
        driver.setName(name);
        driver.setExperience(experience);
        id = driverService.create(driver);
        driverService.delete(id);
    }

    @Test
    public void testRead() throws Exception {
        long id=4;
        Driver driver = driverService.read(id);
        assertTrue(driver.getName().equals("Орлов Алексей Андреевич"));
    }

    @Test
    public void testReadAll() throws Exception {
        List<Driver> drivers = driverService.readAll();
        assertNotNull(drivers);
    }

    @Test
    public void testUpdate() throws Exception {
        long id=4;
        Driver driver = driverService.read(id);
        driver.setExperience(16);
        driverService.update(driver);
        assertTrue(driverService.read(id).getExperience()==16);

    }


}
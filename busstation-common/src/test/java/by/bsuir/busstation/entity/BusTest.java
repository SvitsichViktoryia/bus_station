package by.bsuir.busstation.entity;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Виктория on 03.05.2016.
 */
public class BusTest {

    @Test
    public void testGetId() throws Exception {
    Bus bus=new Bus();
        long id=11,Id;
        bus.setId(id);
        Id=bus.getId();
        assertEquals(11,Id);
    }

    @Test
    public void testSetId() throws Exception {
        Bus bus=new Bus();
        long id=15,Id;
        bus.setId(id);
        Id=bus.getId();
        assertEquals(15,Id);
    }

    @Test
    public void testGetRegNumber() throws Exception {
        Bus bus=new Bus();
        String str;
        str=bus.getRegNumber();
        assertEquals(null,str);
    }

    @Test
    public void testSetRegNumber() throws Exception {
        Bus bus=new Bus();
        bus.setRegNumber("QW3209-AQ2");
        assertEquals(bus.getRegNumber(),"QW3209-AQ2");
    }

    @Test
    public void testGetInspectionDate() throws Exception {
        Bus bus=new Bus();
        assertEquals(null,bus.getInspectionDate());
    }

    @Test
    public void testGetSeats() throws Exception {
    Bus bus = new Bus();
        bus.setSeats(12);
        assertTrue(bus.getSeats()==12);
    }

    @Test
    public void testSetSeats() throws Exception {
        Bus bus = new Bus();
        bus.setSeats(12);
        bus.setSeats(bus.getSeats()-5);
        assertFalse(bus.getSeats()==12);
    }

    @Test
    public void testEquals() throws Exception {
        Bus bus1= new Bus();
        bus1.setSeats(12);
        bus1.setRegNumber("QW2-90");
        Bus bus2= new Bus();
        bus2.setSeats(12);
        bus2.setRegNumber("QW2-90");
        assertTrue(bus1.equals(bus2));
    }

    @Test
    public void testToString() throws Exception {
        Bus bus= new Bus();
        long  id=12;
        int seats=12;
        String regNumber="QW2-90";
        bus.setId(id);
        bus.setSeats(seats);
        bus.setRegNumber(regNumber);
        Date inspectionDate= new Date();
        bus.setInspectionDate(inspectionDate);
        String str=bus.toString();
        String strr="Bus [id=" + id + ", regNumber=" + regNumber + ", inspectionDate=" + inspectionDate + ", seats=" + seats + "]";
        assertTrue(str.equals(strr));
    }
}
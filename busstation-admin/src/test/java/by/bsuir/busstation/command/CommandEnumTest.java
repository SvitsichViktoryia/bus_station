package by.bsuir.busstation.command;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Виктория on 03.05.2016.
 */
public class CommandEnumTest {
    @Test
    public void testCommandEnum() throws Exception {
        CommandEnum commandEnum=null;

        commandEnum = CommandEnum.valueOf("AUTH");
        commandEnum = CommandEnum.valueOf("MAIN");
        commandEnum = CommandEnum.valueOf("PLACES");
        commandEnum = CommandEnum.valueOf("TICKETS");
        commandEnum = CommandEnum.valueOf("DRIVERS");
        commandEnum = CommandEnum.valueOf("BUSES");
        commandEnum = CommandEnum.valueOf("LOGOUT");
        commandEnum = CommandEnum.valueOf("UNKNOWN");
        commandEnum = CommandEnum.valueOf("USERS");

    }


}
package SmokeTestHerOkuApp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({C01_CreateBooking.class,
        C02_GetBooking.class,
        C03_UpdateBooking.class,
        C04_PartiallyUpdateBooking.class,
        C05_DeleteBooking.class,
        C06_GetDeleteBooking.class})
public class Runner {


}

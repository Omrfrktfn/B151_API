package SmokeTestHerOkuApp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({C01_CreateBooking.class,
        C02_GetBooking.class,
        C03_UpdateBooking.class})
public class Runner {


}

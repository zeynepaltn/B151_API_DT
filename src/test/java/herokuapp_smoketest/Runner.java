package herokuapp_smoketest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                C01_CreateBooking.class,  //Class'lar Runner'a yerlestirdigimiz sira ile calisir
                C02_GetBookingById.class,
                C03_UpdateBooking.class,
                C04_PartiallyUpdateBooking.class,
                C05_DeleteBooking.class,
                C06_GetDeletedBooking.class

        }
)

public class Runner {



}

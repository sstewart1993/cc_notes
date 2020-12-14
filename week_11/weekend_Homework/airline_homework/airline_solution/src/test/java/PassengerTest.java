import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PassengerTest {

    Passenger passenger;

    @Before
    public void setup(){
        passenger = new Passenger("Colin", 2);
    }

    @Test
    public void hasName(){
        assertEquals("Colin", passenger.getName());
    }

    @Test
    public void hasBags(){
        assertEquals(2, passenger.getBags());
    }

    @Test
    public void canSetFlight(){
        Date date = new GregorianCalendar(2018, Calendar.NOVEMBER, 18).getTime();
        Plane plane = new Plane(PlaneType.BOEING747);
        Flight flight = new Flight(plane, "FN999", AirportName.EDI, AirportName.GLA, date);
        passenger.setFlight(flight);
        assertEquals(flight, passenger.getFlight());
    }

    @Test
    public void startsWithNoSeatNumber(){
        assertNull(passenger.getSeatNo());
    }

    @Test
    public void canSetSeatNo(){
        Integer seatNo = 23;
        passenger.setSeatNo(seatNo);
        assertEquals(seatNo, passenger.getSeatNo());
    }
}

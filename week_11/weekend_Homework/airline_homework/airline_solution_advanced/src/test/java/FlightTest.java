import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FlightTest {

    Flight flight;
    Plane plane;
    Passenger passenger;
    Date date;

    @Before
    public void setup(){
        plane = new Plane(PlaneType.WEEPLANE);
        passenger = new Passenger("Colin", 2);
        date = new GregorianCalendar(2018, Calendar.NOVEMBER, 18).getTime();
        flight = new Flight(plane, "CB123", AirportName.GLA, AirportName.EDI, date);
    }

    @Test
    public void hasPlane(){
        assertEquals(plane, flight.getPlane());
    }

    @Test
    public void hasFlightNo(){
        assertEquals("CB123", flight.getFlightNo());
    }

    @Test
    public void hasDestination(){
        assertEquals(AirportName.GLA, flight.getDestination());
    }

    @Test
    public void hasDepartureAirport(){
        assertEquals(AirportName.EDI, flight.getDepartureAirport());
    }

    @Test
    public void hasDepartureTime(){
        assertEquals(date, flight.getDepartureTime());
    }

    @Test
    public void startsEmpty(){
        assertEquals(0, flight.passengerCount());
    }

    @Test
    public void canCheckRemainingSeats(){
        assertEquals(1, flight.remainingSeats());
    }

    @Test
    public void canBookPassenger(){
        flight.addPassenger(passenger);
        assertEquals(1, flight.passengerCount());
    }

    @Test
    public void canGenerateSeatNumbers(){
        assertEquals(1, flight.getSeatNumbers().size());
    }

    @Test
    public void canAllocateSeatNumbers(){
        flight.allocateSeatNumber(passenger);
        assertNotNull(passenger.getSeatNo());
        assertEquals(0, flight.getSeatNumbers().size());
    }

}

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class FlightManagerTest {
    FlightManager flightManager;
    Plane plane;
    Passenger passenger;
    Flight flight;

    @Before
    public void setup(){
        plane = new Plane(PlaneType.WEEPLANE);
        passenger = new Passenger("Colin", 2);
        Date date = new GregorianCalendar(2018, Calendar.NOVEMBER, 18).getTime();
        flight = new Flight(plane, "CB123", AirportName.GLA, AirportName.EDI, date);
        flightManager = new FlightManager(flight);
    }

    @Test
    public void bookingPassengerAddsFlightToPassenger(){
        flightManager.bookPassenger(passenger);
        assertEquals(flight, passenger.getFlight());
    }

    @Test
    public void cannotBookPassengersBeyondCapacity(){
        flightManager.bookPassenger(passenger);
        flightManager.bookPassenger(passenger);
        assertEquals(1, flight.passengerCount());
    }

    @Test
    public void canCalculateBaggageWeight(){
        flightManager.bookPassenger(passenger);
        assertEquals(20, flightManager.baggageWeight());
    }

    @Test
    public void canCalculateRemainingBaggageWeight(){
        flightManager.bookPassenger(passenger);
        assertEquals(5, flightManager.remainingBaggageWeight());
    }

    @Test
    public void canCalculateBaggagePerPassenger(){
        assertEquals(25, flightManager.baggagePerPassenger());
    }

    @Test
    public void canReduceRemainingSeats(){
        flightManager.bookPassenger(passenger);
        assertEquals(0, flight.remainingSeats());
    }


}

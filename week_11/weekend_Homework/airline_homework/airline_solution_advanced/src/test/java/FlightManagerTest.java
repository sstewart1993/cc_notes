import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void canSortPassengers(){
        Plane plane = new Plane(PlaneType.BOEING747);
        Passenger passenger1 = new Passenger("Colin", 2);
        Passenger passenger2 = new Passenger("Steve", 1);
        Date date = new GregorianCalendar(2018, Calendar.NOVEMBER, 18).getTime();
        Flight flight = new Flight(plane, "CB123", AirportName.GLA, AirportName.EDI, date);
        flightManager = new FlightManager(flight);
        flightManager.bookPassenger(passenger1);
        flightManager.bookPassenger(passenger2);
        passenger1.setSeatNo(34);
        passenger2.setSeatNo(24);
        ArrayList<Passenger> sortedPassengers = flightManager.sortPassengers();
        Passenger firstPassengerInArray = sortedPassengers.get(0);
        assertEquals(passenger2, firstPassengerInArray);
    }

    @Test
    public void canFindPassenger(){
    Plane plane = new Plane(PlaneType.BOEING747);
    Passenger passenger1 = new Passenger("Colin", 2);
    Passenger passenger2 = new Passenger("Steve", 1);
    Date date = new GregorianCalendar(2018, Calendar.NOVEMBER, 18).getTime();
    Flight flight = new Flight(plane, "CB123", AirportName.GLA, AirportName.EDI, date);
    flightManager = new FlightManager(flight);
    flightManager.bookPassenger(passenger1);
    flightManager.bookPassenger(passenger2);
    passenger1.setSeatNo(34);
    passenger2.setSeatNo(24);
    flightManager.sortPassengers();
    Passenger found = flightManager.getPassenger(24);
    assertEquals(passenger2, found);
}

    @Test
    public void passengerNotFoundReturnsNull(){
        Plane plane = new Plane(PlaneType.BOEING747);
        Passenger passenger1 = new Passenger("Colin", 2);
        Passenger passenger2 = new Passenger("Steve", 1);
        Date date = new GregorianCalendar(2018, Calendar.NOVEMBER, 18).getTime();
        Flight flight = new Flight(plane, "CB123", AirportName.GLA, AirportName.EDI, date);
        flightManager = new FlightManager(flight);
        flightManager.bookPassenger(passenger1);
        flightManager.bookPassenger(passenger2);
        passenger1.setSeatNo(34);
        passenger2.setSeatNo(24);
        flightManager.sortPassengers();
        Passenger found = flightManager.getPassenger(14);
        assertEquals(null, found);
    }

}

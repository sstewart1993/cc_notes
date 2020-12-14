import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Flight {

    private Plane plane;
    private String flightNo;
    private AirportName destination;
    private AirportName departureAirport;
    private Date departureTime;
    private ArrayList<Passenger> passengers;
    private ArrayList<Integer> seatNumbers;

    public Flight(Plane plane, String flightNo, AirportName destination, AirportName departureAirport, Date departureTime) {
        this.plane = plane;
        this.flightNo = flightNo;
        this.destination = destination;
        this.departureAirport = departureAirport;
        this.departureTime = departureTime;
        this.passengers = new ArrayList<>();
        this.seatNumbers = generateSeatNumbers();
    }

    private ArrayList<Integer> generateSeatNumbers() {
        ArrayList<Integer> seatNumbers = new ArrayList<>();
        for(int i = 1; i <= this.plane.getCapacity(); i ++){
            seatNumbers.add(i);
        }
        return seatNumbers;
    }


    public Plane getPlane() {
        return plane;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public AirportName getDestination() {
        return destination;
    }

    public AirportName getDepartureAirport() {
        return departureAirport;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public int passengerCount() {
        return this.passengers.size();
    }

    public int remainingSeats() {
        return this.plane.getCapacity() - passengerCount();
    }

    public void addPassenger(Passenger passenger) {
            this.passengers.add(passenger);
            allocateSeatNumber(passenger);
    }

    public void allocateSeatNumber(Passenger passenger){
        Random random = new Random();
        int randomIndex = random.nextInt(this.seatNumbers.size());
        Integer seatNumber = this.seatNumbers.remove(randomIndex);
        passenger.setSeatNo(seatNumber);
    }

    public ArrayList<Integer> getSeatNumbers() {
        return seatNumbers;
    }
}

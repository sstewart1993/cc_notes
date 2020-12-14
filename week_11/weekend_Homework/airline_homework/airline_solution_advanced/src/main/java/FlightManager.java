import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class FlightManager {

    private Flight flight;
    private int reservedBaggageWeight;

    public FlightManager(Flight flight) {
        this.flight = flight;
        this.reservedBaggageWeight = flight.getPlane().getWeight()/2;
    }

    public void bookPassenger(Passenger passenger){
        if(this.flight.remainingSeats() > 0) {
            this.flight.addPassenger(passenger);
            passenger.setFlight(this.flight);
        }
    }

    public int baggageWeight() {
        int totalBaggageWeight = 0;
        for(Passenger passenger : this.flight.getPassengers()){
            int baggageWeight = passenger.getBags() * 10;
            totalBaggageWeight += baggageWeight;
        }
        return totalBaggageWeight;
    }

    public int remainingBaggageWeight(){
        return reservedBaggageWeight - baggageWeight();
    }


    public int baggagePerPassenger() {
        int passengers = this.flight.getPlane().getCapacity();
        return reservedBaggageWeight/passengers;
    }

    public ArrayList<Passenger> sortPassengers() {
        Flight flight = this.flight;
        ArrayList<Passenger> passengers = flight.getPassengers();
        ArrayList<Passenger> sortedPassengers = new ArrayList<Passenger>(passengers.size());
        for (int i = 0; i < passengers.size(); i++) {
            for (int nextIndex = 1; nextIndex < passengers.size(); nextIndex++) {
                Passenger firstPassenger = passengers.get(i);
                Passenger secondPassenger = passengers.get(nextIndex);
                if (!sortedPassengers.contains(firstPassenger)) {
                    sortedPassengers.add(firstPassenger);
                }
                if (!sortedPassengers.contains(secondPassenger)) {
                    sortedPassengers.add(secondPassenger);
                }
                if (firstPassenger.getSeatNo() > secondPassenger.getSeatNo()) {
                    sortedPassengers.set(i, secondPassenger);
                    sortedPassengers.set(nextIndex, firstPassenger);
                }
            }
        }
        return sortedPassengers;
    }

    public Passenger getPassenger(int seat) {
        ArrayList<Passenger> sortedPassengers = this.sortPassengers();
        Passenger passenger = this.binarySearch(sortedPassengers, seat);
        return passenger;
    }

    private Passenger binarySearch(ArrayList<Passenger> passengers, int seat){
        if (passengers.size() == 0){
            return null;
        }

        int midIndex = passengers.size() / 2;
        Passenger passenger = passengers.get(midIndex);

        if (passenger.getSeatNo() == seat){
            return passenger;
        }

        ArrayList<Passenger> shortList;
        if (seat > passenger.getSeatNo()){
            shortList = new ArrayList<Passenger>(passengers.subList(midIndex + 1, passengers.size()));
        } else {
            shortList = new ArrayList<Passenger>(passengers.subList(0, midIndex));
        }
        return binarySearch(shortList, seat);
    }


}

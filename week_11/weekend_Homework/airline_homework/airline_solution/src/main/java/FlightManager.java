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
}

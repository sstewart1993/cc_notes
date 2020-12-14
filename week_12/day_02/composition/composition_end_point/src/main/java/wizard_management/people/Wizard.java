package wizard_management.people;


import wizard_management.behaviours.IFly;

public class Wizard {
    String name;
    IFly ride;

    public Wizard(String name, IFly ride){
        this.name = name;
        this.ride = ride;
    }

    public String getName(){
        return this.name;
    }

    public IFly getRide(){
        return this.ride;
    }

    public void changeRide(IFly newRide){
        this.ride = newRide;
    }

    public String fly(){
        return this.ride.fly();
    }

}

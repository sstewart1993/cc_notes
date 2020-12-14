package wizard_management.cleaning;


import wizard_management.behaviours.IFly;

public class Broomstick extends CleaningImplement implements IFly {

    int speed;

    public Broomstick(String brand, int speed){
        super(brand);
        this.speed = speed;
    }

    public int getSpeed(){
        return this.speed;
    }

    @Override
    public String fly(){
        return "mounting broom, running, skipping, flying!";
    }
}

package wizard_management.carpets;


import wizard_management.behaviours.IFly;

public class MagicCarpet extends Carpet implements IFly {

    public MagicCarpet(String colour){
        super(colour);
    }

    @Override
    public String fly(){
        return "Hovering up, straightening out, flying off!";
    }

}

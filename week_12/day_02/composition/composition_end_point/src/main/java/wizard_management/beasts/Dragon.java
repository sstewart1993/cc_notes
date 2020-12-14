package wizard_management.beasts;


import wizard_management.behaviours.IFly;

public class Dragon extends MythicalBeast implements IFly {

    public Dragon(String name){
        super(name);
    }

    @Override
    public String fly(){
        return "Standing up tall, beating wings, lift off!";
    }

}

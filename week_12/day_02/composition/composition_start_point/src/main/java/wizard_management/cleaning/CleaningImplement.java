package wizard_management.cleaning;


public abstract class CleaningImplement{

    String brand;

    public CleaningImplement(String brand) {
        this.brand = brand;
    }

    public String getBrand(){
        return this.brand;
    }
}

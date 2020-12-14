import static org.junit.Assert.*;
import org.junit.*;
import wizard_management.beasts.Dragon;
import wizard_management.beasts.Ogre;
import wizard_management.carpets.MagicCarpet;
import wizard_management.cleaning.Broomstick;
import wizard_management.people.Wizard;


public class WizardTest {

    Wizard wizard;
    Broomstick broomstick;

    @Before
    public void before(){
        broomstick = new Broomstick("Nimbus", 10);
        wizard = new Wizard("Toby", broomstick);
    }

    @Test
    public void hasName(){
        assertEquals("Toby", wizard.getName());
    }

    @Test
    public void hasBroomstick(){
        Broomstick broomstick = (Broomstick) wizard.getRide();
        assertEquals("Nimbus", broomstick.getBrand());
    }

    @Test
    public void can_fly_broomstick(){
        assertEquals("mounting broom, running, skipping, flying!", wizard.fly());
    }

    @Test
    public void can_fly_dragon(){
        Dragon dragon = new Dragon("Tilly");
        wizard = new Wizard("Toby", dragon);
        assertEquals("Standing up tall, beating wings, lift off!", wizard.fly());
    }

    @Test
    public void can_fly_magic_carpet(){
        MagicCarpet magicCarpet = new MagicCarpet("White");
        wizard = new Wizard("Alex", magicCarpet);
        assertEquals("Hovering up, straightening out, flying off!", wizard.fly());
    }

    @Test
    public void wizard_can_change_ride(){
        Dragon dragon = new Dragon("Dave");
        wizard.changeRide(dragon);
        assertEquals("Standing up tall, beating wings, lift off!", wizard.fly());
    }


}

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlaneTest {

    Plane plane;

    @Before
    public void setup(){
        plane = new Plane(PlaneType.BOEING747);
    }

    @Test
    public void hasCapacity(){
        assertEquals(200, plane.getCapacity());
    }

    @Test
    public void hasWeight(){
        assertEquals(1000, plane.getWeight());
    }
}

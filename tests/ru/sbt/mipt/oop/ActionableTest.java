
package ru.sbt.mipt.oop;

        import org.junit.Test;

        import java.util.Arrays;
        import java.util.HashSet;
        import java.util.List;
        import java.util.Set;

        import static org.junit.Assert.assertEquals;

public class ActionableTest {



    @Test
    public void testComponentComposite() {
        List<Light> lights1 = Arrays.asList(new Light("1", false), new Light("2", true));
        List<Door> doors1 = Arrays.asList(new Door("1", false));
        Room kitchen = new Room(lights1, doors1,"kitchen");

        List<Light> lights2 = Arrays.asList(new Light("3", true));
        List<Door> doors2 = Arrays.asList(new Door("2", false));
        Room bathroom = new Room(lights2, doors2,"bathroom");

        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom));

        Set<Object> unvisitedObjects = new HashSet<>();
        unvisitedObjects.add(smartHome);
        unvisitedObjects.add(kitchen);
        unvisitedObjects.add(bathroom);
        unvisitedObjects.addAll(lights1);
        unvisitedObjects.addAll(lights2);
        unvisitedObjects.addAll(doors1);
        unvisitedObjects.addAll(doors2);

        smartHome.executeAction( object -> {
            unvisitedObjects.remove(object);
        });
        assertEquals(0, unvisitedObjects.size());
    }
}


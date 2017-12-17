package ru.sbt.mipt.oop;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActionableTest {






    @Test
    public void actionableComposite(){
        List<Light> lights_1 = Arrays.asList(new Light("1", false), new Light("2", true));
        List<Door> doors_1 = Arrays.asList(new Door("1", false));
        Room kitchen = new Room(lights_1, doors_1, "kitchen");

        List<Light> lights_2 = Arrays.asList(new Light("3", true));
        List<Door> doors_2 = Arrays.asList(new Door("2", false));
        Room bathroom = new Room(lights_2, doors_2, "bathroom");

        SmartHome home = new SmartHome(Arrays.asList(kitchen, bathroom));

        Set<Object> unvisited = new HashSet<>();
        unvisited.add(home);
        unvisited.add(kitchen);
        unvisited.add(bathroom);
        unvisited.addAll(lights_1);
        unvisited.addAll(lights_2);
        unvisited.addAll(doors_1);
        unvisited.addAll(doors_2);

        home.executeAction(obj ->{
            unvisited.remove(obj);
        });
        System.out.println(unvisited);
        assertTrue(unvisited.isEmpty());
    }

    @Test
    public void testForRooms() {
        SmartHome smartHome = HomeBuilder.create();
        Collection<Room> rooms = new HashSet<>();


        Iterator roomsIterator = smartHome.getRoomsIterator();
        while (roomsIterator.hasNext()) {
            Room room = (Room) roomsIterator.next();
            rooms.add(room);
        }

        smartHome.executeAction(object -> {
            if (object instanceof Room) {
                rooms.remove(object);
            }
        });
        assertEquals(0, rooms.size());
    }





}
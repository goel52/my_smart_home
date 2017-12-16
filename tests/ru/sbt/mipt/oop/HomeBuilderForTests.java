package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

import java.util.Arrays;

/**
 * Created by Roman on 07.12.2017.
 */
public class HomeBuilderForTests {
    public static SmartHome initSmartHome() {
        //4 rooms
        //9 lights
        //4 doors
        Room kitchen = new Room(
                Arrays.asList(new Light("1", false), new Light("2", true)),
                Arrays.asList(new Door("1", false)),
                "кухня");
        Room bathroom = new Room(
                Arrays.asList(new Light("3", true)),
                Arrays.asList(new Door("2", false)),
                "ванная");
        Room bedroom = new Room(
                Arrays.asList(new Light("4", false), new Light("5", false), new Light("6", false)),
                Arrays.asList(new Door("3", true)),
                "спальня");
        Room hall = new Room(
                Arrays.asList(new Light("7", false), new Light("8", false), new Light("9", false)),
                Arrays.asList(new Door("4", true)),
                "коридор");
        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
        return smartHome;
    }
}

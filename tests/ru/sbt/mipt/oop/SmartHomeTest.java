package ru.sbt.mipt.oop;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SmartHomeTest {
    @Test
    public void addRoom() throws Exception {
        Room room = new Room(
                Arrays.asList(new Light("1", false), new Light("2", true)),
                Arrays.asList(new Door("1", false)),
                "кухня");
        SmartHome smartHome = new SmartHome();
        smartHome.addRoom(room);
        assertTrue(smartHome.getRooms().contains(room));
    }

}
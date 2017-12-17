package ru.sbt.mipt.oop;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ru.sbt.mipt.oop.EventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.EventType.LIGHT_ON;

public class DoorsEventHandlerTest {
    @Test
    public void testTurnOffSomeLights() {
        SmartHome smartHome = HomeBuilderForTests.initSmartHome();
        Collection<Room> rooms = smartHome.getRooms();

        String[] turnedOffLightId = new String[]{"2", "3", "4", "5"};
        LightsEventHandler processor = new LightsEventHandler();
//        for (String lightId : turnedOffLightId) {
//            Event event = new Event(LIGHT_OFF, lightId);
//            processor.handle(smartHome, event);
//        }

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(turnedOffLightId)) assertFalse(light.isOn());
            }
        }
    }

    @Test
    public void testTurnOnSomeLights() {
        SmartHome smartHome = HomeBuilderForTests.initSmartHome();
        Collection<Room> rooms = smartHome.getRooms();

        String[] turnedOffLightId = new String[]{"2", "3", "4", "5"};
        LightsEventHandler processor = new LightsEventHandler();
//        for (String lightId : turnedOffLightId) {
//            Event event = new Event(LIGHT_ON, lightId);
//            processor.handle(smartHome, event);
//        }

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(turnedOffLightId)) assertTrue(light.isOn());
            }
        }
    }
}
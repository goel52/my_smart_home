package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.AlarmSystem;
import ru.sbt.mipt.oop.AlarmSystemStates;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.Event;
import ru.sbt.mipt.oop.EventObserver;
import ru.sbt.mipt.oop.EventType;

import static org.junit.Assert.assertEquals;

public class TestAlarmSystem {
    private static final String TRUE_PASSWORD = "123";
    private static final String FALSE_PASSWORD = "321";
    Event event = new Event(EventType.DOOR_OPENED, "1");
    EventObserver eventObserver = new EventObserver(new SmartHome());

    // no action is required except turning on (without password)
    @Test
    public void testFromOffState() {
        AlarmSystem alarmSystem = new AlarmSystem(TRUE_PASSWORD, eventObserver);
        assertEquals(AlarmSystemStates.OFF, alarmSystem.getState());

        alarmSystem.turnOff();
        assertEquals(AlarmSystemStates.OFF, alarmSystem.getState());

        alarmSystem.enterPassword(TRUE_PASSWORD);
        assertEquals(AlarmSystemStates.OFF, alarmSystem.getState());

        alarmSystem.enterPassword(FALSE_PASSWORD);
        assertEquals(AlarmSystemStates.OFF, alarmSystem.getState());

        alarmSystem.onEvent(event);
        assertEquals(AlarmSystemStates.OFF, alarmSystem.getState());

        alarmSystem.turnOn();
        assertEquals(AlarmSystemStates.ON, alarmSystem.getState());
    }

    // no action is required except turning off and onEvent
    @Test
    public void testFromOnState() {
        AlarmSystem alarmSystem = new AlarmSystem(TRUE_PASSWORD, eventObserver);
        alarmSystem.turnOn();
        assertEquals(AlarmSystemStates.ON, alarmSystem.getState());

        alarmSystem.turnOn();
        assertEquals(AlarmSystemStates.ON, alarmSystem.getState());

        alarmSystem.enterPassword(TRUE_PASSWORD);
        assertEquals(AlarmSystemStates.ON, alarmSystem.getState());

        alarmSystem.enterPassword(FALSE_PASSWORD);
        assertEquals(AlarmSystemStates.ON, alarmSystem.getState());

        alarmSystem.onEvent(event);
        assertEquals(AlarmSystemStates.WAIT_FOR_PASSWORD, alarmSystem.getState());

        alarmSystem = new AlarmSystem(TRUE_PASSWORD, eventObserver);
        alarmSystem.turnOn();

        alarmSystem.turnOff();
        assertEquals(AlarmSystemStates.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }

    // no action is required except entering passwords
    @Test
    public void testFromWaitForPasswordState() {
        AlarmSystem alarmSystem = new AlarmSystem(TRUE_PASSWORD, eventObserver);
        alarmSystem.turnOn();
        alarmSystem.onEvent(event);
        alarmSystem.enterPassword(FALSE_PASSWORD);
        assertEquals(AlarmSystemStates.ALARM, alarmSystem.getState());

        alarmSystem = new AlarmSystem(TRUE_PASSWORD, eventObserver);
        alarmSystem.turnOn();
        alarmSystem.onEvent(event);
        assertEquals(AlarmSystemStates.WAIT_FOR_PASSWORD, alarmSystem.getState());

        alarmSystem.onEvent(event);
        assertEquals(AlarmSystemStates.WAIT_FOR_PASSWORD, alarmSystem.getState());

        alarmSystem.turnOn();
        assertEquals(AlarmSystemStates.WAIT_FOR_PASSWORD, alarmSystem.getState());

        alarmSystem.turnOff();
        assertEquals(AlarmSystemStates.WAIT_FOR_PASSWORD, alarmSystem.getState());

        alarmSystem.enterPassword(TRUE_PASSWORD);
        assertEquals(AlarmSystemStates.OFF, alarmSystem.getState());
    }

    // no action is required except entering true password
    @Test
    public void testFromAlarmState() {
        AlarmSystem alarmSystem = new AlarmSystem(TRUE_PASSWORD, eventObserver);
        alarmSystem.turnOn();
        alarmSystem.onEvent(event);
        alarmSystem.enterPassword(FALSE_PASSWORD);
        assertEquals(AlarmSystemStates.ALARM, alarmSystem.getState());

        alarmSystem.onEvent(event);
        assertEquals(AlarmSystemStates.ALARM, alarmSystem.getState());

        alarmSystem.turnOn();
        assertEquals(AlarmSystemStates.ALARM, alarmSystem.getState());

        alarmSystem.turnOff();
        assertEquals(AlarmSystemStates.ALARM, alarmSystem.getState());

        alarmSystem.enterPassword(TRUE_PASSWORD);
        assertEquals(AlarmSystemStates.OFF, alarmSystem.getState());
    }
}
package ru.sbt.mipt.oop;

import org.junit.Test;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.EventType.LIGHT_ON;

public class EventTest {
    @Test
    public void getType() throws Exception {
        Event event= new Event(LIGHT_ON,"123");
        assertTrue(event.getType()== LIGHT_ON);
    }
}






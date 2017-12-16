package ru.sbt.mipt.oop;

public class EventProducer {

    private AlarmSystem alarmSystem;

    public EventProducer(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    public Event getNextEvent() {

        if (Math.random() < 0.05) return null;
        EventType eventType = EventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new Event(eventType, objectId);
    }

    public void runEventCycle() {
        Event event = getNextEvent();
        while (event != null) {
            System.out.println("Got events: " + event);
            alarmSystem.onEvent(event);
            event = getNextEvent();
        }
    }
}
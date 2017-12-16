package ru.sbt.mipt.oop;

public class SensorEvent {
    private final CommandType type;
    private final String objectId;

    public SensorEvent(CommandType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}

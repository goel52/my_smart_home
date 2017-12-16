package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.EventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.EventType.DOOR_OPENED;

public class DoorEventHandler implements EventHandler {
    @Override
    public void handle(SmartHome smartHome, Event event) {
        if (isDoorEvent(event)) {
            smartHome.executeAction(Door.class, obj -> {
                Door door = (Door) obj;
                if (door.getId().equals(event.getObjectId())) {
                    changeDoorStatus(event, door);
                }
            });
        }
    }

    private boolean isDoorEvent(Event event) {
        return event.getType() == DOOR_OPENED || event.getType() == DOOR_CLOSED;
    }

    private void changeDoorStatus(Event event, Door door) {
        if (event.getType() == DOOR_OPENED
                && !door.isOpen()) {
            door.setOpen(true);
            System.out.println("Двеь " + door.getId() + " в комнате " + door.getRoomName() + " открыта.");
        } else if (event.getType() == DOOR_CLOSED
                && door.isOpen()) {
            door.setOpen(false);
            System.out.println("Дверь " + door.getId() + " в комнате " + door.getRoomName() + " закрыта.");
        }
    }
}

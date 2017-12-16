package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.EventType.DOOR_CLOSED;

//will turn off all lights if handler is active (when alarm system is OFF)

public class AutoEventHandler implements EventHandler, CommandHandler {
    private boolean isHandlerActive;

    public static AutoEventHandler getAutoEventHandler() {
        AutoEventHandler autoEventHandler = new AutoEventHandler();
        autoEventHandler.setHandlerStatus(true);
        return autoEventHandler;
    }

    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
    @Override
    public void handle(SmartHome smartHome, Event event) {
        if (isHandlerActive && isDoorClosedEvent(event)) {
            smartHome.executeAction(Door.class, object -> {
                Door door = (Door) object;
                if (door.getId().equals(event.getObjectId())
                        && door.getRoomName().equals("коридор")) {
                    turnOffHomeLights(smartHome);
                }
            });
        }
    }

    private void turnOffHomeLights(SmartHome smartHome) {
        smartHome.executeAction(Light.class, object -> {
            Light light = (Light) object;
            light.setOn(false);
            SensorEvent command = new SensorEvent(CommandType.LIGHT_OFF, light.getId());
            Application.sendCommand(command);
        });
        System.out.println("Свет во всем доме выключен");
    }

    private boolean isDoorClosedEvent(Event event) {
        return event.getType() == DOOR_CLOSED;
    }

    @Override
    public void setHandlerStatus(boolean isActive) {
        isHandlerActive = isActive;
    }
}

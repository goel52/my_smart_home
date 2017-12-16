package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.EventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.EventType.LIGHT_ON;

public class LightsEventHandler implements EventHandler {
    @Override
    public void handle(SmartHome smartHome, Event event) {
        if (isLightEvent(event)) {
            smartHome.executeAction(Light.class, obj -> {
                Light light = (Light) obj;
                if (light.getId().equals(event.getObjectId())) {
                    changeLightStatus(event, light);
                }
            });
        }
    }

    private boolean isLightEvent(Event event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }

    private void changeLightStatus(Event event, Light light) {
        if (event.getType() == LIGHT_ON
                && !light.isOn()) {
            light.setOn(true);
            System.out.println("Свет " + light.getId() + " в комнате " + light.getRoomName() + " включен.");
        } else if (event.getType() == LIGHT_OFF
                && light.isOn()) {
            light.setOn(false);
            System.out.println("Свет " + light.getId() + " в комнате " + light.getRoomName() + " выклюен");
        }
    }
}

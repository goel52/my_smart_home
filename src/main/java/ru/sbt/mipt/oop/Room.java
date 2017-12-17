package ru.sbt.mipt.oop;

import java.util.Collection;
import java.util.Iterator;

import static ru.sbt.mipt.oop.EntityType.DOOR;
import static ru.sbt.mipt.oop.EntityType.LIGHT;
import static ru.sbt.mipt.oop.EntityType.ROOM;

public class Room implements Actionable {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
        for (Door door : getDoors()) {
            door.setRoomName(getName());
        }
        for (Light light : getLights()) {
            light.setRoomName(getName());
        }
    }

    public Room() {

    }
    public Iterator getLightsIterator() {
        return lights.iterator();
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    @Override
    public void executeAction(Action action) {
        for (Light light : lights) {
            light.executeAction(action);
        }
        for (Door door : doors) {
            door.executeAction(action);
        }
        action.execute(this);
    }
}
